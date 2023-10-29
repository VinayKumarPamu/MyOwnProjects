package com.VickyTravels;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Journey {
	static String source;
	static String destination;
	static LocalDate journeyDate;
	static int passingernum;
	static Scanner scanner = new Scanner(System.in);

	public static double Prising() {
		double price = 1500;// Cost of Journey Generation //Assumption
		ArrayList date = Journey.journey();
		DayOfWeek day = ((LocalDate) date.get(2)).getDayOfWeek();
		String dayName = day.toString();
		if (dayName.equalsIgnoreCase("Satureday") || dayName.equalsIgnoreCase("Sunday")) {
			price = price + (200 + 200 * 0.18);
		}
		return price;
	}

	public static int Seating() {
		ArrayList date = Journey.journey();
		final int BusSeatingCount = 56;
		int availSeats = 0;
		int passingerNum = (int) date.get(3);
		availSeats = BusSeatingCount - passingerNum;
		return availSeats;
	}

	public static void journeyDetails() throws NullPointerException { // Arraylist of Journey Details
		System.out.println("Enter source of your journey");
		source = scanner.next();
		System.out.println("Enter destination of your journey");
		destination = scanner.next();
		System.out.println("Enter date of journey in the formate of \nYYYY\nMM\nDD");
		journeyDate = LocalDate.of(scanner.nextInt(), scanner.nextInt(), scanner.nextInt());
		System.out.println("Enter passingers count");
		passingernum = scanner.nextInt();
	}

	public static ArrayList<Object> journey() {
		ArrayList<Object> journey = new ArrayList<>();
		journey.add(0, source);
		journey.add(1, destination);
		journey.add(2, journeyDate);
		journey.add(3, passingernum);
		return journey;
	}
}
