package project1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class atabaseSetup {

	
	
	    private static final String url = "jdbc:mysql://localhost:3306/project1";
	    private static final String username = "root";
	    private static final String password = "987654321";

	    public static void main(String[] args) {
	        
	        String createBusesTable = "CREATE TABLE IF NOT EXISTS buses (" +
	                "id INT AUTO_INCREMENT PRIMARY KEY," + 
	                "bus_number VARCHAR(255) NOT NULL UNIQUE," + 
	                "total_seats INT NOT NULL);";

	        String createPassengersTable = "CREATE TABLE IF NOT EXISTS passengers (" +
	                "id INT AUTO_INCREMENT PRIMARY KEY," + 
	                "name VARCHAR(255) NOT NULL," +
	                "contact_info VARCHAR(255) NOT NULL);";

	        String createReservationsTable = "CREATE TABLE IF NOT EXISTS reservations (" +
	                "id INT AUTO_INCREMENT PRIMARY KEY," + 
	                "bus_id INT NOT NULL," +
	                "seat_number INT NOT NULL," +
	                "passenger_id INT NOT NULL," +
	                "FOREIGN KEY (bus_id) REFERENCES buses (id) ON DELETE CASCADE," + 
	                "FOREIGN KEY (passenger_id) REFERENCES passengers (id) ON DELETE CASCADE," +
	                "UNIQUE(bus_id, seat_number));";

	        
	        try (Connection conn = DriverManager.getConnection(url, username, password);
	             Statement stmt = conn.createStatement()) {
	            stmt.execute(createBusesTable);
	            stmt.execute(createPassengersTable);
	            stmt.execute(createReservationsTable);
	            System.out.println("Tables created successfully.");
	        } catch (Exception e) {
	            System.out.println("Error: " + e.getMessage());
	        }
	    }
	}

