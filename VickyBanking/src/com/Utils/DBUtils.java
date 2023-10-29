package com.Utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DBUtils {
	static Connection connection=null;
	public static void connekt() throws SQLException {
		connections connectDb=new connections();
		connection=DriverManager.getConnection(connectDb.db_link,connectDb.Uname,connectDb.Pwd);
	}
	public static void connect(String Query) throws SQLException  {
		connekt();
		PreparedStatement stmt=connection.prepareStatement(Query);
		stmt.execute();
		connection.close();
	}
}