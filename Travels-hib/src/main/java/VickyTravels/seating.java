package VickyTravels;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class seating {
	@Id
	String JourneyDate;
	int seatCapacity;
	public String getJourneyDate() {
		return JourneyDate;
	}
	public void setJourneyDate(Object object) {
		JourneyDate = (String) object;
	}
	public int getSeatCapacity() {
		return seatCapacity;
	}
	public void setSeatCapacity(int seatInt) {
		this.seatCapacity = seatInt;
	}
	@Override
	public String toString() {
		return "Seating [JourneyDate=" + JourneyDate + ", seatCapacity=" + seatCapacity + "]";
	}
	public seating() {
	}
}
