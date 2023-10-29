package VickyTravels;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="userdetails1")
public class userdetails {
	@Id
	int id;
	String fname;
	String lname;
	String email;
	String gender;
	String pwd;
	String mobileNum;
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
	public String getLname() {
		return lname;
	}
	public void setLname(String lname) {
		this.lname = lname;
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
		return "userDetails [id=" + id + ", fname=" + fname + ", lname=" + lname + ", gender=" + gender + ", email=" + email
				+ ", mobNum=" + mobileNum + ", pwd=" + pwd + "]";
	}
	public userdetails() {
	}

}
