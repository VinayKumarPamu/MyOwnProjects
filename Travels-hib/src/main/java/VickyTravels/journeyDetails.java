package VickyTravels;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="journeydetails")
public class journeyDetails {
	String src;
	String dstn;
	String journeyDate;
	int passNum;
	@Id
	int id;
	String mobNum;
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
	public String getMobNum() {
		return mobNum;
	}
	public void setMobNum(String mobNum) {
		this.mobNum = mobNum;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price2) {
		this.price = price2;
	}
	public journeyDetails() {
	}
	@Override
	public String toString() {
		return "journeyDetails [ id=" + id + ",source=" + src + ", destination=" + dstn + ", journeyDate=" + journeyDate
				+ ", passingerNum=" + passNum + ", mobNum=" + mobNum + ", price=" + price + "]";
	}
	
}
