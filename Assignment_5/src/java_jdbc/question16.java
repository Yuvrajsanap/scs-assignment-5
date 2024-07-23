package java_jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class question16 {
	private static final String JDBC_URL = "jdbc:mysql://localhost:3306/testdb";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "Yuvraj@12345";

	public static void main(String[] args) {
		try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD)) {
			System.out.println("Connected to the database.");

			// Create the table
			createTable(connection);
			System.out.println("Table created successfully.");

			// Insert data using batch updates
			insertDataInBatch(connection);
			System.out.println("Data inserted successfully using batch processing.");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private static void createTable(Connection connection) throws SQLException {
		String createTableSQL = "CREATE TABLE IF NOT EXISTS assjdbc16 (" + "id INT AUTO_INCREMENT PRIMARY KEY, "
				+ "name VARCHAR(100) NOT NULL, " + "value INT)";
		try (PreparedStatement pstmt = connection.prepareStatement(createTableSQL)) {
			pstmt.executeUpdate();
		}
	}

	private static void insertDataInBatch(Connection connection) throws SQLException {
		String insertSQL = "INSERT INTO assjdbc16 (name, value) VALUES (?, ?)";
		try (PreparedStatement pstmt = connection.prepareStatement(insertSQL)) {
			connection.setAutoCommit(false); // Disable auto-commit for batch processing

			for (int i = 1; i <= 1000; i++) {
				pstmt.setString(1, "yuvraj " + i);
				pstmt.setInt(2, i);
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
			e.printStackTrace();
		} finally {
			connection.setAutoCommit(true); // Re-enable auto-commit
		}
	}
}
