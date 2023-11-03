package com.service;

import com.entity.User;
import java.util.List;
import com.entity.Journey;

public interface LoginService {
	
	void signUpUser(User user);
	List<Object> signInUser(User user);
	Journey journeyDetails(Journey jrny);
	void confirmTicket(Journey jrny);
	List<Journey> retriveJourney(Journey jrny);
	void UpdateTicket(Journey jrny);
	

}
