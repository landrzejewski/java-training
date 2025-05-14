package pl.training.jdbc;

import pl.training.jdbc.utils.Mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class UserMapper implements Mapper<Optional<User>> {

    private final static String ID_COLUMN = "id";
    private final static String NAME_COLUMN = "name";
    private final static String EMAIL_COLUMN = "email";

    @Override
    public Optional<User> map(ResultSet resultSet) throws SQLException {
        if (resultSet.next()) {
            var id = resultSet.getLong(ID_COLUMN);
            var name = resultSet.getString(NAME_COLUMN);
            var email = resultSet.getString(EMAIL_COLUMN);
            var user = new User(id, name, email);
            return Optional.of(user);
        }
        return Optional.empty();
    }

}