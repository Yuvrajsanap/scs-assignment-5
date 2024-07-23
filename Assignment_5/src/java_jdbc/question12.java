package java_jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class question12 {
	public static void main(String[] args) {
		// Database URL
		String jdbcUrl = "jdbc:mysql://localhost:3306/testdb";
		// Database credentials
		String username = "root";
		String password = "Yuvraj@12345";

		Connection connection = null;
		Statement statement = null;

		try {
			// Load the JDBC driver
			Class.forName("com.mysql.cj.jdbc.Driver");

			// Establish a connection to the database
			connection = DriverManager.getConnection(jdbcUrl, username, password);

			// Disable auto-commit mode
			connection.setAutoCommit(false);

			// Create a Statement object
			statement = connection.createStatement();

			// Add SQL statements to the batch
			statement.addBatch("INSERT INTO assjdbc12 (name, email) VALUES ('yuvraj', 'yuvraj@example.com')");
			statement.addBatch("INSERT INTO assjdbc12 (name, email) VALUES ('aniket', 'aniket@example.com')");
			statement.addBatch("INSERT INTO assjdbc12 (name, email) VALUES ('mayur', 'mayur@example.com')");
			statement.addBatch("INSERT INTO assjdbc12 (name, email) VALUES ('yash', 'yash@example.com')");

			// Execute the batch
			int[] updateCounts = statement.executeBatch();

			// Commit the transaction
			connection.commit();
			System.out.println("Batch update successful.");

			// Print the update counts
			for (int count : updateCounts) {
				System.out.println("Rows affected: " + count);
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
