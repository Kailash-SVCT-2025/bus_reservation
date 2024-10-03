package project1;

public class Reservation {
	
	    private Bus bus;
	    private int seatNumber;
	    private passenger passenger;

	    public Reservation(Bus bus, int seatNumber, passenger passenger) {
	        this.bus = bus;
	        this.seatNumber = seatNumber;
	        this.passenger = passenger;
	    }

	    public Bus getBus() {
	        return bus;
	    }

	    public int getSeatNumber() {
	        return seatNumber;
	    }

	    public passenger getPassenger() {
	        return passenger;
	    }
	}



