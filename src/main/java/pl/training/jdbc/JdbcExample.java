package pl.training.jdbc;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JdbcExample {

    private static final String URL = "jdbc:postgresql://localhost:5432/training";
    private static final String USER = "admin";
    private static final String PASSWORD = "admin";
    private static final int MAX_POOL_SIZE = 6;
    private static final Logger LOGGER = Logger.getLogger(JdbcExample.class.getName());
    private static final int ID_COLUMN_INDEX = 1;
    private static final String CREATE_TABLE_SQL = """
            CREATE TABLE IF NOT EXISTS users (
                id SERIAL PRIMARY KEY,
                name VARCHAR(100) NOT NULL,
                email VARCHAR(100) NOT NULL
            )""";
    private static final String INSERT_USER_SQL = """
            INSERT INTO
                users (name, email)
                VALUES (?, ?)""";
    private static final String UPDATE_USER_SQL = """
            UPDATE users SET
                name = ?,
                email = ?
                WHERE id = ?""";
    private static final String DELETE_USER_SQL = """
            DELETE FROM users
                WHERE id = ?""";

    public static void main(String[] args) {
        System.setProperty("java.util.logging.SimpleFormatter.format", "%4$s: %5$s%n");
        new JdbcExample().start();
    }

    private void start() {
        var dataSource = hikariDataSource();
        try (var connection = dataSource.getConnection()) {
            createTable(connection);
            var user = insertUser(connection, new User("Marek Nowak","marek@training.pl")).orElseThrow();
            LOGGER.log(Level.INFO, "User inserted with id: " + user.id());
            // var updatedUser = new User(user.id(), "Jan Kowalski", "jan@training.pl");
            // updateUser(connection, updatedUser);
            // deleteUser(connection, user.id());
        } catch (SQLException exception) {
            LOGGER.log(Level.SEVERE, exception.getMessage());
        }
    }

    private DataSource hikariDataSource() {
        var config = new HikariConfig();
        config.setJdbcUrl(URL);
        config.setUsername(USER);
        config.setPassword(PASSWORD);
        config.setMaximumPoolSize(MAX_POOL_SIZE);
        return new HikariDataSource(config);
    }

    /*private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    private DataSource postgresDataSource() {
        var dataSource = new PGSimpleDataSource();
        dataSource.setURL(URL);
        dataSource.setUser(USER);
        dataSource.setPassword(PASSWORD);
        return dataSource;
    }*/

    private void createTable(Connection connection) throws SQLException {
        try (var statement = connection.createStatement()) {
            statement.execute(CREATE_TABLE_SQL);
        }
    }

    private Optional<User> insertUser(Connection connection, User user) throws SQLException {
        try (var preparedStatement = connection.prepareStatement(INSERT_USER_SQL, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, user.name());
            preparedStatement.setString(2, user.email());
            int insertedRows = preparedStatement.executeUpdate();
            if (insertedRows == 1) {
                var resultSet = preparedStatement.getGeneratedKeys();
                if (resultSet.next()) {
                    var id = resultSet.getLong(ID_COLUMN_INDEX);
                    return Optional.of(user.withId(id));
                }
            }
        }
        return Optional.empty();
    }

    private void updateUser(Connection connection, User user) throws SQLException {
        try (var preparedStatement = connection.prepareStatement(UPDATE_USER_SQL)) {
            preparedStatement.setString(1, user.name());
            preparedStatement.setString(2, user.email());
            preparedStatement.setLong(3, user.id());
            int updatedRows = preparedStatement.executeUpdate();
            LOGGER.log(Level.INFO, updatedRows + " rows updated");
        }
    }

    private void deleteUser(Connection connection, Long userId) throws SQLException {
        try (var preparedStatement = connection.prepareStatement(DELETE_USER_SQL)) {
            preparedStatement.setLong(1, userId);
            int deletedRows = preparedStatement.executeUpdate();
            LOGGER.log(Level.INFO, deletedRows + " rows deleted");
        }
    }

}

record User(Long id, String name, String email) {

    public User(String name, String email) {
        this(null, name, email);
    }

    public User withId(Long id) {
        return new User(id, name, email);
    }

}
