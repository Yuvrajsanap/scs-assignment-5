package java_jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class question6 {
	public static void main(String[] args) {
		// Database URL
		String jdbcUrl = "jdbc:mysql://localhost:3306/testdb";
		// Database credentials
		String username = "root";
		String password = "Yuvraj@12345";

		Connection connection = null;
		Statement statement = null;
		PreparedStatement preparedStatement = null;
		CallableStatement callableStatement = null;
		ResultSet resultSet = null;

		try {
			// Step 1: Load the JDBC driver
			Class.forName("com.mysql.cj.jdbc.Driver");

			// Step 2: Establish a connection to the database
			connection = DriverManager.getConnection(jdbcUrl, username, password);

			// *** Using Statement ***
			// Step 3: Create a Statement object
			statement = connection.createStatement();
			String sql = "SELECT id, name, email FROM assjdbc6";
			resultSet = statement.executeQuery(sql);
			System.out.println("Using Statement:");
			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String name = resultSet.getString("name");
				String email = resultSet.getString("email");
				System.out.println("ID: " + id + ", Name: " + name + ", Email: " + email);
			}
			resultSet.close(); // Close the ResultSet

			// *** Using PreparedStatement ***
			String insertSql = "INSERT INTO assjdbc6 (name, email) VALUES (?, ?)";
			preparedStatement = connection.prepareStatement(insertSql);
			preparedStatement.setString(1, "New User");
			preparedStatement.setString(2, "new.user@example.com");
			int rowsAffected = preparedStatement.executeUpdate();
			System.out.println("Rows affected using PreparedStatement: " + rowsAffected);

			// *** Using CallableStatement ***
			// Create a stored procedure in MySQL
			String procedureSql = "CREATE PROCEDURE GetUserDetails(IN userId INT) " + "BEGIN "
					+ "SELECT * FROM assjdbc6 WHERE id = userId; " + "END";
			statement.execute(procedureSql); // Create the procedure

			String callSql = "{call GetUserDetails(?)}";
			callableStatement = connection.prepareCall(callSql);
			callableStatement.setInt(1, 1); // Pass the user ID as a parameter
			resultSet = callableStatement.executeQuery();
			System.out.println("Using CallableStatement:");
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
			// Step 4: Clean up resources
			try {
				if (resultSet != null)
					resultSet.close();
				if (statement != null)
					statement.close();
				if (preparedStatement != null)
					preparedStatement.close();
				if (callableStatement != null)
					callableStatement.close();
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
