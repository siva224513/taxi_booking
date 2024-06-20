package taxi_booking;

import java.util.ArrayList;

public class Taxi {
	static  int taxicount=0;
	int id = 0;
	boolean isBooked;
	int totalEarnings;
	int freeTime;
	char currSpot;
	ArrayList<BookingDetails>bookingDetails;

	public Taxi() {
		isBooked = false;
		currSpot='A';
		totalEarnings=0;
		freeTime=5;
		taxicount++;
		id=taxicount;
		bookingDetails=new ArrayList<BookingDetails>();
	}
	public void setDetails(boolean b, char nextSpot, int earning, int nextFreeTime, BookingDetails currBookingDetails) {
		this.isBooked=b;
		this.currSpot=nextSpot;
		this.totalEarnings=earning;
		this.freeTime=nextFreeTime;
		bookingDetails.add(currBookingDetails);
	}
}
