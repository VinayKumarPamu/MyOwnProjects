package com.builder;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

import com.entity.User;
public class UserRowMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        // Map the ResultSet data to a User object
        User user = new User();
        user.setFname(rs.getString(2));
        user.setEmail(rs.getString(4));
        user.setPwd(rs.getString(5));
        user.setUsername(rs.getString(3));
        user.setId(rs.getInt(1));
        return user;
    }
}