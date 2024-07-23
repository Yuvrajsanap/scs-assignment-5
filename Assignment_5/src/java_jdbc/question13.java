package java_jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.Queue;

public class question13 {
	private static final int INITIAL_POOL_SIZE = 10;
	private static final String JDBC_URL = "jdbc:mysql://localhost:3306/testdb";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "Yuvraj@12345";
	private static final Queue<Connection> connectionPool = new LinkedList<>();

	static {
		try {
			// Load the JDBC driver
			Class.forName("com.mysql.cj.jdbc.Driver");

			// Initialize the connection pool
			for (int i = 0; i < INITIAL_POOL_SIZE; i++) {
				connectionPool.add(DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD));
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	public static Connection getConnection() throws SQLException {
		if (connectionPool.isEmpty()) {
			return DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
		} else {
			return connectionPool.poll();
		}
	}

	public static void releaseConnection(Connection connection) {
		if (connection != null) {
			connectionPool.offer(connection);
		}
	}

	public static void main(String[] args) {
		try (Connection connection = question13.getConnection()) {
			// Perform database operations
			createTable(connection);
			insertData(connection);
			fetchData(connection);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private static void createTable(Connection connection) throws SQLException {
		String createTableSQL = "CREATE TABLE IF NOT EXISTS assjdbc13 (" + "id INT AUTO_INCREMENT PRIMARY KEY, "
				+ "name VARCHAR(100) NOT NULL, " + "email VARCHAR(100) NOT NULL)";
		try (PreparedStatement pstmt = connection.prepareStatement(createTableSQL)) {
			pstmt.executeUpdate();
		}
	}

	private static void insertData(Connection connection) throws SQLException {
		String insertSQL = "INSERT INTO assjdbc13 (name, email) VALUES (?, ?)";
		try (PreparedStatement pstmt = connection.prepareStatement(insertSQL)) {
			pstmt.setString(1, "yuvraj");
			pstmt.setString(2, "yuvraj@example.com");
			pstmt.addBatch();

			pstmt.setString(1, "vishal");
			pstmt.setString(2, "vishal@example.com");
			pstmt.addBatch();

			pstmt.setString(1, "om");
			pstmt.setString(2, "om@example.com");
			pstmt.addBatch();

			pstmt.executeBatch();
		}
	}

	private static void fetchData(Connection connection) throws SQLException {
		String selectSQL = "SELECT * FROM assjdbc13";
		try (PreparedStatement pstmt = connection.prepareStatement(selectSQL)) {
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String email = rs.getString("email");
				System.out.println("ID: " + id + ", Name: " + name + ", Email: " + email);
			}
		}
	}
}
