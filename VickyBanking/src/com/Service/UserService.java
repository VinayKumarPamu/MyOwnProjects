package com.Service;

public interface UserService {
	
	public  UserDTO CreateAccount();
	public UserDTO AuthenticateUser();
	public void Banking(String AccNum);
}
