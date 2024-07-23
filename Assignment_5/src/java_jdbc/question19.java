package java_jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class question19 {
	private static final String JDBC_URL = "jdbc:mysql://localhost:3306/testdb";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "Yuvraj@12345";

	public static void main(String[] args) {
		// Record data to be inserted
		String name = "yuvraj sanap";
		String email = "yuvraj@example.com";

		try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD)) {
			System.out.println("Connected to the database.");

			// Insert the record
			insertRecord(connection, name, email);
			System.out.println("Record inserted successfully.");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private static void insertRecord(Connection connection, String name, String email) throws SQLException {
		String insertSQL = "INSERT INTO assjdbc19 (name, email) VALUES (?, ?)";
		try (PreparedStatement pstmt = connection.prepareStatement(insertSQL)) {
			pstmt.setString(1, name);
			pstmt.setString(2, email);
			pstmt.executeUpdate();
		}
	}
}
