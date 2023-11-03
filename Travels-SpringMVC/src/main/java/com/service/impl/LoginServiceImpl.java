package com.service.impl;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.entity.User;
import com.repository.LoginServiceRepository;
import com.entity.Journey;
import com.service.LoginService;

@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	LoginServiceRepository loginServiceRepository;

	@Override
	public void signUpUser(User user) {
		loginServiceRepository.singUpUser(user);
		System.out.println("User Inserted !!");

	}

	@Override
	public List<Object> signInUser(User user) {
		boolean userCheck=false;
		List<Object> usercheck=new ArrayList<>(2);
		List<User> signInUser = loginServiceRepository.signInUser(user);
		if(!signInUser.isEmpty()) {
				if(signInUser.get(0).getUsername().equals(user.getUsername())&& signInUser.get(0).getPwd().equals(user.getPwd())) {
					System.out.println("User Logged In !!");
					userCheck=true;
				}else {
					System.out.println("User Failed to logIn");
				}
		}else {
			System.out.println("User Details Not Found");
		}
		usercheck.add(0,userCheck);
		usercheck.add(1,signInUser.get(0).getId());
		return usercheck;
}
	@Override
	public Journey journeyDetails(Journey jrny) {
		String jrnyDate = jrny.getJourneyDate();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate localDate = LocalDate.parse(jrnyDate, formatter);
		float price = 1500;// Cost of Journey Generation //Assumption
		LocalDate journeyDate =localDate;
		DayOfWeek dayOfWeek = journeyDate.getDayOfWeek();
		String dayName = dayOfWeek.toString();
		if (dayName.equalsIgnoreCase("Satureday") || dayName.equalsIgnoreCase("Sunday")) {
			price = (float) (price + (200 + 200 * 0.18));
		}
		int passNum = jrny.getPassingerNum();
		float Price=price*passNum;
		System.out.println("Price for Tickets: "+Price);
		jrny.setPrice(Price);
		return jrny;
		
	}

	@Override
	public void confirmTicket(Journey jrny) {
		loginServiceRepository.journeyDetails(jrny);
		System.out.println("Journey details Updated");		
	}

	@Override
	public List<Journey> retriveJourney(Journey jrny) {
		return loginServiceRepository.retriveJrny(jrny);
		
	}
	

	@Override
	public void UpdateTicket(Journey jrny) {
		loginServiceRepository.UpdateTicket(jrny);
	}
	

}
