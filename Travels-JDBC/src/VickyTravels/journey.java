package VickyTravels;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;
import java.time.format.DateTimeFormatter;

public class journey {
	static String source=null;
	static String destination=null;
	static LocalDate journeyDate=null;
	static int passingernum =0;
	static int passingerNum;
	static int availSeats ;
	static String date=null;
	static final int BusSeatingCount = 56;
	public static ArrayList<Object> Seat=new ArrayList<>();
	static Scanner scanner = new Scanner(System.in);
	public static void Prising() throws SQLException {
		PreparedStatement stateOgj1 = dbOperations. connection.prepareStatement("select * from seating where JourneyDate='"
				+date+"'");
		ResultSet res1=stateOgj1.executeQuery();
		while(res1.next()) {
			Seat.add(res1.getString(1));
			Seat.add(res1.getString(2));
		}
		if(Integer.parseInt((String) Seat.get(1))>=BusSeatingCount) {
			System.out.println("Seates not available for selected date");
		}
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate localDate = LocalDate.parse(date, formatter);
		float price = 1500;// Cost of Journey Generation //Assumption
		LocalDate journeyDate =localDate;
		DayOfWeek dayOfWeek = journeyDate.getDayOfWeek();
		String dayName = dayOfWeek.toString();
		if (dayName.equalsIgnoreCase("Satureday") || dayName.equalsIgnoreCase("Sunday")) {
			price = (float) (price + (200 + 200 * 0.18));
		}
		float Price=price*passingerNum;
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
		if(confirm==1) {
			payment.paymenT();
			PreparedStatement updateStmt = dbOperations.connection.prepareStatement("UPDATE " + dbOperations.planName + 
					" SET Price = ? WHERE Mobile_Number = ? AND JourneyDate = ?");
			updateStmt.setFloat(1, Price);
			updateStmt.setString(2, (String) SignInSignOut.userDetails.get(5));
			updateStmt.setString(3, date);
			updateStmt.executeUpdate();
			updateStmt.close();
			System.out.println("Tickets booked!!...");
		}else {
			System.out.println("No tickets booked!!");
		}
	}
	public static void Seating() throws SQLException {
		ArrayList<Object> plan=new ArrayList<>();
		PreparedStatement stateOgj2 = dbOperations. connection.prepareStatement("select * from JOURNEY_DETAILS where Source='"
				+source+"'and Destination='"+destination+"'");
		ResultSet res=stateOgj2.executeQuery();
		while(res.next()) {
			plan.add(res.getString(1));
			plan.add(res.getString(2));
			plan.add(res.getString(3));
			plan.add(res.getString(4));
			plan.add(res.getString(5));
			plan.add(res.getString(6));
		}
		date=(String) plan.get(2);
		passingerNum =Integer.parseInt((String) plan.get(3));
		availSeats = BusSeatingCount - passingerNum;
		if(availSeats<=0) {
			System.out.println("Seates not available for selected date");
		}else {
			PreparedStatement stateobj = null;
			if(journeyDetails.editJ==0) {
				stateobj=dbOperations.connection.prepareStatement("insert into Seating (JourneyDate,SeatCapacity) values(?,?)");
			}
			else if(journeyDetails.editJ==1) {
				stateobj=dbOperations.connection.prepareStatement("update Seating set JourneyDate=?,SeatCapacity=?");
			}
			PreparedStatement stateOgj1 = dbOperations. connection.prepareStatement("select * from seating where JourneyDate='"
					+date+"'");
			ResultSet res1=stateOgj1.executeQuery();
			int passnum = 0;
			while(res1.next()) {
				Seat.add(res1.getString(1));
				Seat.add(res1.getString(2));
				if(passnum==0) {
					passnum=Integer.parseInt( (String) plan.get(3))+res1.getInt("SeatCapacity");
				}else {
					passnum+=res1.getInt("SeatCapacity");
				}
				stateobj=dbOperations.connection.prepareStatement("update Seating set JourneyDate=?,SeatCapacity=? where JourneyDate='"+date+"'");
				passingernum=passnum;
			}
			stateobj.setString(1, date);
			stateobj.setInt(2,passingernum);
			stateobj.executeUpdate();
		}
	}
	public static void journeyDetails() throws NullPointerException, SQLException {
		System.out.println("Enter source of your journey");
		source = scanner.next();
		System.out.println("Enter destination of your journey");
		destination = scanner.next();
		boolean test=false;
		while(!test) {
			System.out.println("Enter date of journey in the formate of \nYYYY\nMM\nDD");
			journeyDate = LocalDate.of(scanner.nextInt(), scanner.nextInt(), scanner.nextInt());
			int date=journeyDate.compareTo(LocalDate.now());
			test=true;
			if(date<0) {
				System.out.println("Please enter a valid date");
				test=false;
			}
		}
		System.out.println("Enter passingers count");
		passingernum = scanner.nextInt();
		journeYDBop();
	}
	public static void journeYDBop() throws SQLException {
		PreparedStatement stateOgj = null;
		if(journeyDetails.editJ==0) {
			stateOgj=dbOperations.connection.prepareStatement("insert into "+dbOperations.planName+"(Source,Destination,Journeydate,passingerNum,Mobile_Number) values (?,?,?,?,?)");
			stateOgj.setDate(3,java.sql.Date.valueOf(journeyDate));
			stateOgj.setLong(4, passingernum);
			stateOgj.setString(5,(String) SignInSignOut.userDetails.get(5));
		}else if(journeyDetails.editJ==1) {
			stateOgj=dbOperations.connection.prepareStatement("UPDATE " + dbOperations.planName +" SET Source = ?, Destination = ?, passingerNum = ?  WHERE Mobile_Number = ? and Journeydate = ?");
			stateOgj.setLong(3, passingernum);
			stateOgj.setString(4,(String) SignInSignOut.userDetails.get(5));
			stateOgj.setDate(5,java.sql.Date.valueOf(journeyDate));
		}
		stateOgj.setString(1, source);
		stateOgj.setString(2, destination);
		stateOgj.executeUpdate();
		//		dbOperations.DBop();
	}
}