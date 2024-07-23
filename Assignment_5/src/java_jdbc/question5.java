package java_jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class question5 {
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

			// Step 2: Establish a connection to the database using DriverManager
			connection = DriverManager.getConnection(jdbcUrl, username, password);
			System.out.println("Connection established successfully.");

			// Step 3: Create a statement object
			statement = connection.createStatement();

			// Step 4: Execute a SQL query
			String sql = "SELECT id, name, email FROM assjdbc5";
			resultSet = statement.executeQuery(sql);

			// Step 5: Process the result set
			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String name = resultSet.getString("name");
				String email = resultSet.getString("email");

				System.out.println("ID: " + id + ", Name: " + name + ", Email: " + email);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
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
				e.printStackTrace();
			}
		}
	}
}
