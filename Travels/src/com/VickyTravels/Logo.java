package com.VickyTravels;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Logo {
	public Logo() throws NullPointerException {
		File file = new File("E:\\vinay\\JAVA Project\\Logo.txt");
		FileInputStream f = null;
		try {
			f = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			System.out.println("flie not found");
			System.out.println("Try again!");
		}
		int i;
		try {
			while ((i = f.read()) != -1) {
				System.out.print((char) i);
			}
		} catch (IOException e) {
		} catch (NullPointerException e1) {
			System.exit(0);
		}
	}
}
