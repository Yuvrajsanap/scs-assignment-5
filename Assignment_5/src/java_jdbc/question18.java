package java_jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class question18 {
	private static final String JDBC_URL = "jdbc:mysql://localhost:3306/testdb";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "Yuvraj@12345";

	public static void main(String[] args) {
		try (Connection connection = getConnection()) {
			System.out.println("Connected to the database.");

			// Create the table
			createTable(connection);
			System.out.println("Table created successfully.");

			// Insert data using batch processing
			insertDataInBatch(connection);
			System.out.println("Data inserted successfully.");

			// Retrieve and display data
			retrieveData(connection);
		} catch (SQLException e) {
			handleSQLException(e);
		}
	}

	private static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
	}

	private static void createTable(Connection connection) throws SQLException {
		String createTableSQL = "CREATE TABLE IF NOT EXISTS assjdbc18 (" + "id INT AUTO_INCREMENT PRIMARY KEY, "
				+ "name VARCHAR(100) NOT NULL, " + "email VARCHAR(100) NOT NULL, " + "age INT)";
		try (PreparedStatement pstmt = connection.prepareStatement(createTableSQL)) {
			pstmt.executeUpdate();
		}
	}

	private static void insertDataInBatch(Connection connection) throws SQLException {
		String insertSQL = "INSERT INTO assjdbc18 (name, email, age) VALUES (?, ?, ?)";
		try (PreparedStatement pstmt = connection.prepareStatement(insertSQL)) {
			connection.setAutoCommit(false); // Disable auto-commit for batch processing

			for (int i = 1; i <= 1000; i++) {
				pstmt.setString(1, "yuvraj " + i);
				pstmt.setString(2, "email" + i + "@example.com");
				pstmt.setInt(3, 20 + (i % 30));
				pstmt.addBatch();

				if (i % 100 == 0) { // Execute batch every 100 inserts
					pstmt.executeBatch();
					System.out.println("Executed batch at record: " + i);
				}
			}

			pstmt.executeBatch(); // Execute any remaining inserts
			connection.commit(); // Commit the transaction
			System.out.println("Transaction committed.");
		} catch (SQLException e) {
			connection.rollback(); // Rollback transaction on error
			System.err.println("Transaction rolled back due to error.");
			handleSQLException(e);
		} finally {
			connection.setAutoCommit(true); // Re-enable auto-commit
		}
	}

	private static void retrieveData(Connection connection) throws SQLException {
		String querySQL = "SELECT id, name, email, age FROM assjdbc18";
		try (PreparedStatement pstmt = connection.prepareStatement(querySQL); ResultSet rs = pstmt.executeQuery()) {
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String email = rs.getString("email");
				int age = rs.getInt("age");

				System.out.println("ID: " + id + ", Name: " + name + ", Email: " + email + ", Age: " + age);
			}
		}
	}

	private static void handleSQLException(SQLException e) {
		System.err.println("SQLState: " + e.getSQLState());
		System.err.println("Error Code: " + e.getErrorCode());
		System.err.println("Message: " + e.getMessage());
		Throwable t = e.getCause();
		while (t != null) {
			System.out.println("Cause: " + t);
			t = t.getCause();
		}
	}
}
