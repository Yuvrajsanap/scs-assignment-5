package java_jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class question10 {
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
			// Load the JDBC driver
			Class.forName("com.mysql.cj.jdbc.Driver");

			// Establish a connection to the database
			connection = DriverManager.getConnection(jdbcUrl, username, password);

			// Demonstrate TYPE_FORWARD_ONLY
			System.out.println("TYPE_FORWARD_ONLY ResultSet:");
			statement = connection.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
			String sql = "SELECT id, name, email FROM assjdbc10";
			resultSet = statement.executeQuery(sql);
			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String name = resultSet.getString("name");
				String email = resultSet.getString("email");
				System.out.println("ID: " + id + ", Name: " + name + ", Email: " + email);
			}
			resultSet.close();

			// Demonstrate TYPE_SCROLL_INSENSITIVE
			System.out.println("\nTYPE_SCROLL_INSENSITIVE ResultSet:");
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			resultSet = statement.executeQuery(sql);
			if (resultSet.first()) {
				do {
					int id = resultSet.getInt("id");
					String name = resultSet.getString("name");
					String email = resultSet.getString("email");
					System.out.println("ID: " + id + ", Name: " + name + ", Email: " + email);
				} while (resultSet.next());
			}
			resultSet.close();

			// Demonstrate TYPE_SCROLL_SENSITIVE
			System.out.println("\nTYPE_SCROLL_SENSITIVE ResultSet:");
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			resultSet = statement.executeQuery(sql);
			if (resultSet.first()) {
				do {
					int id = resultSet.getInt("id");
					String name = resultSet.getString("name");
					String email = resultSet.getString("email");
					System.out.println("ID: " + id + ", Name: " + name + ", Email: " + email);
				} while (resultSet.next());
			}
			resultSet.close();

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
			// Clean up resources
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
