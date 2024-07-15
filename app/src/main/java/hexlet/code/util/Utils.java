package hexlet.code.util;

import hexlet.code.App;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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

    public static Timestamp formatTimestamp(Timestamp timestamp) {
        LocalDateTime localDateTime = timestamp.toLocalDateTime();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        String formattedDateTime = localDateTime.format(formatter);

        LocalDateTime parsedDateTime = LocalDateTime.parse(formattedDateTime, formatter);

        return Timestamp.valueOf(parsedDateTime);
    }

    public static String getCurrentDateTime() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        return now.format(formatter);
    }

    public static Timestamp getCurrentTimestamp() {
        LocalDateTime now = LocalDateTime.now();
        return Timestamp.valueOf(now);
    }
}
