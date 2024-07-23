package java_jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class question14 {
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

	public static synchronized Connection getConnection() throws SQLException {
		if (connectionPool.isEmpty()) {
			return DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
		} else {
			return connectionPool.poll();
		}
	}

	public static synchronized void releaseConnection(Connection connection) {
		if (connection != null) {
			connectionPool.offer(connection);
		}
	}

	public static void main(String[] args) {
		// Create a thread pool
		ExecutorService executorService = Executors.newFixedThreadPool(5);

		// Submit multiple tasks to the executor service
		for (int i = 0; i < 10; i++) {
			executorService.submit(() -> {
				try (Connection connection = question14.getConnection()) {
					performDatabaseOperations(connection);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			});
		}

		// Shutdown the executor service
		executorService.shutdown();
	}

	private static void performDatabaseOperations(Connection connection) throws SQLException {
		// Perform database operations
		createTable(connection);
		insertData(connection);
		fetchData(connection);
	}

	private static void createTable(Connection connection) throws SQLException {
		String createTableSQL = "CREATE TABLE IF NOT EXISTS assjdbc14 (" + "id INT AUTO_INCREMENT PRIMARY KEY, "
				+ "name VARCHAR(100) NOT NULL, " + "email VARCHAR(100) NOT NULL)";
		try (PreparedStatement pstmt = connection.prepareStatement(createTableSQL)) {
			pstmt.executeUpdate();
		}
	}

	private static void insertData(Connection connection) throws SQLException {
		String insertSQL = "INSERT INTO assjdbc14 (name, email) VALUES (?, ?)";
		try (PreparedStatement pstmt = connection.prepareStatement(insertSQL)) {
			pstmt.setString(1, "david backhmen");
			pstmt.setString(2, "david@example.com");
			pstmt.addBatch();

			pstmt.setString(1, "neymar jr");
			pstmt.setString(2, "neymar@example.com");
			pstmt.addBatch();

			pstmt.setString(1, "kayli mbappe");
			pstmt.setString(2, "mbappe@example.com");
			pstmt.addBatch();

			pstmt.executeBatch();
		}
	}

	private static void fetchData(Connection connection) throws SQLException {
		String selectSQL = "SELECT * FROM assjdbc14";
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
