package hexlet.code;

import hexlet.code.model.Url;
import hexlet.code.model.UrlCheck;
import hexlet.code.repository.UrlRepository;
import hexlet.code.rout.NamedRoutes;
import io.javalin.Javalin;
import io.javalin.testtools.JavalinTest;
import okhttp3.Response;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;

public class AppTest {

    private static Javalin app;
    private static MockWebServer mockWebServer;

    private static Path getPathToFixtures() {
        return Paths.get("src/test/resources/fixtures/mock.html").toAbsolutePath().normalize();
    }

    private static String readTestFile() throws IOException {
        Path pathToMockFile = getPathToFixtures();
        return Files.readString(pathToMockFile);
    }

    @BeforeAll
    public static void beforeAll() throws IOException {
        mockWebServer = new MockWebServer();
        MockResponse mockResponse = new MockResponse()
                .setBody(readTestFile());
        mockWebServer.enqueue(mockResponse);
        mockWebServer.start();
    }

    @AfterAll
    public static void afterAll() throws IOException {
        mockWebServer.shutdown();
    }

    @BeforeEach
    public final void setApp() throws Exception {
        app = App.getApp();
    }

    @Test
    public void testWebRequests() {
        String initialUrl = mockWebServer.url("/").toString().replaceAll("/$", "");
        JavalinTest.test(app, (server, client) -> {
            String requestAttribute = "url=" + initialUrl;
            assertThat(client.post(NamedRoutes.pathToSites(), requestAttribute).code()).isEqualTo(200);

            Url url = UrlRepository.findByName(initialUrl).orElse(null);
            assertThat(url.getName()).isEqualTo(initialUrl);
            client.post(NamedRoutes.pathToCheck(url.getId()));
            Response response = client.get(NamedRoutes.pathToSite(url.getId()));
            assertThat(response.code()).isEqualTo(200);
            assertThat(response.body().string()).contains(initialUrl);
            UrlCheck urlCheck = UrlRepository.retrieveLatestChecks().get(url.getId());

            assertThat(urlCheck.getStatusCode()).isEqualTo(200);
            assertThat(urlCheck.getTitle()).isEqualTo("Mock page");
            assertThat(urlCheck.getH1()).isEqualTo("And the rain will kill us all "
                    + "Throw ourselves against the wall "
                    + "But no one else can see "
                    + "The preservation of the martyr in me "
                    + "Psychosocial!");
            assertThat(urlCheck.getDescription()).contains("The limits of the dead!");
        });
    }

    @Test
    public void testRootPage() {
        JavalinTest.test(app, ((server, client) -> {
            var response = client.get("/");
            assertThat(response.code()).isEqualTo(200);
        }));
    }

    @Test
    public void testWebSitesPage() {
        JavalinTest.test(app, ((server, client) -> {
            var response = client.get("/urls");
            assertThat(response.code()).isEqualTo(200);
        }));
    }

    @Test
    public void testUrlNotFound() {
        JavalinTest.test(app, ((server, client) -> {
            var response = client.get("/urls/7777777");
            assertThat(response.code()).isEqualTo(404);
        }));
    }

    @Test
    public void testSuccessRequest() throws SQLException {
        Url newUrl = new Url("https://github.com");
        UrlRepository.save(newUrl);
        JavalinTest.test(app, ((server, client) -> {
            Response response = client.get("/urls/" + newUrl.getId());
            assertThat(response.code()).isEqualTo(200);
        }));
    }

    @Test
    public void testSavedUrl() throws SQLException {
        String initialUrl = "https://github.com";
        JavalinTest.test(app, (server, client) -> {
            String requestBody = "url=" + initialUrl;
            try (Response response = client.post("/urls", requestBody)) {
                assertThat(response.code()).isEqualTo(200);
            }
        });
        Url url = UrlRepository.findByName(initialUrl).isPresent() ? UrlRepository.findByName(initialUrl).get() : null;
        assertThat(Objects.requireNonNull(url).getName()).isEqualTo(initialUrl);
    }
}
