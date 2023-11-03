package com.entity;


public class Journey {

	String src;
	String dstn;
	String journeyDate;
	int passNum;
	int id;
	float price;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getSource() {
		return src;
	}
	public void setSource(String source) {
		this.src = source;
	}
	public String getDestination() {
		return dstn;
	}
	public void setDestination(String destination) {
		this.dstn = destination;
	}
	public String getJourneyDate() {
		return journeyDate;
	}
	public void setJourneyDate(String journeyDate) {
		this.journeyDate = journeyDate;
	}
	public int getPassingerNum() {
		return passNum;
	}
	public void setPassingerNum(int passingerNum) {
		this.passNum = passingerNum;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price2) {
		this.price = price2;
	}
	public Journey() {
	}
	@Override
	public String toString() {
		return "journeyDetails [ id=" + id + ",source=" + src + ", destination=" + dstn + ", journeyDate=" + journeyDate
				+ ", passingerNum=" + passNum + ", price=" + price + "]";
	}
	public Journey(String src, String dstn, String journeyDate, int passNum) {
		super();
		this.src = src;
		this.dstn = dstn;
		this.journeyDate = journeyDate;
		this.passNum = passNum;
	}
	public Journey(String src, String dstn, String journeyDate, int passNum, float price) {
		super();
		this.src = src;
		this.dstn = dstn;
		this.journeyDate = journeyDate;
		this.passNum = passNum;
		this.price = price;
	}
	public Journey(String src, String dstn, String journeyDate, int passNum, int id, float price) {
		super();
		this.src = src;
		this.dstn = dstn;
		this.journeyDate = journeyDate;
		this.passNum = passNum;
		this.id = id;
		this.price = price;
	}
	public Journey(String source, String destination, int passNum2) {
		super();
		this.src = source;
		this.dstn = destination;
		this.passNum = passNum2;
	}
	

}
