package com.flm.repository;

import java.util.List;

import com.entity.User;
import com.entity.Journey;

public interface LoginServiceRepository {
	
	
	void singUpUser(User user);
	List<User> signInUser(User user);
	void journeyDetails(Journey jrny);
	List<Journey> retriveJrny(Journey jrny);
	void UpdateTicket(Journey jrny);

}
