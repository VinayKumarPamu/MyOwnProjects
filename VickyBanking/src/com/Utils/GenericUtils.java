package com.Utils;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import com.Service.DefaultUserService;
import com.Service.TransactionDTO;
import com.Service.UserDTO;

public class GenericUtils {
	public static boolean retriveTest=false;
	public static ArrayList<Object> checkUser=new ArrayList<>();
	// inserting new user into DataBase
	public static void insert() {
		DefaultUserService Service=new DefaultUserService();
		UserDTO createAccount = Service.CreateAccount();
		String substring1 = createAccount.getMobNum().substring(0, 5);
		try {
			DBUtils.connekt();

			PreparedStatement stmt=DBUtils.connection.prepareStatement("insert into vickyusers(user_fname,user_lname,"
					+"Gender,MobNum,email,AccountNumber,hashedPassword,saltPassword,halfMobNum_LogIn)"+
					"values	(?,?,?,?,?,?,?,?,?)");
			stmt.setString(1, createAccount.getUserFirstName());
			stmt.setString(2, createAccount.getUserLastName());
			stmt.setString(3, createAccount.getGender());
			stmt.setString(4, createAccount.getMobNum());
			stmt.setString(5, createAccount.getEmail());
			stmt.setString(6, createAccount.getAccNum());
			stmt.setString(7,createAccount.getHashedPassword());
			stmt.setInt(8,createAccount.getSalt());
			stmt.setString(9, substring1);
			stmt.execute();
			stmt.close();
			DBUtils.connection.close();

			int splitPosition = 5;  // Split at the last 5 characters
			String fName = createAccount.getMobNum().substring(0, splitPosition);
			DBUtils.connekt();
			PreparedStatement accBal=DBUtils.connection.prepareStatement("INSERT INTO vickyuserAccounts (UserName,AccountNumber,AvalBalance) VALUES (?,?,500)");
			accBal.setString(1, createAccount.getUserFirstName()+fName);
			accBal.setString(2, createAccount.getAccNum());
			accBal.execute();
			accBal.close();
			DBUtils.connection.close();
		} catch (SQLException e) {
			System.out.println("Failed to Create Account");
		}
	}
	//to check availability of Already registered user info
	public static boolean retrive(){
		ResultSet result;
		try {
			DBUtils.connekt();
			PreparedStatement stmt=DBUtils.connection.prepareStatement("select * from vickyusers");
			result = stmt.executeQuery();
			while(result.next()) {
				retriveTest=true;
				checkUser.add(result.getString(4));
				checkUser.add(result.getString(5));
				checkUser.add(result.getString(6));
				checkUser.add(result.getString(7));
				checkUser.add(result.getString(8));
				checkUser.add(result.getString(1));
			}
			stmt.close();
			DBUtils.connection.close();
		} catch (SQLException e) {
			System.out.println("No Account Details found");
		}
		return retriveTest;
	}
	//retriving data of user for validation 
	public static ArrayList validateUser(String fName,String halfMobNum){
		ArrayList<Object> validateuser=new ArrayList<>();
		ResultSet result;
		try {
			DBUtils.connekt();
			PreparedStatement stmt=DBUtils.connection.prepareStatement("select * from vickyusers where user_fname=? and halfMobNum_LogIn=?");
			stmt.setString(1, fName);
			stmt.setString(2, halfMobNum);
			result = stmt.executeQuery();
			while(result.next()) {
				DefaultUserService.startDailyCleanup(result.getString(6));//to clean failcount  Will be executed on server when the code is deployed
				validateuser.add(result.getString(4));//Mobile Number
				validateuser.add(result.getString(5));//Email
				validateuser.add(result.getString(6));//AccNum
				validateuser.add(result.getString(7));//HashedPwd
				validateuser.add(result.getString(8));//SaltPwd
				validateuser.add(result.getString(1));//UserName
				validateuser.add(result.getInt(10));
			}
			stmt.close();
			DBUtils.connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("No Account Details found");
		}
		return validateuser;
	}	
	//inserting Last Login of user   
	public static void insertAccInfo(){
		DefaultUserService Service=new DefaultUserService();
		UserDTO createAccount = Service.AuthenticateUser();
		try {
			DBUtils.connekt();
			PreparedStatement stmt=DBUtils.connection.prepareStatement("update vickyuserAccounts set LastLogIn=? where AccountNumber=?");
			stmt.setString(2, createAccount.getAccNum());
			stmt.setString(1, createAccount.getLastLogIn());
			stmt.execute();
			stmt.close();
			DBUtils.connection.close();
		} catch (SQLException e) {
			System.out.println("Account not Found");
		}
	}
	//inserting fail count
	public static void failcount(int failcount, String AccountNum){
		try {
			DBUtils.connekt();
			// Calculate expiration Date for the midnight of the next day
			Date MidnightTimestamp = DefaultUserService.timeManage();
			String sql = "update vickyusers set failedCount=?, expirationTimestamp=? where AccountNumber=?";
			PreparedStatement stmt = DBUtils.connection.prepareStatement(sql);
			stmt.setInt(1, failcount);
			stmt.setDate(2, MidnightTimestamp);
			stmt.setString(3, AccountNum);
			stmt.executeUpdate();
			stmt.close();
			DBUtils.connection.close();
		} catch (SQLException e) {
			System.out.println("No Account Details found");
		}
	}
	///clearing fail count
	public static void cleanupExpiredData(String AccNum){
		try {
			DBUtils.connekt();
			Date currentTimestamp = new Date(System.currentTimeMillis());
			String sql = "update vickyusers set failedCount=0 WHERE AccountNumber=? and expirationTimestamp < ?";
			PreparedStatement stmt = DBUtils.connection.prepareStatement(sql);
			stmt.setString(1, AccNum);
			stmt.setDate(2, currentTimestamp);
			stmt.executeUpdate();
			stmt.close();
			DBUtils.connection.close();
		} catch (SQLException e) {
			System.out.println("No Account Details found");
		}
	}
	//retrive Account Information of user
	public static ArrayList AccOp(String AccNum){
		ArrayList AccOperation=new ArrayList<>();
		try {
			DBUtils.connekt();
			PreparedStatement stmt=DBUtils.connection.prepareStatement("select * from vickyuserAccounts where AccountNumber=?");
			stmt.setString(1, AccNum);
			ResultSet AccOperations = stmt.executeQuery();
			while(AccOperations.next()) {
				AccOperation.add(AccOperations.getString(2));
				AccOperation.add(AccOperations.getString(3));
				AccOperation.add(AccOperations.getString(4));
			}
			stmt.close();
			DBUtils.connection.close();
		} catch (SQLException e) {
			System.out.println("No Account Details found for account number"+AccNum);
		}
		return AccOperation;
	}
	//update Account information
	public static boolean insertAccInfo1(String AccNum, String Amt){
		int status=0;
		try {
			DBUtils.connekt();
			PreparedStatement stmt=DBUtils.connection.prepareStatement("update vickyuserAccounts set AvalBalance=? where AccountNumber=?");
			stmt.setString(2, AccNum);
			stmt.setString(1,Amt);
			status = stmt.executeUpdate();
			stmt.close();
			DBUtils.connection.close();
		} catch (SQLException e) {
			System.out.println("Account Balance Not Updated");
		}
		if(status>0) {
			return true;
		}else {
			return false;
		}
	}
	//Updating Transaction history
	public static void Transactions(TransactionDTO transactions) {
		try {
			DBUtils.connekt();
			PreparedStatement stmt=DBUtils.connection.prepareStatement("insert into VickyuserAccBanking values(?,?,?,?,?,?,?,?)");
			stmt.setString(1, transactions.getTransactionDate());
			stmt.setString(2, transactions.getType());
			stmt.setInt(3, transactions.getTransactionAmount());
			stmt.setString(4, transactions.getStatus());
			stmt.setInt(5, (int) transactions.getRefNum());
			stmt.setString(6, transactions.getBalanceAfterTransaction());
			stmt.setString(7, transactions.getReceiverAccNum());
			stmt.setString(8, transactions.getSenderAccNum());
			stmt.execute();
			stmt.close();
			DBUtils.connection.close();
		} catch (SQLException e) {
			System.out.println("Transaction details not Updated");
		}
	}
	//retriving Transaction history
	public static ResultSet TransactionHistory(String AccNum){
		ResultSet History = null;
		try {
			DBUtils.connekt();
			PreparedStatement stmt=DBUtils.connection.prepareStatement("select * from VickyuserAccBanking where SenderAccNum=?");
			stmt.setString(1, AccNum);
			History = stmt.executeQuery();
			stmt.close();
			DBUtils.connection.close();
		} catch (SQLException e) {
			System.out.println("No transactions are made by You");
		}
		return History;
	}
	// for forgot password
	public static ArrayList forgot(String fname,String halfMobNum,String email){
		ArrayList pwd=new ArrayList<>();
		ResultSet result;
		try {
			DBUtils.connekt();
			PreparedStatement stmt=DBUtils.connection.prepareStatement("select * from vickyusers where user_fname=? and halfMobNum_LogIn=? and email=?");
			stmt.setString(1, fname);
			stmt.setString(2, halfMobNum);
			stmt.setString(3, email);
			result = stmt.executeQuery();
			while(result.next()) {
				pwd.add(result.getString(7));
				pwd.add(result.getString(8));
				pwd.add(result.getString(1));
			}
			stmt.close();
			DBUtils.connection.close();
		} catch (SQLException e) {
			System.out.println("No Account Details found");
		}
		return pwd;
	}
	// inserting new password after forgot operation
	public static boolean insertpwd(String hashedpwd,int salt,String fname,String email) {
		boolean testpwd=false;
		try {
			DBUtils.connekt();
			PreparedStatement stmt=DBUtils.connection.prepareStatement("update vickyusers set hashedPassword=?,saltPassword=? where user_fname=? and email=?");
			stmt.setString(1, hashedpwd);
			stmt.setInt(2, salt);
			stmt.setString(3, fname);
			stmt.setString(4, email);
			int execute = stmt.executeUpdate();
			if(execute>0){
				testpwd=true;
			}
		} catch (SQLException e) {
			System.out.println("No Account Details found");
		}
		return testpwd;
	}
}
