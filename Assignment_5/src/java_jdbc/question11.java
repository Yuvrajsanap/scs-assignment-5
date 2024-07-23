package java_jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.sql.Statement;

public class question11 {
	public static void main(String[] args) {
		// Database URL
		String jdbcUrl = "jdbc:mysql://localhost:3306/testdb";
		// Database credentials
		String username = "root";
		String password = "Yuvraj@12345";

		Connection connection = null;
		Statement statement = null;
		Savepoint savepoint = null;

		try {
			// Load the JDBC driver
			Class.forName("com.mysql.cj.jdbc.Driver");

			// Establish a connection to the database
			connection = DriverManager.getConnection(jdbcUrl, username, password);

			// Disable auto-commit mode
			connection.setAutoCommit(false);

			// Create a Statement object
			statement = connection.createStatement();

			// Start the transaction
			System.out.println("Starting transaction...");

			// Execute multiple SQL statements
			statement.executeUpdate(
					"INSERT INTO assjdbc11 (name, email) VALUES ('Alice Johnson', 'alice.johnson@example.com')");
			statement
					.executeUpdate("INSERT INTO assjdbc11 (name, email) VALUES ('Bob Brown', 'bob.brown@example.com')");

			// Set a savepoint
			savepoint = connection.setSavepoint("Savepoint1");

			// Execute more SQL statements
			statement.executeUpdate(
					"INSERT INTO assjdbc11 (name, email) VALUES ('Charlie Green', 'charlie.green@example.com')");

			// Simulate a condition where rollback is required
			boolean conditionToRollback = true;
			if (conditionToRollback) {
				System.out.println("Rolling back to savepoint...");
				connection.rollback(savepoint); // Roll back to the savepoint
			} else {
				System.out.println("Committing transaction...");
				connection.commit(); // Commit the transaction
			}

		} catch (ClassNotFoundException e) {
			System.err.println("JDBC Driver not found.");
			e.printStackTrace();
		} catch (SQLException e) {
			System.err.println("SQL error occurred.");
			System.err.println("Error Code: " + e.getErrorCode());
			System.err.println("SQL State: " + e.getSQLState());
			System.err.println("Message: " + e.getMessage());
			e.printStackTrace();
			try {
				if (connection != null) {
					System.out.println("Rolling back transaction due to error...");
					connection.rollback(); // Roll back the transaction on error
				}
			} catch (SQLException rollbackEx) {
				System.err.println("Error rolling back transaction.");
				rollbackEx.printStackTrace();
			}
		} finally {
			// Clean up resources
			try {
				if (statement != null)
					statement.close();
				if (connection != null) {
					connection.setAutoCommit(true); // Restore auto-commit mode
					connection.close();
				}
			} catch (SQLException e) {
				System.err.println("Error closing resources.");
				e.printStackTrace();
			}
		}
	}
}
