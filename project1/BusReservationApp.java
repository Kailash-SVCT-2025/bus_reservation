package project1;
import java.util.ArrayList;
import java.util.Scanner;
public class BusReservationApp {
	

	    public static void main(String[] args) {
	        Scanner scanner = new Scanner(System.in);
	        ReservationSystem reservationSystem = new ReservationSystem();

	        // Adding buses directly to the database
	        reservationSystem.addBus(new Bus("BUS001", 10));
	        reservationSystem.addBus(new Bus("BUS002", 15));
	        reservationSystem.addBus(new Bus("BUS003", 20));

	        while (true) {
	            System.out.println("\n--- Bus Reservation System ---");
	            ArrayList<Bus> buses = reservationSystem.getBuses();
	            for (Bus bus : buses) {
	                System.out.println("Bus Number: " + bus.getBusNumber() + ", Total Seats: " + bus.getTotalSeats());
	            }

	            System.out.print("Enter '1' to reserve a seat or '2' to cancel a reservation (or 'exit' to quit): ");
	            String choice = scanner.nextLine();

	            if (choice.equalsIgnoreCase("exit")) {
	                break;
	            }

	            if (choice.equals("1")) {
	                System.out.print("Enter bus number: ");
	                String busNumber = scanner.nextLine();
	                Bus selectedBus = buses.stream()
	                        .filter(bus -> bus.getBusNumber().equals(busNumber))
	                        .findFirst().orElse(null);
	                
	                if (selectedBus == null) {
	                    System.out.println("Bus not found. Please try again.");
	                    continue;
	                }

	                System.out.print("Enter seat number to reserve: ");
	                int seatNumber = scanner.nextInt();
	                scanner.nextLine(); // Consume the newline

	                System.out.print("Enter passenger name: ");
	                String name = scanner.nextLine();

	                System.out.print("Enter contact info: ");
	                String contactInfo = scanner.nextLine();

	                passenger passenger = new passenger(name, contactInfo);
	                if (reservationSystem.reserveSeat(selectedBus, seatNumber, passenger)) {
	                    System.out.println("Seat " + seatNumber + " reserved successfully for " + name);
	                } else {
	                    System.out.println("Failed to reserve seat " + seatNumber + ". It may already be reserved.");
	                }

	            } else if (choice.equals("2")) {
	                System.out.print("Enter bus number: ");
	                String busNumber = scanner.nextLine();
	                Bus selectedBus = buses.stream()
	                        .filter(bus -> bus.getBusNumber().equals(busNumber))
	                        .findFirst().orElse(null);
	                
	                if (selectedBus == null) {
	                    System.out.println("Bus not found. Please try again.");
	                    continue;
	                }

	                System.out.print("Enter seat number to cancel: ");
	                int seatNumber = scanner.nextInt();
	                scanner.nextLine(); // Consume the newline

	                if (reservationSystem.cancelReservation(selectedBus, seatNumber)) {
	                    System.out.println("Reservation for seat " + seatNumber + " canceled successfully.");
	                } else {
	                    System.out.println("No reservation found for seat " + seatNumber + ".");
	                }
	            } else {
	                System.out.println("Invalid choice. Please try again.");
	            }
	        }

	        System.out.println("Thank you for using the Bus Reservation System!");
	        scanner.close();
	    }
	}


