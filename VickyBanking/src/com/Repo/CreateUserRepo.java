package com.Repo;

import java.sql.SQLException;
import com.Utils.DBUtils;

public class CreateUserRepo implements UserRepo{
public static void main(String[] args) throws SQLException {
	CreateUserRepo repo=new CreateUserRepo();
	repo.UserRepoCreate();
	repo.UserTransaction();
	repo.UserAccount();
}
	@Override
	public void UserRepoCreate() throws SQLException {
		String columnDetails="user_fname varchar(100), user_lname varchar(100), "
				+ "Gender varchar(30),MobNum varchar(100)UNIQUE KEY,email varchar(150)UNIQUE KEY,AccountNumber bigint not null, hashedPassword varchar(100),"
				+ "saltPassword varbinary(30),halfMobNum_LogIn varchar(30),failedCount int,expirationTimestamp Date,primary key(AccountNumber)";
		String createQuery="create table Vickyusers ("+columnDetails+")";
		DBUtils.connect(createQuery);
		System.out.println("success");
	}

	@Override
	public void UserAccount() throws SQLException {
		String columnDetails="UserName varchar(100)UNIQUE KEY,AccountNumber bigint not null,"
				+ "AvalBalance bigint,LastLogIn varchar(100), foreign key(AccountNumber) references vickyusers(AccountNumber)";
		String createQuery="create table VickyuserAccounts ("+columnDetails+")";
		DBUtils.connect(createQuery);
		System.out.println("success");
	}

	@Override
	public void UserTransaction() throws SQLException {
		String columnDetails="TransactionDate varchar(100),Type varchar(50),Amount int,Status varchar(50),RefNum int,"
				+ "BalanceAfterTransaction int,ReceiverAccNum bigint,SenderAccNum bigint not null,foreign key(SenderAccNum) references vickyusers(AccountNumber)";
		String createQuery="create table VickyuserAccBanking ("+columnDetails+")";
		DBUtils.connect(createQuery);
		System.out.println("success");
	}

}
