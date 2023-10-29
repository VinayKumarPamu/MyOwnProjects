package VickyTravels;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class journeyDetails {
	public static int editJ=0;
	public static void jourNey() throws NullPointerException, SQLException {
		ArrayList<Object> bookings=new ArrayList<>();
		Scanner scanner=new Scanner(System.in);
		int select=0;
		boolean testNum=false;
		while(select<4) {
			while(!testNum) {
				try {
					System.out.println(" Enter your selection number:\n1.Plan your journey\n2.Edit planed journey\n3.Booking and Payment History\n4.Log-Out");
					select=scanner.nextInt();
					testNum=true;
				}catch (InputMismatchException e) {
					System.out.println("Please enter correctly");
					scanner.nextLine();
					testNum=false;
				}
			}
			switch(select) {
			case 1:
				journey.journeyDetails();
				journey.Seating();
				journey.Prising();
				break;
			case 2:
				editJ=1;
				journey.journeyDetails();
				journey.availSeats=0;
				journey.Seating();
				journey.Prising();
				break;
			case 3:
				PreparedStatement stateOgj2 = dbOperations. connection.prepareStatement("select * from JOURNEY_DETAILS where  Mobile_Number = ?");
				stateOgj2.setString(1, (String) SignInSignOut.userDetails.get(5));
				ResultSet res=stateOgj2.executeQuery();
				System.out.println("SOURCE:\tDESTINATION:\tJOURNEY DATE:\tNUMBER OF PASSINGERS:\tPRICE:");
				while(res.next()) {
					bookings.add(res.getString(1));
					bookings.add(res.getString(2));
					bookings.add(res.getString(3));
					bookings.add(res.getString(4));
					bookings.add(res.getString(6));
					System.out.println(bookings.get(0)+"\t"+bookings.get(1)+"\t\t"+bookings.get(2)+"\t"+bookings.get(3)+"\t\t\t"+bookings.get(4));
				}
				break;
			case 4:
				SignInSignOut.userDetails.clear();
				SignInSignOut.siso();
				break;
			}
			select=0;
			testNum=false;
		}
	}
}