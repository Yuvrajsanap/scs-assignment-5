package java_jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class question17 {
	private static final String JDBC_URL = "jdbc:mysql://localhost:3306/testdb";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "Yuvraj@12345";

	public static void main(String[] args) {
		try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD)) {
			System.out.println("Connected to the database.");

			// Create the table
			createTable(connection);
			System.out.println("Table created successfully.");

			// Insert data into the table
			insertData(connection, "admin", "admin123");
			insertData(connection, "user", "user123");
			System.out.println("Data inserted successfully.");

			// Query data using a parameterized query to prevent SQL injection
			queryData(connection, "admin", "admin123");
			queryData(connection, "user", "user123");
			queryData(connection, "admin", "' OR '1'='1"); // Attempted SQL injection
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private static void createTable(Connection connection) throws SQLException {
		String createTableSQL = "CREATE TABLE IF NOT EXISTS assjdbc17 (" + "id INT AUTO_INCREMENT PRIMARY KEY, "
				+ "username VARCHAR(50) NOT NULL, " + "password VARCHAR(50) NOT NULL)";
		try (PreparedStatement pstmt = connection.prepareStatement(createTableSQL)) {
			pstmt.executeUpdate();
		}
	}

	private static void insertData(Connection connection, String username, String password) throws SQLException {
		String insertSQL = "INSERT INTO assjdbc17 (username, password) VALUES (?, ?)";
		try (PreparedStatement pstmt = connection.prepareStatement(insertSQL)) {
			pstmt.setString(1, username);
			pstmt.setString(2, password);
			pstmt.executeUpdate();
		}
	}

	private static void queryData(Connection connection, String username, String password) throws SQLException {
		String querySQL = "SELECT id, username FROM assjdbc17 WHERE username = ? AND password = ?";
		try (PreparedStatement pstmt = connection.prepareStatement(querySQL)) {
			pstmt.setString(1, username);
			pstmt.setString(2, password);
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					System.out.println("User authenticated: " + rs.getString("username"));
				} else {
					System.out.println("Authentication failed for username: " + username);
				}
			}
		}
	}
}
