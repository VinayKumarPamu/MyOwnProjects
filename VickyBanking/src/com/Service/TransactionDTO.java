package com.Service;

import java.sql.Date;
import java.time.LocalDate;

public class TransactionDTO {
	String transactionDate;
	String Type;
	int transactionAmount;
	String Status;
	double RefNum;
	String BalanceAfterTransaction;
	String ReceiverAccNum;
	String SenderAccNum;
	public String getTransactionDate() {
		return transactionDate;
	}
	@Override
	public String toString() {
		return "transaction Date=" + transactionDate + ", Type=" + Type + ", Transaction Amount="
				+ transactionAmount + ", Status=" + Status + ", Reference Number=" + RefNum + ", Balance After Transaction="
				+ BalanceAfterTransaction + ", Receiver Account Number=" + ReceiverAccNum + ", Sender Account Number=" + SenderAccNum
				+ "]";
	}
	public void setTransactionDate(String string) {
		this.transactionDate = string;
	}
	public String getType() {
		return Type;
	}
	public void setType(String type) {
		Type = type;
	}
	public int getTransactionAmount() {
		return transactionAmount;
	}
	public void setTransactionAmount(int transactionAmount) {
		this.transactionAmount = transactionAmount;
	}
	public String getStatus() {
		return Status;
	}
	public void setStatus(String status) {
		Status = status;
	}
	public double getRefNum() {
		return RefNum;
	}
	public void setRefNum(double d) {
		RefNum = d;
	}
	public String getBalanceAfterTransaction() {
		return BalanceAfterTransaction;
	}
	public void setBalanceAfterTransaction(String newBal) {
		BalanceAfterTransaction = newBal;
	}
	public String getReceiverAccNum() {
		return ReceiverAccNum;
	}
	public void setReceiverAccNum(String receiverAccNum) {
		ReceiverAccNum = receiverAccNum;
	}
	public String getSenderAccNum() {
		return SenderAccNum;
	}
	public void setSenderAccNum(String senderAccNum) {
		SenderAccNum = senderAccNum;
	}
}
