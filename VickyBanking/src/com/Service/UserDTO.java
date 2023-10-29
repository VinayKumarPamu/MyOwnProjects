package com.Service;


public class UserDTO {
	String UserFirstName;
	String UserLastName;
	String MobNum;
	String Email;
	String Gender;
	String HashedPassword;
	int failCount;
	String lastLogIn;
	int salt;
	String AccNum;
	
	public int getFailCount() {
		return failCount;
	}
	public void setFailCount(int failCount) {
		this.failCount = failCount;
	}
	public String getLastLogIn() {
		return lastLogIn;
	}
	public void setLastLogIn(String lastLogIn) {
		this.lastLogIn = lastLogIn;
	}
	public String getAccNum() {
		return AccNum;
	}
	public void setAccNum(String accNum) {
		AccNum = accNum;
	}
	public String getUserFirstName() {
		return UserFirstName;
	}
	public void setUserFirstName(String userFirstName) {
		UserFirstName = userFirstName;
	}
	public String getUserLastName() {
		return UserLastName;
	}
	public void setUserLastName(String userLastName) {
		UserLastName = userLastName;
	}
	public String getMobNum() {
		return MobNum;
	}
	public void setMobNum(String mobNum) {
		MobNum = mobNum;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	public String getGender() {
		return Gender;
	}
	public void setGender(String gender) {
		Gender = gender;
	}
	public String getHashedPassword() {
		return HashedPassword;
	}
	public void setHashedPassword(String hashedPassword) {
		HashedPassword = hashedPassword;
	}
	public int getSalt() {
		return salt;
	}
	public void setSalt(int salt) {
		this.salt = salt;
	}
	
	
}
