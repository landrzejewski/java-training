package pl.training.jdbc;

import pl.training.jdbc.utils.HikariDataSourceProvider;
import pl.training.jdbc.utils.JdbcTemplate;
import pl.training.jdbc.utils.ParameterSource;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JdbcExample {

    private static final String URL = "jdbc:postgresql://localhost:5432/training";
    private static final String USER = "admin";
    private static final String PASSWORD = "admin";

    private static final Logger LOGGER = Logger.getLogger(JdbcExample.class.getName());
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
    private static final String SELECT_USERS_SQL = """
            SELECT * from users
            """;
    private static final String SELECT_USER_BY_ID_SQL = """
            SELECT * from users where
                id = ?
            """;
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
        var dataSourceProvider = new HikariDataSourceProvider(URL, USER, PASSWORD);
        var jdbcTemplate = new JdbcTemplate(dataSourceProvider);
        try {
            jdbcTemplate.createTable(CREATE_TABLE_SQL);

            // insert
            var userId = jdbcTemplate.insert(INSERT_USER_SQL, statement -> {
                statement.setString(1, "Marek Nowak");
                statement.setString(2, "marek@training.pl");
            }).orElseThrow();

            // update
            /*var updatedUser = new User(userId, "Jan Kowalski", "jan@training.pl");
            jdbcTemplate.update(UPDATE_USER_SQL, statement -> {
                statement.setString(1, updatedUser.name());
                statement.setString(2, updatedUser.email());
                statement.setLong(3, updatedUser.id());
            });*/

            // delete
            // jdbcTemplate.update(DELETE_USER_SQL, statement -> statement.setLong(1, userId));

            // select
            jdbcTemplate.select(SELECT_USERS_SQL, ParameterSource.NONE, new UsersMapper())
                    .forEach(System.out::println);

            ParameterSource parameterSource = statement -> statement.setLong(1, userId);
            var userWithId = jdbcTemplate.select(SELECT_USER_BY_ID_SQL, parameterSource, new UserMapper());
            System.out.println(userWithId);
        } catch (SQLException exception) {
            LOGGER.log(Level.SEVERE, exception.getMessage());
        }
    }

}





