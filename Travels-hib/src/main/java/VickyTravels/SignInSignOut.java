package VickyTravels;

import java.sql.SQLException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
public class SignInSignOut {
	static journeyDetails jrny=new journeyDetails();
	static int validCount=0;
	static Scanner scanner=new Scanner(System.in);
	public static ArrayList<Object> userArray = new ArrayList<Object>();
	public static void siso() throws SQLException {
		String Enum=null;
		int num=1;
		String email=null;
		String eMail=null;
		String password=null;
		String firstname = null;
		String lastname = null;
		String gender = null;
		String userid = null;
		String passWord = null;
		String mobilenum = null;
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
				userdetails user=new userdetails();
				user.setFname(firstname);
				user.setLname(lastname);
				user.setEmail(eMail);
				user.setGender(gender);
				user.setMobNum(Enum);
				user.setPwd(password);
				DBop.update(user,user.getEmail());
				System.out.println(DBop.retrive(user.getEmail()));	
			}
			if (num == 2) { // Sign-In
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
				if(num1==4) {			//		forgot pwd
					System.out.println("Enter your User Id for reference");
					userid = scanner.next();
					userdetails reference = DBop.retrive(userid);
					if(userid.equals(reference.getEmail())) {
						System.out.println("Your Password for the User name:"+reference.getEmail()+" is "+reference.getPwd());
					}else {
						System.out.println("User not found\nPlease create account");
					}
					siso();
				}
			}
			if(validInput) {
				int temp=0;
				try {
					userdetails user1=new userdetails();
					user1=DBop.retrive(userid);
					if (user1.getEmail().equals(userid)&&user1.getPwd().equals(passWord)) { 
						temp=1;
						userArray.add(user1.getFname());
						userArray.add(user1.getLname());
						userArray.add(user1.getGender());
						userArray.add(user1.getMobNum());
						userArray.add(user1.getEmail());
						userArray.add(user1.getPwd());
					}
					else if (user1.getEmail().equals(userid)&&!user1.getPwd().equals(passWord)) { 
						System.out.println("Wrong password entered");
						validCount++;
					}else {
						System.out.println("User not found");
					}
				}catch(NullPointerException e) {}
				if(temp==1) {
					mainMenu.jourNey();
				}else {
					System.out.println("User not found");
					if(validCount<5) {
						siso();
					}
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
	public static void journey() {
		System.out.println("Enter source of your journey");
		String source = scanner.next();
		System.out.println("Enter destination of your journey");
		String destination = scanner.next();
		System.out.println("Enter date of journey in the formate of \nYYYY\nMM\nDD");
		LocalDate journeyDate = LocalDate.of(scanner.nextInt(), scanner.nextInt(), scanner.nextInt());
		System.out.println("Enter passingers count");
		int passingernum = scanner.nextInt();
		jrny.setSource(source);
		jrny.setDestination(destination);
		jrny.setPassingerNum(passingernum);
		jrny.setJourneyDate(journeyDate.toString());
		jrny.setMobNum((String) userArray.get(3));
		//		DBop.update(jrny,(String) userDeTails.get(3));
	}
	static final int BusSeatingCount = 56;
	public static void Prising() {
		journey();
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
		System.out.println("You want to continue to book tickets?\n1.YES\n2.NO");
		boolean test=false;
		int confirm=0;
		while(!test) {
			confirm=scanner.nextInt();
			test=true;
			if(confirm>2) {
				System.out.println("Please enter correctly");
				test=false;
			}
		}
		if(mainMenu.editJ!=1) {
			if(confirm==1) {
				payment.paymenT();
				jrny.setPrice(Price);
				DBop.update(jrny,""+userArray.get(3));
				System.out.println("Tickets booked!!...");
			}else {
				System.out.println("No tickets booked!!");
			}
		}else {
			if(confirm==1) {
				payment.paymenT();
				jrny.setPrice(Price);
				List<seating> details = DBop.retriveSeat(jrny.journeyDate);
				for (seating journey : details) {
					if(journey.JourneyDate.equals(jrny.journeyDate)) {
						DBop.editedUpdate(jrny);
					}else {
						break;
					}
				}
				System.out.println("Tickets updated!!...");
			}else {
				System.out.println("No tickets updated!!");
			}
		}
	}
	public static void Seating() throws SQLException {
		int seatInt = 0;
		String source = jrny.getSource();
		String destination = jrny.getDestination();
		List<journeyDetails> details = DBop.retriveJourney(source,destination);
		int passingerNum = 0;
		seating seat=new seating();
		for (journeyDetails journeyDetails : details) {
			seat.setJourneyDate(journeyDetails.getJourneyDate());
			List<seating> retriveSeat = DBop.retriveSeat(journeyDetails.getJourneyDate());
			for (seating seating : retriveSeat) {
				if(mainMenu.editJ==1) {
					if(Objects.equals(jrny.journeyDate, journeyDetails.getJourneyDate())) {
						passingerNum = journeyDetails.getPassingerNum();
					}
				}else {
					passingerNum = jrny.passNum;
				}
				seatInt=seating.getSeatCapacity();
				seatInt+=passingerNum;
				seat.setSeatCapacity(seatInt);
			}
			if(retriveSeat.isEmpty()) {
				seat.setSeatCapacity(journeyDetails.getPassingerNum());
			} 
			int availSeats = BusSeatingCount - seatInt;
			if(availSeats<=0) {
				System.out.println("Seates not available for selected date");
				break;
			}
			DBop.update(seat,journeyDetails.getJourneyDate());
		}
	}
}