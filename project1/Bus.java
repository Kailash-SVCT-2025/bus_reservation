package project1;

public class Bus {
    private String busNumber;
    private int totalSeats;

    public Bus(String busNumber, int totalSeats) {
        this.busNumber = busNumber;
        this.totalSeats = totalSeats;
    }

    public String getBusNumber() {
        return busNumber;
    }

    public int getTotalSeats() {
        return totalSeats;
    }

    @Override
    public String toString() {
        return "Bus{" +
                "busNumber='" + busNumber + '\'' +
                ", totalSeats=" + totalSeats +
                '}';
    }
}
