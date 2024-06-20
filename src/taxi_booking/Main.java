package taxi_booking;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		ArrayList<Taxi> taxiList = createTaxi(4);

		Scanner scanner = new Scanner(System.in);
		boolean exit = false;
		while (!exit) {
			System.out.println("1.Call Taxi Booking\n2.Booking Details\n3.Exit");
			int input = scanner.nextInt();
			switch (input) {
			case 1:
				System.out.println("Customer id:");
				int cusId = scanner.nextInt();
				System.out.println("PickUp point:");
				char pickPoint = scanner.next().charAt(0);
				System.out.println("Drop point:");
				char dropPoint = scanner.next().charAt(0);
				System.out.println("Pickup time:");
				int pickTime = scanner.nextInt();

				ArrayList<Taxi> freeTaxis = getFreeTaxis(taxiList, pickPoint, pickTime);
				for (Taxi taxi : freeTaxis) {
		            System.out.println("Taxi "+taxi.id);
		        }
				Collections.sort(freeTaxis, new Mycomparator());
				bookTaxi(cusId, pickPoint, dropPoint, pickTime, freeTaxis);

				break;
			case 2:
				break;
			case 3:
				System.out.println("exit...");
				exit = true;

			}

		}
	}

	private static void bookTaxi(int cusId, char pickPoint, char dropPoint, int pickTime, ArrayList<Taxi> freeTaxis) {
		int minDistance = Integer.MAX_VALUE;
		Taxi bookedTaxi = null;
		int earning = 0;
		int nextFreeTime = 0;
		int distanceBetweenPickupAndDrop = 0;
		char nextSpot = 'Z';
		BookingDetails bookingDetails = null;
		for (Taxi t : freeTaxis) {
			int distanceBetweenCustomerAndTaxi = Math.abs((pickPoint - 'A') - (t.currSpot - 'A')) * 15;
			if (distanceBetweenCustomerAndTaxi < minDistance) {
				bookedTaxi = t;
				distanceBetweenPickupAndDrop = Math.abs((dropPoint - 'A') - (pickPoint - 'A')) * 15;
				earning = (distanceBetweenPickupAndDrop - 5) * 10 + 100;
				int dropTime = pickTime + Math.abs((pickPoint - 'A') - (dropPoint - 'A'));
				nextFreeTime = dropTime;
				nextSpot = dropPoint;
				bookingDetails = new BookingDetails(cusId, pickPoint, dropPoint, pickTime, dropTime, earning);
				minDistance = distanceBetweenCustomerAndTaxi;
			}
		}
		bookedTaxi.setDetails(true, nextSpot, bookedTaxi.totalEarnings + earning, nextFreeTime, bookingDetails);
		System.out.println("Taxi " + bookedTaxi.id + " booked");
	}

	private static ArrayList<Taxi> createTaxi(int n) {
		ArrayList<Taxi> taxiList = new ArrayList<Taxi>();

		for (int i = 0; i < n; i++) {
			Taxi taxi = new Taxi();
			taxiList.add(taxi);
		}

		return taxiList;
	}

	private static ArrayList<Taxi> getFreeTaxis(ArrayList<Taxi> taxiList, char pickPoint, int pickTime) {
		ArrayList<Taxi> freeTaxis = new ArrayList<Taxi>();
		for (Taxi t : taxiList) {
			if (t.freeTime <= pickTime && Math.abs((t.currSpot - '0') - (pickPoint - '0')) <= pickTime - t.freeTime) {
				freeTaxis.add(t);
			}
		}

		return freeTaxis;
	}

}

class Mycomparator implements Comparator<Taxi> {

	@Override
	public int compare(Taxi a, Taxi b) {

		return a.totalEarnings - b.totalEarnings;
	}

}
