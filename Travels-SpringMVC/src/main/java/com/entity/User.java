package com.entity;

public class User {
	int id;
	String fname;
	String email;
	String gender;
	String pwd;
	String mobileNum;
	String Username;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public String getUsername() {
		return Username;
	}
	public void setUsername(String lname) {
		this.Username = lname;
	}
	public User(String username, String pwd) {
		super();
		this.pwd = pwd;
		Username = username;
	}
	public User() {
		super();
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMobNum() {
		return mobileNum;
	}
	public void setMobNum(String mobNum) {
		this.mobileNum = mobNum;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	@Override
	public String toString() {
		return "userDetails [id=" + id + ", fname=" + fname + ", Username=" + Username +", email=" + email
				+  ", pwd=" + pwd + "]";
	}
	public User(int id, String fname, String Username, String email, String pwd) {
		super();
		this.id = id;
		this.fname = fname;
		this.Username = Username;
		this.email = email;
		this.pwd = pwd;
	}

}
