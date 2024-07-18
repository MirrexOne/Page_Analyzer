package hexlet.code.repository;

import hexlet.code.model.Url;
import hexlet.code.model.UrlCheck;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static hexlet.code.util.Utils.getCurrentTimestamp;

public class UrlRepository extends BaseRepository {
    public static void save(Url url) throws SQLException {
        String insertQuery = "INSERT INTO urls (name, created_at) VALUES (?, ?)";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, url.getName());
            statement.setTimestamp(2, getCurrentTimestamp());
            statement.executeUpdate();
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                url.setId(generatedKeys.getLong(1));
                url.setCreatedAt(getCurrentTimestamp());
            } else {
                throw new SQLException("DB have not returned an id after saving an entity");
            }
        }
    }

    public static void saveCheckups(UrlCheck check) throws SQLException {
        String insertQuery = "INSERT INTO url_checks (url_id, status_code, title, h1, description, created_at)"
                + "VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS)) {
            statement.setLong(1, check.getUrlId());
            statement.setInt(2, check.getStatusCode());
            statement.setString(3, check.getTitle());
            statement.setString(4, check.getH1());
            statement.setString(5, check.getDescription());
            statement.setTimestamp(6, getCurrentTimestamp());
            statement.executeUpdate();
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                check.setId(generatedKeys.getLong(1));
                check.setCreatedAt(getCurrentTimestamp());
            } else {
                throw new SQLException("Failed to save");
            }
        }

    }

    public static List<UrlCheck> findById(Long id) throws SQLException {
        String retrieveQuery = "SELECT * FROM url_checks WHERE url_id = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(retrieveQuery)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            List<UrlCheck> urlChecks = new ArrayList<>();
            while (resultSet.next()) {
                long id1 = resultSet.getLong("id");
                int statusCode = resultSet.getInt("status_code");
                String title = resultSet.getString("title");
                String h1 = resultSet.getString("h1");
                String description = resultSet.getString("description");
                Timestamp createdAt = resultSet.getTimestamp("created_at");
                UrlCheck urlCheck = new UrlCheck(statusCode, title, h1, description);
                urlCheck.setId(id1);
                urlCheck.setUrlId(id);
                urlCheck.setCreatedAt(createdAt);
                urlChecks.add(urlCheck);
            }
            return urlChecks;
        }
    }

    public static Map<Long, UrlCheck> retrieveLatestChecks() throws SQLException {
        String retrieveQuery = "SELECT * FROM url_checks";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(retrieveQuery)) {
            ResultSet resultSet = statement.executeQuery();
            Map<Long, UrlCheck> result = new HashMap<>();
            while (resultSet.next()) {
                long id = resultSet.getLong("id");
                long urlId = resultSet.getLong("url_id");
                int statusCode = resultSet.getInt("status_code");
                String title = resultSet.getString("title");
                String h1 = resultSet.getString("h1");
                String description = resultSet.getString("description");
                Timestamp createdAt = resultSet.getTimestamp("created_at");
                UrlCheck urlCheck = new UrlCheck(statusCode, title, h1, description);
                urlCheck.setId(id);
                urlCheck.setUrlId(urlId);
                urlCheck.setCreatedAt(createdAt);
                result.put(urlId, urlCheck);
            }
            return result;
        }
    }

    public static Optional<Url> find(Long id) throws SQLException {
        String retrieveQuery = "SELECT * FROM urls WHERE id = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(retrieveQuery)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String name = resultSet.getString("name");
                Timestamp createdAt = resultSet.getTimestamp("created_at");
                Url url = new Url(name);
                url.setId(id);
                url.setCreatedAt(createdAt);
                return Optional.of(url);
            }
            return Optional.empty();
        }
    }

    public static Optional<Url> findByName(String name) throws SQLException {
        String retrieveQuery = "SELECT * FROM urls WHERE name = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(retrieveQuery)) {
            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String name1 = resultSet.getString("name");
                long id = resultSet.getLong("id");
                Timestamp createdAt = resultSet.getTimestamp("created_at");
                Url url = new Url(name1);
                url.setId(id);
                url.setCreatedAt(createdAt);
                return Optional.of(url);
            }
            return Optional.empty();
        }
    }

    public static List<Url> getEntities() throws SQLException {
        String retrieveAll = "SELECT * FROM urls";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(retrieveAll)) {
            ResultSet resultSet = statement.executeQuery();
            List<Url> result = new ArrayList<>();

            while (resultSet.next()) {
                long id = resultSet.getLong("id");
                String name = resultSet.getString("name");
                Timestamp createdAt = resultSet.getTimestamp("created_at");
                Url url = new Url(name);
                url.setId(id);
                url.setCreatedAt(createdAt);
                result.add(url);
            }
            return result;
        }
    }
}
