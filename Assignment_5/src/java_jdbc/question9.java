package java_jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class question9 {
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
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

			// Step 4: Execute a SQL SELECT query
			String sql = "SELECT id, name, email FROM assjdbc9";
			resultSet = statement.executeQuery(sql);

			// Step 5: Navigate and Retrieve Data from ResultSet
			System.out.println("Data from assjdbc9 table:");

			// Navigate to the first row
			if (resultSet.first()) {
				do {
					int id = resultSet.getInt("id");
					String name = resultSet.getString("name");
					String email = resultSet.getString("email");

					System.out.println("ID: " + id + ", Name: " + name + ", Email: " + email);
				} while (resultSet.next()); // Continue until no more rows
			} else {
				System.out.println("No data found in assjdbc9 table.");
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
