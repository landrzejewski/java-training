package pl.training.jdbc;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.postgresql.ds.PGSimpleDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JdbcExample {

    private static final String URL = "jdbc:postgresql://localhost:5432/training";
    private static final String USER = "admin";
    private static final String PASSWORD = "admin";
    private static final int MAX_POOL_SIZE = 6;
    private static final Logger LOGGER = Logger.getLogger(JdbcExample.class.getName());
    private static final String CREATE_TABLE_SQL = """
            CREATE TABLE IF NOT EXISTS users (
                id SERIAL PRIMARY KEY,
                name VARCHAR(100) NOT NULL,
                email VARCHAR(100) NOT NULL UNIQUE
            )""";

    public static void main(String[] args) {
        System.setProperty("java.util.logging.SimpleFormatter.format", "%4$s: %5$s%n");
        new JdbcExample().start();
    }

    private void start() {
        var dataSource = hikariDataSource();
        try (var connection = dataSource.getConnection()) {
            createTable(connection);
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
            LOGGER.log(Level.INFO, "Table Users created");
        }
    }



}

// todo
// logowanie do pliku