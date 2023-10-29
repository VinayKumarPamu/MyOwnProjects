package VickyTravels;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

public class SignInSignOut {
	public static String Enum=null;
	static int validCount=0;
	public static ArrayList<Object> userDetails = new ArrayList<>();
	public static ArrayList<Object> userdetails = new ArrayList<>();
	public static void siso() throws SQLException {
		System.out.println(validCount);
		int num=1;
		String email=null;
		String eMail=null;
		String password=null;
		PreparedStatement stmt;
		String firstname = null;
		String lastname = null;
		String gender = null;
		String userid = null;
		String passWord = null;
		String mobilenum = null;
		dbOperations.DBoperations();
		Scanner scanner=new Scanner(System.in);
		boolean validInput=false;
		while (num > 0 && num < 2) {
			while (!validInput) {
				try {
					System.out.println("\n1.New User Registration\n2.Sign-In\n3.Exit");
					num = scanner.nextInt();
					validInput = true;
					if(num>3) {
						throw new Exception();
					}
				} catch (Exception e) {
					System.out.println("Invalid input. Please enter correctly");
					scanner.nextLine(); // consume the invalid input
				}
			}
			if(num==3) {
				System.exit(0);
			}
			if (num == 1) { // Sign-Up
				int num1 = 1;
				while (num1 > 0 && num1 <= 6) { // Taking data from user in menu Style
					while (validInput) {
						try {
							System.out.println(
									"1.First Name\n2.Last Name\n3.MobileNumber\n4.Gender\n5.E-mail\n6.Password");
							num1 = scanner.nextInt();
							validInput = false;
							if(num1>6) {
								throw new Exception("entered number is wrong");
							}
						} catch (Exception e) {
							System.out.println("Invalid input. Please enter correctly.");
							scanner.nextLine(); // consume the invalid input
						}
					}
					switch (num1) {
					case 1:
						System.out.println("Enter your first name");
						firstname = scanner.next();
						break;
					case 2:
						System.out.println("Enter your last name");
						lastname = scanner.next();
						break;
					case 3:
						System.out.println("Enter your mobile number");
						int temp5 = 0;
						while (temp5 == 0) {
							mobilenum = scanner.next();
							if (mobilenum.length() == 10) {
								Enum=mobilenum;
								temp5 = 1;
							} else {
								System.out.println("Wrong Mobile Number");
								System.out.println("Enter 10 digits of your mobile number");
							}
						}
						break;
					case 4:
						System.out.println("Enter your Gender");
						gender = scanner.next();
						break;
					case 5:
						System.out.println("Enter your Email Adderss");
						boolean valid=false;
						while(!valid) {
							email = scanner.next();
							if(email.contains("@")&&email.contains(".")&&email.indexOf(".")>email.indexOf("@")+2) {
								eMail=email;
								valid=true;
							}else {
								System.out.println("Please enter a valid email.");
							}
						}
						break;
					case 6:
						System.out.println("Set your Password");
						password = scanner.next();
						if (isValidPassword(password)) {
							System.out.println("Password is valid.");
						} else {
							System.out.println("Password is not valid.");
						}
						break;
					}
					num1++;
				}
				stmt=dbOperations.connection.prepareStatement("insert into LOGIN_CREDINTIALS(fname,lname,gender,eMAIL,Mobile_Number,PASSWORD) values(?,?,?,?,?,?)");
				stmt.setString(1,firstname);
				stmt.setString(2, lastname);
				stmt.setString(3, gender);
				stmt.setString(4,eMail);
				stmt.setString(5, Enum);
				stmt.setString(6, password);
				try {
					stmt.executeUpdate();
					System.out.println("Your Account successfully created");
				}catch(SQLIntegrityConstraintViolationException e) {
					System.out.println("Email or Mobile number already exists");
					siso();
				}
			}
			if (num == 2) { // Sign-In
				dbOperations.DBop();
				int num1 = 1;
				while (num1 > 0 && num1 <= 2) {
					while (validInput) {
						try {
							System.out.println("1.UserName\n2.Password\n3.Forgot Password");
							num1 = scanner.nextInt();
							validInput = false;
							if(num1>3) {
								throw new Exception();
							}
						} catch (Exception e) {
							System.out.println("Invalid input. Please enter correctly");
							scanner.nextLine(); // consume the invalid input
						}
					}
					switch (num1) {
					case 1:
						System.out.println("Enter your User Id");
						userid = scanner.next();
						validInput = false;
						break;
					case 2:
						System.out.println("Enter your Password");
						passWord = scanner.next();
						break;
					}
					num1++;
				}validInput=true;
				if(num1==4) {
					System.out.println("Enter your User Id for reference");
					userid = scanner.next();
					PreparedStatement stateOgj2 = dbOperations. connection.prepareStatement("select * from LOGIN_CREDINTIALS where  eMAIL = ?");
					stateOgj2.setString(1, userid);
					ResultSet res=stateOgj2.executeQuery();
					while(res.next()) {
						userdetails.add(0,res.getString(5));
						userdetails.add(1,res.getString(7));
					}
					if(userid.equals(userdetails.get(0))) {
						System.out.println("Your Password for the User name:"+userdetails.get(0)+" is "+userdetails.get(1));
					}else {
						System.out.println("User not found\nPlease create account");
					}
					siso();
				}
			}
			if(validInput) {
				int temp=0;
				while(dbOperations.result.next()) {
					try {
						if (dbOperations.result.getString(5).equals(userid)&&dbOperations.result.getString(7).equals(passWord)) { 
							temp=1;
							userDetails.add(dbOperations.result.getString(1));
							userDetails.add(dbOperations.result.getString(2));
							userDetails.add(dbOperations.result.getString(3));
							userDetails.add(dbOperations.result.getString(4));
							userDetails.add(dbOperations.result.getString(5));
							userDetails.add(dbOperations.result.getString(6));
							userDetails.add(dbOperations.result.getString(7));
						}
						else if (dbOperations.result.getString(5).equals(userid)&&!dbOperations.result.getString(7).equals(passWord)) { 
							System.out.println("Wrong password entered");
							validCount++;
						}
					}catch(NullPointerException e) {}
				}
				if(temp==1) {
					journeyDetails.jourNey();
				}else {
					System.out.println("User not found");
					if(validCount<5) {
						siso();
					}
				}
				if(validCount>4) {
					// Locking Function
					System.out.println("Your Account has been locked for today due to multiple invalid attempts");
					LocalDateTime time = LocalDateTime.now();
					LocalDateTime time1 = time.plusHours(24);
					if (time == time1) {
						continue;
					}
					System.exit(0);
				}
			}
		}
	}
	public static boolean isValidPassword(String password) {
		if (password.length() < 8) {
			return false;
		}
		boolean hasLetter = false;
		boolean hasDigit = false;
		for (char c : password.toCharArray()) {
			if (Character.isLetter(c)) {
				hasLetter = true;
			} else if (Character.isDigit(c)) {
				hasDigit = true;
			}
		}
		return hasLetter && hasDigit;
	}
}