package com;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;
import com.Utils.GenericUtils;

public class VickyBanking {
	public static void main(String[] args){
		File file = new File("E:\\vinay\\new workplace\\VickyBanking\\VickyBankingLogo.txt");
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
		main();
	}

	public static void main() {
		System.out.println("\n\t\t\t\t\t\tWellcome to VICKY Banking Services");
		Scanner scanner=new Scanner(System.in);
		boolean check=true;
		int menuselect=0;
		while(check) {
			try {
				System.out.println("\n1.New User Registration\n2.Sign-In\n3.Exit");
				menuselect=scanner.nextInt();
				check=false;
			}catch(InputMismatchException exc){
				System.out.println("Please enter a valid number");
			}

			switch(menuselect) {
			case 1:
				GenericUtils.insert();
				System.out.println("Account Created Successfully");
				break;
			case 2:
				GenericUtils.insertAccInfo();
				break;
			case 3:
				System.exit(0);
			}check=true;
		}
	}

}
