package hexlet.code;

import hexlet.code.model.Url;
import hexlet.code.repository.UrlRepository;
import io.javalin.Javalin;
import io.javalin.testtools.JavalinTest;
import okhttp3.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;

public class AppTest {

    Javalin app;

    @BeforeEach
    public final void setApp() throws Exception {
        app = App.getApp();
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
    public void testSaveUrl() throws SQLException {
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
