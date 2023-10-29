package VickyTravels;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class dbOperations {
	public static Connection connection=null;
	public static String db_link="jdbc:mysql://localhost:3306/new";
	public static String Uname="root";
	public static String Pwd="Vinay@7989";
	public static Scanner scanner=new Scanner(System.in);
	public static ResultSet result;
	public static ResultSet result_plan;
	public static String name1=null;
	public static String planName=null;
	public static void DBop() throws SQLException {
		PreparedStatement stateOgj = connection.prepareStatement("select * from "+name1);
		result=stateOgj.executeQuery();
	}
	public static void DBoperations() throws SQLException {
		try {
			String planRoot="Source varchar(100),Destination varchar(100),Journeydate varchar(100) NOT NULL,passingerNum int NOT NULL default 0,"
					+ "Mobile_Number varchar(40),Price float not null default 0,foreign key(Mobile_Number) references LOGIN_CREDINTIALS(Mobile_Number)";
			String numCol="id int NOT NULL AUTO_INCREMENT primary key,fname varchar(50),lname varchar(50),gender varchar(50), "
					+ "eMAIL varchar(400) unique,Mobile_Number varchar(40) unique,PASSWORD varchar(400)";
			String seating="JourneyDate varchar(100) not null,SeatCapacity int";
			name1="LOGIN_CREDINTIALS";
			planName="JOURNEY_DETAILS";
			connection=DriverManager.getConnection(dbOperations.db_link, dbOperations.Uname, dbOperations.Pwd);
//			System.out.println("connected to DB");
			try {
				PreparedStatement stateOgj = connection.prepareStatement("select * from "+name1);
				PreparedStatement stateOgj1=connection.prepareStatement("select * from "+planName);
				result=stateOgj.executeQuery();
				result_plan=stateOgj1.executeQuery();
			}catch(SQLException e) {
			}
			opDDL(planRoot, numCol,seating);
		}catch(SQLException s) {
			System.out.println("Sorry this application is not working\nNot connected to Database");
			System.exit(0);
		}
	}
	public static void opDDL(String planRoot, String numCol,String seating) throws SQLException {
		PreparedStatement stateOgj;
		if(result==null||result_plan==null) {
			stateOgj=connection.prepareStatement("create table "+name1+"("+numCol+")");
			stateOgj.execute();
			stateOgj=connection.prepareStatement("create table "+planName+"("+planRoot+")");
			stateOgj.execute();
			stateOgj=connection.prepareStatement("create table Seating ("+seating+")");
			stateOgj.execute();
		}
	}
//	public static void drop() {
//		PreparedStatement stateOgj;
//		try {
//			stateOgj = connection.prepareStatement("drop table "+name1+","+planName+",Seating;");
//			stateOgj.execute();
//		} catch (SQLException e) {
//		}
//	}
}