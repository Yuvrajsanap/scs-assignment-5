package java_jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class question8 {
	public static void main(String[] args) {
		// Database URL
		String jdbcUrl = "jdbc:mysql://localhost:3306/testdb";
		// Database credentials
		String username = "root";
		String password = "Yuvraj@12345";

		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;

		try {
			// Step 1: Load the JDBC driver
			Class.forName("com.mysql.cj.jdbc.Driver");

			// Step 2: Establish a connection to the database
			connection = DriverManager.getConnection(jdbcUrl, username, password);

			// Step 3: Create a Statement object
			statement = connection.createStatement();

			// Step 4: Execute a SQL SELECT query
			String sql = "SELECT id, name, email FROM assjdbc8";
			resultSet = statement.executeQuery(sql);

			// Step 5: Process the ResultSet
			System.out.println("Data from assjdbc8 table:");
			boolean hasResults = false; // To check if there are any results
			while (resultSet.next()) {
				hasResults = true;
				int id = resultSet.getInt("id");
				String name = resultSet.getString("name");
				String email = resultSet.getString("email");

				System.out.println("ID: " + id + ", Name: " + name + ", Email: " + email);
			}

			if (!hasResults) {
				System.out.println("No data found in assjdbc8 table.");
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
		} finally {
			// Step 6: Clean up resources
			try {
				if (resultSet != null)
					resultSet.close();
				if (statement != null)
					statement.close();
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				System.err.println("Error closing resources.");
				e.printStackTrace();
			}
		}
	}
}
