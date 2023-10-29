package VickyTravels;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class mainMenu {
	public static int editJ=0;
	public static void jourNey() throws NullPointerException, SQLException {
		ArrayList<Object> bookings=new ArrayList<Object>();
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
				SignInSignOut.Prising();
				SignInSignOut.Seating();
				break;
			case 2:
				editJ=1;
				SignInSignOut.Prising();
				SignInSignOut.Seating();
				break;
			case 3:
				System.out.println("Enter your date of journey to search:\nYYYY\nMM\nDD");
				LocalDate journeyDate = LocalDate.of(scanner.nextInt(), scanner.nextInt(), scanner.nextInt());
				List<journeyDetails> retriveJourney = DBop.retriveJourney(journeyDate.toString());
				System.out.println("SOURCE:\tDESTINATION:\tJOURNEY DATE:\tNUMBER OF PASSINGERS:\tPRICE:");
				for (journeyDetails object : retriveJourney) {
					System.out.println(object.getSource()+"\t"+object.getDestination()+"\t\t"+object.getJourneyDate()+"\t"+object.getPassingerNum()+"\t\t\t"+object.getPrice());
				}
				break;
			case 4:
				SignInSignOut.userArray.clear();
				SignInSignOut.siso();
				break;
			}
			select=0;
			testNum=false;
		}
	}
}
