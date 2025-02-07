package pl.training.jdbc;

import pl.training.jdbc.utils.Mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsersMapper implements Mapper<List<User>> {

    private final static UserMapper USER_MAPPER = new UserMapper();

    @Override
    public List<User> map(ResultSet resultSet) throws SQLException {
        var result = new ArrayList<User>();
        while (resultSet.next()) {
            USER_MAPPER.map(resultSet).ifPresent(result::add);
        }
        return result;
    }

}
