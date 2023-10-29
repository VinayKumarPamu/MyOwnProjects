package VickyTravels;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;

public class logo {
	public static void main(String[] args) throws SQLException {
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
			System.out.println("\n");
		} catch (IOException e) {
		} catch (NullPointerException e1) {
			System.exit(0);
		}
		DBop.dbop();
		SignInSignOut.siso();
	}

}
