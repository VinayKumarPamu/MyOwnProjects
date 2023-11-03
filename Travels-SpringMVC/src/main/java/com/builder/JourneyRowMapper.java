package com.builder;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

import com.entity.Journey;
public class JourneyRowMapper implements RowMapper<Journey> {
    @Override
    public Journey mapRow(ResultSet rs, int rowNum) throws SQLException {
        // Map the ResultSet data to a User object
        Journey jrny=new Journey();
        jrny.setSource(rs.getString(1));
        jrny.setDestination(rs.getString(2));
        jrny.setJourneyDate(rs.getString(3));
        jrny.setPassingerNum(rs.getInt(4));
        jrny.setPrice(rs.getFloat(5));
        jrny.setId(rs.getInt(6));
        return jrny;
    }
}