package com.Service;

import java.io.IOException;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Objects;
import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import com.VickyBanking;
import com.Utils.GenericUtils;

public class DefaultUserService implements UserService{
	Scanner scanner=new Scanner(System.in);
	//SignUp
	@Override
	public UserDTO CreateAccount() {
		UserDTO userDT = new UserDTO() ;
		boolean retrive=GenericUtils.retrive();
		ArrayList checkUser= GenericUtils.checkUser;
		System.out.println("Enter your first name");
		userDT.setUserFirstName(scanner.next());
		System.out.println("Enter your last name");
		userDT.setUserLastName(scanner.next());
		System.out.println("Enter your Email");
		boolean valid=false;
		while(!valid) {
			String email=scanner.next();//*********EMAIL Validation
			if(email.contains("@")&&email.contains(".")&&email.indexOf(".")>email.indexOf("@")+2) {
				if(retrive==true) {
					if(!checkUser.get(1).equals(email)) {/// Checking if any user having same email 
						userDT.setEmail(email);
						valid=true;
					}else {
						System.out.println("Email already in USE, Please Login into your account / enter a valid email.");
					}
				}
				else {
					userDT.setEmail(email);
					valid=true;
				}
			}else {
				System.out.println("Please enter a valid email.");
			}
		}
		System.out.println("Enter your mobile number");
		while(valid) {
			String mobilenum=scanner.next();
			if (mobilenum.length() == 10) {
				if(retrive==true) {// Mobile number validation
					if(!checkUser.get(0).equals(mobilenum)) {
						userDT.setMobNum(mobilenum);
						valid=false;
					}else {
						System.out.println("Mobile Number is already Linked with an Account");
					}
				}else {
					userDT.setMobNum(mobilenum);
					valid=false;
				}
			} else {
				System.out.println("Wrong Mobile Number");
				System.out.println("Enter 10 digits of your mobile number");
			}
		}
		System.out.println("Enter your Gender");
		userDT.setGender(scanner.next());
		System.out.println("Enter your password");
		int salt  ;
		while(!valid) {
			salt = (int) (Math.random() * 10);
			String password=scanner.next();
			if (isValidPassword(password)) {// Password Validation and hashing
				int hashedPassword=passwordHash(password, salt);
				userDT.setHashedPassword(""+hashedPassword);
				userDT.setSalt(salt);
				valid=true;
			} else {
				System.out.println("Password is not valid.");
			}
		}
		double x =0;//   ACCOUNT NUMBER GENERATION
		System.out.println("Initial deposite of 500 into Account to create your account."
				+ "\nWhould you like to continue to create your Account?\n1.Yes\n2.No");
		if(scanner.nextInt()==1) {
			boolean negativeCheck=true;
			while(negativeCheck) {
				try {
					int randomInt = (int) (Math.random() * 1000000);
					x = randomInt*10000;
					if(x<0) {
						throw new IOException();
					}
					if(retrive==true) {
						if(!checkUser.get(2).equals(x)) {
							userDT.setAccNum(""+x);
							negativeCheck=false;
						}else {
							negativeCheck=true;
						}
					}
					else {
						userDT.setAccNum(""+x);
						negativeCheck=false;
					}
				}catch(IOException negative) {
					negativeCheck=true;
				}
			}
		}else {
			System.out.println("Thank you");
			System.exit(0);
		}
		return userDT;
	}
	//login
	@Override
	public UserDTO AuthenticateUser() {
		timeManage();
		int failcount=1;
		boolean failcheck=true;
		UserDTO user=new UserDTO();
		while(failcheck==true) {
			System.out.println("Enter your user id\n(your first name and starting 5 digits of your mobile number Ex.. FirstName12345)");
			user.setUserFirstName(scanner.next());
			int length = user.getUserFirstName().length();
			int splitPosition = length - 5;  // Split at the last 5 characters
			String fName = user.getUserFirstName().substring(0, splitPosition);
			String halfMobNum = user.getUserFirstName().substring(splitPosition);
			// CHECK ON CHAT-GPT HOW TO GET DATA USING FOREIGN KEY to remove halfMobNum_LogIn, using FK get data of vickyusers using USERNAME from vickyuserAccounts
			ArrayList checkUser = null;
			checkUser=GenericUtils.validateUser(fName,halfMobNum);
			if(checkUser.isEmpty()) {
				System.out.println("User not Found");
			}else {
				failcheck=false;
				int failedcount = (int) checkUser.get(6);
				if(failedcount>=3) {
					System.out.println("You are not Allowed to login for today");
					VickyBanking.main();
				}else {
					while(!failcheck) {
						boolean pwd=false;
						int selekt=0;
						while(!pwd) {
							try {
								System.out.println("Select one choice\n1. Enter Your Password\n2. Forgot Your Password");
								selekt=scanner.nextInt();
								if(selekt>2) {
									throw new InputMismatchException();						
								}
							}catch(InputMismatchException inputMissmatch) {
								System.out.println("Please enter 1 or 2 number");
							}

							switch(selekt) {
							case 1:
								//Login password
								System.out.println("Enter Your Password");
								String password=scanner.next();
								int salt  =Integer.parseInt((String) checkUser.get(4));
								String hashpwd=(String) checkUser.get(3);
								int hashedPassword=passwordHash(password, salt);
								if(hashpwd.equals(""+hashedPassword)&&fName.equals(checkUser.get(5))) {
									user.AccNum=(String) checkUser.get(2);
									user.lastLogIn= lastLog();
									System.out.println("LogIn successful");
									//Banking route								-------------------------------
									Banking(user.AccNum);
									failcheck=true;
								}else {/// Fail Count
									GenericUtils.failcount(failcount, (String) checkUser.get(2));// fail count storing in db
									failcount++;				
									if(failcount>3) {
										System.out.println("You have failed to login into your account.\nYour Account "+checkUser.get(2)+" is locked for Today, Better Luck Next Time.:-)");
										failcheck=true;
										VickyBanking.main();
									}else {
										System.out.println("Invalid Login Credintials. You have "+(4-failcount)+" attempts\nTry again");
									}
								}
								pwd=true;
								break;
							case 2:
								//Forgot pwd
								System.out.println("Enter your UserName and Email");
								String Uname=scanner.next();
								String email=scanner.next();
								int len = user.getUserFirstName().length();
								int splitNum = len - 5;  // Split at the last 5 characters
								String firstName = Uname.substring(0, splitNum);
								String halfmobNum = Uname.substring(splitNum);
								ArrayList forgot = GenericUtils.forgot(firstName, halfmobNum, email);
								if(forgot.isEmpty()) {
									System.out.println("Verification of User is Failed");
									VickyBanking.main();
								}else {
									while(!pwd) {
										System.out.println("Enter your new Password");
										String newPwd=scanner.next();
										int Salt  =Integer.parseInt((String) forgot.get(1));
										String hashPwd=(String) forgot.get(0);
										int HashedPassword=passwordHash(newPwd, Salt);
										if(hashPwd.equals(""+HashedPassword)) {
											System.out.println("Entered password do not match any of previous passwords.");
										}else {
											pwd=true;
											int slt = (int) (Math.random() * 10);
											if (isValidPassword(newPwd)) {
												HashedPassword=passwordHash(newPwd, slt);
												boolean insertpwd = GenericUtils.insertpwd(""+HashedPassword, slt, firstName, email);
												if(insertpwd==true) {
													System.out.println("Your new Password updated Successfully");
													Banking(user.AccNum);
												}
											} else {
												System.out.println("Password is not valid.");
												VickyBanking.main();
											}
										}
									}
								}
							}
						}
					}
					failcheck=false;
				}
			}
		}
		return user;
	}
	//Password Validations
	public static boolean isValidPassword(String password) {
		if (password.length() < 8) {
			return false;
		}
		boolean hasLetter = false;
		boolean hasDigit = false;
		for (char c : password.toCharArray()) {
			if (Character.isLetter(c)) {
				hasLetter = true;
			} else if (Character.isDigit(c)) {
				hasDigit = true;
			}
		}
		return hasLetter && hasDigit;
	}
	// Password Hashing-salt method
	private static int passwordHash(String password,int salt) {
		int hashedPassword=0;
		hashedPassword = Objects.hash(password, salt);
		return hashedPassword;
	}
	//implementation of last login
	private static String lastLog() {
		String date = LocalDateTime.now().toString();
		String lastLogInDate=date.substring(0, 10)+" Time:"+date.substring(11, 16);
		return lastLogInDate;
	}
	//to get date for FailCount
	public static Date timeManage() {
		LocalDate currentDate = LocalDate.now();
		LocalDate midnightOfNextDay = currentDate.atStartOfDay().toLocalDate();
		return Date.valueOf(midnightOfNextDay);
	}
	//Banking
	@Override
	public void Banking(String accNum) {
		ArrayList accOp = null;
		boolean bankingLoop=false;
		int select=0;
		Scanner scanner=new Scanner(System.in);
		while(!bankingLoop) {
			accOp= GenericUtils.AccOp(accNum);
			System.out.println("Select your service");
			try {
				System.out.println("1.Balance Enquiry\n2.Deposite Money\n"
						+ "3.Withdraw Money\n4.Transfer Money\n5.Transaction History\n6.LogOut");
				select = scanner.nextInt();
			}catch(InputMismatchException missmatch) {
				System.out.println("Please enter a valid number");
			}
			switch(select) {
			case 1:
				balanceCheck(accOp);
				break;
			case 2:
				try {
					DepositeMoney(accOp);
				} catch (SQLException e) {}
				break;
			case 3:
				try {
					WithdrawMoney(accOp);
				} catch (SQLException e) {
				}
				break;
			case 4:
				try {
					TransferMoney(accOp);
				} catch (SQLException e) {
				}
				break;
			case 5:
				try {
					TransactionHistory(accOp);
				} catch (SQLException e) {
				}
				break;
			case 6:
				accOp.clear();
				VickyBanking.main(null);
				break;
			}
		}
	}
	private void balanceCheck(ArrayList accOp)  {
		System.out.println("Your Account Balance is Rs."+accOp.get(1)+" at "+LocalDate.now());
	}
	private void DepositeMoney(ArrayList accOp) throws SQLException {
		//for vickyuserAccounts DB Operations
		TransactionDTO transaction=new TransactionDTO();
		System.out.println("Enter the Amount you want to Deposite into Account "+accOp.get(0));
		int dipAmt = scanner.nextInt();
		String TransString="Amount "+dipAmt+" is deposited in your account.";
		Transactions(accOp,"Deposite",TransString,null,dipAmt);
	}
	private void WithdrawMoney(ArrayList accOp) throws SQLException {
		System.out.println("Enter the Amount you want to Withdraw from Account "+accOp.get(0));
		int WitAmt = scanner.nextInt();
		String TransString="Amount "+WitAmt+" is Withdrawn from your account.";
		Transactions(accOp,"Withdraw",TransString,null,WitAmt);
	}
	private void TransferMoney(ArrayList accOp) throws SQLException {
		System.out.println("Enter the Amount you want to Transfer from Account "+accOp.get(0));
		int TransAmt = scanner.nextInt();
		System.out.println("Enter the Receiver Account Number");
		int receiverAccNum=scanner.nextInt();
		String TransString="Amount "+TransAmt+" is Transfered from your account to "+receiverAccNum;
		Transactions(accOp,"Transfer",TransString,""+receiverAccNum,TransAmt);
	}
	protected void Transactions(ArrayList accOp,String type,String TransString,String receiverAccNum,int Amt) {
		//for vickyuserAccounts DB Operations
		TransactionDTO transaction=new TransactionDTO();
		int amount=0;
		if(type.equalsIgnoreCase("Withdraw")||type.equalsIgnoreCase("Transfer")) {
			amount=Integer.parseInt((String) accOp.get(1))-Amt;
		}else {
			amount=Integer.parseInt((String) accOp.get(1))+Amt;
		}
		String newBal=String.valueOf(amount);
		boolean Status=GenericUtils.insertAccInfo1((String) accOp.get(0),newBal);
		// for VickyuserAccBanking DB Operations
		transaction.setTransactionDate(lastLog());
		transaction.setBalanceAfterTransaction(newBal);
		transaction.setReceiverAccNum(null);
		transaction.setRefNum(Math.random() * 1000000);
		transaction.setSenderAccNum((String) accOp.get(0));
		if(Status==true) {
			transaction.setStatus("Successful");
			System.out.println(TransString+".\nThe updated Account Balance is "+newBal);
		}else {
			transaction.setStatus("Failed");
		}
		transaction.setTransactionAmount(Amt);
		transaction.setType(type);
		GenericUtils.Transactions(transaction);
	}
	private void TransactionHistory(ArrayList accOp) throws SQLException {
		System.out.println("TransactionDate\t\tType\tTransactionAmount\tStatus\t\tRefNum\tAccountBalance\tReceiverAccNum\tSenderAccNum");
		List<Map<String,Object>> transactionHistory = GenericUtils.TransactionHistory((String) accOp.get(0));
		if(!transactionHistory.isEmpty()) {
			System.out.println("hello");
		}
		for(int num=0;num<transactionHistory.size();num++){
			System.out.println(transactionHistory.get(num).get("TransactionDate")+"\t"+transactionHistory.get(num).get("Type")
					+transactionHistory.get(num).get("Amount")+"\t\t\t"+transactionHistory.get(num).get("Status")+"\t"
					+transactionHistory.get(num).get("RefNum")+"\t"+transactionHistory.get(num).get("BalanceAfterTransaction")+
					"\t\t"+transactionHistory.get(num).get("ReceiverAccNum")+"\t\t"+transactionHistory.get(num).get("SenderAccNum"));
		}
	}
	// Cleaning of failed count
	public static void startDailyCleanup(String AccNum) {
		// Get the current time and calculate the time until midnight
		LocalDateTime now = LocalDateTime.now();
		LocalDateTime midnight = now.toLocalDate().atTime(LocalTime.MAX);
		Duration durationUntilMidnight = Duration.between(now, midnight);
		// Calculate the initial delay until midnight
		long initialDelay = durationUntilMidnight.getSeconds();
		ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
		// Schedule the cleanup task to run every 24 hours (adjust as needed)
		scheduler.scheduleAtFixedRate(() -> {
			GenericUtils.cleanupExpiredData(AccNum);
		}, initialDelay, TimeUnit.DAYS.toSeconds(1), TimeUnit.SECONDS);  // Run every 24 hours
	}
}
