package taxi_booking;

public class BookingDetails {
	int cusId;
	char pickPoint;
	char dropPoint;
	int pickTime;
	int dropTime;
	int earning;
	public BookingDetails(int cusId, char pickPoint, char dropPoint, int pickTime, int dropTime, int earning) {
		
		this.cusId = cusId;
		this.pickPoint = pickPoint;
		this.dropPoint = dropPoint;
		this.pickTime = pickTime;
		this.dropTime = dropTime;
		this.earning = earning;
	}

	

}
