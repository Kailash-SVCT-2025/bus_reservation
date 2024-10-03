package project1;
import java.sql.*;
import java.util.ArrayList;

public class ReservationSystem {
	


	    private static final String URL = "jdbc:mysql://localhost:3306/project1";
	    private static final String username = "root"; // MySQL username
	    private static final String password = "987654321"; 

	    public void addBus(Bus bus) {
	        String sql = "INSERT INTO buses(bus_number, total_seats) VALUES(?, ?)";
	        try (Connection conn = DriverManager.getConnection(URL, username, password);
	             PreparedStatement pstmt = conn.prepareStatement(sql)) {
	            pstmt.setString(1, bus.getBusNumber());
	            pstmt.setInt(2, bus.getTotalSeats());
	            pstmt.executeUpdate();
	        } catch (SQLException e) {
	            System.out.println(e.getMessage());
	        }
	    }

	    public ArrayList<Bus> getBuses() {
	        ArrayList<Bus> buses = new ArrayList<>();
	        String sql = "SELECT bus_number, total_seats FROM buses";

	        try (Connection conn = DriverManager.getConnection(URL, username, password);
	             Statement stmt = conn.createStatement();
	             ResultSet rs = stmt.executeQuery(sql)) {
	            while (rs.next()) {
	                buses.add(new Bus(rs.getString("bus_number"), rs.getInt("total_seats")));
	            }
	        } catch (SQLException e) {
	            System.out.println(e.getMessage());
	        }
	        return buses;
	    }

	    public boolean reserveSeat(Bus bus, int seatNumber, passenger passenger) {
	        // Insert passenger into the database
	        String insertPassengerSQL = "INSERT INTO passengers(name, contact_info) VALUES(?, ?)";
	        String insertReservationSQL = "INSERT INTO reservations(bus_id, seat_number, passenger_id) VALUES(?, ?, ?)";
	        int busId = -1;
	        int passengerId = -1;

	        // Get bus ID
	        String getBusIdSQL = "SELECT id FROM buses WHERE bus_number = ?";
	        try (Connection conn = DriverManager.getConnection(URL, username, password);
	             PreparedStatement pstmt = conn.prepareStatement(getBusIdSQL)) {
	            pstmt.setString(1, bus.getBusNumber());
	            ResultSet rs = pstmt.executeQuery();
	            if (rs.next()) {
	                busId = rs.getInt("id");
	            }
	        } catch (SQLException e) {
	            System.out.println(e.getMessage());
	        }

	        // Insert passenger
	        try (Connection conn = DriverManager.getConnection(URL, username, password);
	             PreparedStatement pstmt = conn.prepareStatement(insertPassengerSQL, Statement.RETURN_GENERATED_KEYS)) {
	            pstmt.setString(1, passenger.getName());
	            pstmt.setString(2, passenger.getContactInfo());
	            pstmt.executeUpdate();

	            ResultSet generatedKeys = pstmt.getGeneratedKeys();
	            if (generatedKeys.next()) {
	                passengerId = generatedKeys.getInt(1);
	            }
	        } catch (SQLException e) {
	            System.out.println(e.getMessage());
	            return false; // Failed to reserve
	        }

	        // Reserve the seat
	        try (Connection conn = DriverManager.getConnection(URL, username, password);
	             PreparedStatement pstmt = conn.prepareStatement(insertReservationSQL)) {
	            pstmt.setInt(1, busId);
	            pstmt.setInt(2, seatNumber);
	            pstmt.setInt(3, passengerId);
	            pstmt.executeUpdate();
	            return true;
	        } catch (SQLException e) {
	            System.out.println(e.getMessage());
	            return false; // Failed to reserve
	        }
	    }

	    public boolean cancelReservation(Bus bus, int seatNumber) {
	        String sql = "DELETE FROM reservations WHERE bus_id = (SELECT id FROM buses WHERE bus_number = ?) AND seat_number = ?";
	        try (Connection conn = DriverManager.getConnection(URL, username, password);
	             PreparedStatement pstmt = conn.prepareStatement(sql)) {
	            pstmt.setString(1, bus.getBusNumber());
	            pstmt.setInt(2, seatNumber);
	            int rowsAffected = pstmt.executeUpdate();
	            return rowsAffected > 0; // Return true if a row was deleted
	        } catch (SQLException e) {
	            System.out.println(e.getMessage());
	            return false; // Cancellation failed
	        }
	    
	    }}


