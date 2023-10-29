package com.VickyTravels;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

public class SignInSignUp extends Logo {
	static int temp2 = 0;
	static int temp3 = 0;
	static int temp4 = 0;
	static int num3 = 0;
	public Map<Integer, String> menu = new HashMap<>();
	static Scanner scanner = new Scanner(System.in);

	public SignInSignUp() {
		//		Calling Class with Constructor Chaining method
		super();
		int num = 1;
		String firstname = null;
		String lastname = null;
		String mobilenum = null;
		String Enum = null;
		String gender = null;
		String email = null;
		String password = null;
		String userid = null;
		String passWord = null;
		boolean validInput = false;
		while (num > 0 && num < 2) { // Sign-in and Sign-Up Functions
			while (!validInput) {
				try {
					System.out.println("\n1.New User Registration\n2.Sign-In");
					num = scanner.nextInt();
					validInput = true;
				} catch (InputMismatchException e) {
					System.out.println("Invalid input. Please enter number");
					scanner.nextLine(); // consume the invalid input
				}
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
						} catch (InputMismatchException e) {
							System.out.println("Invalid input. Please enter an integer.");
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
						int temp5 = 0;
						while (temp5 == 0) {
							System.out.println("Enter your mobile number");
							Enum = scanner.next();
							if (Enum.length() == 10) {
								mobilenum = Enum;
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
						email = scanner.next();
						break;
					case 6:
						System.out.println("Set your Password");
						password = scanner.next();
						break;
					}
					num1++;
				}
			}
			Map<String, String> verify = new HashMap<>();
			verify.put(email, password);
			if (num == 2) { // Sign-In
				int num1 = 1;
				for (int temp = 0; (temp >= 0 && temp < 5); temp++) {
					while (num1 > 0 && num1 <= 2) {
						while (validInput) {
							try {
								System.out.println("1.UserName\n2.Password");
								num1 = scanner.nextInt();
								validInput = false;
							} catch (InputMismatchException e) {
								System.out.println("Invalid input. Please enter an integer.");
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
					}
					if (verify.get(email).equals(userid) && verify.get(password).equals(password)) { 
						// Validating User and allowing user to Move-On
						temp3 = 1;
						recalling();
						temp2 = 1;
						break;
					} else {
						System.out.println("Invalid User name or Password");
						userid = null;
						passWord = null;
						num1 = 1;
						validInput = true;
					}
					if (temp >= 4) { // Locking Function
						System.out.println("Your Account has been locked for today due to multiple invalid attempts");
						LocalDateTime time = LocalDateTime.now();
						LocalDateTime time1 = time.plusHours(24);
						if (time == time1) {
							continue;
						}
						break;
					}
				}
			}
		}
		// Mapping the user
		menu.put(0, firstname);
		menu.put(1, lastname);
		menu.put(2, mobilenum);
		menu.put(3, gender);
		menu.put(4, email);
		menu.put(5, password);
	}

	public static void recalling() {
		int num = 0;
		boolean validInput = false;
		if (temp2 == 1) {
			validInput = false;
			while (!validInput) {
				try {
					System.out.println("Book another Journey!!");
					System.out.println("1.Yes\n2.No(exit)");
					num = scanner.nextInt();
					validInput = true;
				} catch (InputMismatchException e) {
					System.out.println("Invalid input. Please enter number 1 or 2.");
					scanner.nextLine(); // consume the invalid input
				}
			}
			switch (num) {
			case 1:
				temp3 = 1;
				temp4 = 1;
				break;
			case 2:
				temp3 = 0;
				System.exit(0);
				break;
			}
		}
		if (temp3 == 1) {
			validInput = true;
			while (validInput) {
				try {
					System.out.println("1.Plan Journey\n2.Edit Journey ");
					num3 = scanner.nextInt();
					validInput = false;
				} catch (InputMismatchException e) {
					System.out.println("Invalid input. Please enter number 1 or 2.");
					scanner.nextLine(); // consume the invalid input
				}
			}
			switch (num3) {
			case 1:
				Journey.journeyDetails();
				System.out.println("The available seats are " + Journey.Seating());
				payment.payment();
				if (Journey.Seating() == 0) {
					System.out.println("There are no Seats available! Sorry for the inconvenience caused");
				}
				System.out.println("\nJourney Details are");
				break;
			case 2:
				Journey.journeyDetails();
				System.out.println("Your Updated Journey Details are\t");
				JourneyDetails.ticket();
				break;
			}
			if (temp4 == 1) {
				JourneyDetails.ticket();
			}
		}
	}
}
