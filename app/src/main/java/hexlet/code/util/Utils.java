package hexlet.code.util;

import hexlet.code.App;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.stream.Collectors;

public class Utils {

    public static String readResourceFile(String fileName) throws IOException {
        InputStream resource = App.class.getClassLoader().getResourceAsStream(fileName);
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(resource, StandardCharsets.UTF_8))) {
            return reader.lines().collect(Collectors.joining("\n"));
        }
    }

    public static int getPort() {
        String port = System.getenv().getOrDefault("DB_PORT", "7070");
        return Integer.parseInt(port);
    }

    public static String getDataBaseUrl() {
        return System.getenv()
                .getOrDefault("JDBC_DATABASE_URL", "jdbc:h2:mem:project;DB_CLOSE_DELAY=-1");
    }

    public static String getDataBasePassword() {
        return System.getenv().getOrDefault("PASSWORD", "");
    }

    public static String getDataBaseUsername() {
        return System.getenv().getOrDefault("USERNAME", "");
    }

    public static String formatTimestamp(Timestamp timestamp) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        return timestamp.toLocalDateTime().format(formatter);
    }

    public static Timestamp getCurrentTimestamp() {
        LocalDateTime now = LocalDateTime.now();
        return Timestamp.valueOf(now);
    }

    public static String formatUrl(URL url) {
        String authority = url.getAuthority() == null ? "" : url.getAuthority();
        String protocol = url.getProtocol();
        return String.format("%s://%s", protocol, authority);
    }

    public static String removeTrailingSlash(String input) {
        if (input != null && input.endsWith("/")) {
            return input.replaceAll("/$", "");
        }
        return input;
    }
}
