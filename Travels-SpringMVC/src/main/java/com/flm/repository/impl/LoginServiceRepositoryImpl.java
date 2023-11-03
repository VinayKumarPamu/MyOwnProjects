package com.flm.repository.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.builder.JourneyRowMapper;
import com.builder.UserRowMapper;
import com.entity.User;
import com.flm.repository.LoginServiceRepository;
import com.entity.Journey;


@Repository
public class LoginServiceRepositoryImpl  implements LoginServiceRepository{
	@Autowired
	JdbcTemplate jdbcTemplate;

	@Override
	public void singUpUser(User user) {
		int rowCount = jdbcTemplate.update("INSERT INTO userdetails1 (id,Name,UserName,email,pwd) VALUES (?,?,?,?,?)",user.getId(),user.getFname(),user.getUsername(),user.getEmail(),user.getPwd());
		System.out.println("Rows INserted "+rowCount);
	}
	@Override
	public  List<User> signInUser(User user) {
		List<User> result = jdbcTemplate.query("select * from userdetails1 where UserName=? and pwd=?",new UserRowMapper(),user.getUsername(),user.getPwd());
		System.out.println(result.toString());
		return result;
	}
	@Override
	public void journeyDetails(Journey jrny) {
		int rowCount = jdbcTemplate.update("INSERT INTO journeydetails (source,Destination,JourneyDate,PassingerNum,Price,id) VALUES (?,?,?,?,?,?)",
				jrny.getSource(),jrny.getDestination(),jrny.getJourneyDate(),jrny.getPassingerNum(),jrny.getPrice(),jrny.getId());
		System.out.println("Rows INserted "+rowCount);
	}
	@Override
	public List<Journey> retriveJrny(Journey jrny) {
		List<Journey> result = jdbcTemplate.query("select * from journeydetails where id=?",new JourneyRowMapper(),jrny.getId());
		System.out.println(result.toString());
		return result;
	}
	
	@Override
	public void UpdateTicket(Journey jrny) {
		int rowCount = jdbcTemplate.update("update journeydetails set source=?,Destination=?,PassingerNum=?,Price=? where JourneyDate=?",
				jrny.getSource(),jrny.getDestination(),jrny.getPassingerNum(),jrny.getPrice(),jrny.getJourneyDate());
		System.out.println("Rows INserted "+rowCount);
	}
}
