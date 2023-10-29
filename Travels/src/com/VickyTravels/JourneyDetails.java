package com.VickyTravels;

import java.util.ArrayList;

public class JourneyDetails extends Journey {
	static SignInSignUp sign = new SignInSignUp();
	public static void main(String[] args) {
		ticket();
		SignInSignUp.recalling();
	}
	public static void ticket() {
		System.out.println("Passinger Name:\t" + sign.menu.get(0) + "\t" + sign.menu.get(1));
		System.out.println("Gender:\t" + sign.menu.get(3));
		ArrayList<?> date = Journey.journey();
		System.out.println("PickUp:\t" + date.get(0) + ",\tDrop at:\t" + date.get(1));
		System.out.println("Journey date:\t" + date.get(2));
		System.out.println("Number of Passingers are:\t" + date.get(3));
		System.out.println("-------------------------------");
		System.out.println("                                " + Journey.Prising() + "/-");
		System.out.println("-------------------------------");
		System.out.println("Email Id:\t" + sign.menu.get(4));
		System.out.println("Mobile number:\t" + sign.menu.get(2));
		System.out.println("-------------------------------");
		System.out.println("Thank you. Have a Nice Journey!!!");
	}
}
