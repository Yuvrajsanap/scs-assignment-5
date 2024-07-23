package java_jdbc;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class question15 {
	private static final String JDBC_URL = "jdbc:mysql://localhost:3306/testdb";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "Yuvraj@12345";

	public static void main(String[] args) {
		try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD)) {
			// Create the table
			createTable(connection);

			// Insert a BLOB and CLOB
			insertData(connection);

			// Retrieve and display the BLOB and CLOB
			fetchData(connection);
		} catch (SQLException | IOException e) {
			e.printStackTrace();
		}
	}

	private static void createTable(Connection connection) throws SQLException {
		String createTableSQL = "CREATE TABLE IF NOT EXISTS assjdbc150 (" + "id INT AUTO_INCREMENT PRIMARY KEY, "
				+ "name VARCHAR(100) NOT NULL, " + "photo BLOB, " + "description MEDIUMTEXT)";
		try (PreparedStatement pstmt = connection.prepareStatement(createTableSQL)) {
			pstmt.executeUpdate();
		}
	}

	private static void insertData(Connection connection) throws SQLException, IOException {
		String insertSQL = "INSERT INTO assjdbc150 (name, photo, description) VALUES (?, ?, ?)";
		try (PreparedStatement pstmt = connection.prepareStatement(insertSQL)) {
			pstmt.setString(1, "Sample Data");

			// Set BLOB
			File photoFile = new File(
					"\"C:\\Users\\yuvra\\Pictures\\Saved Pictures\\wallpaperflare.com_wallpaper (4).jpg\"");
			try (FileInputStream fis = new FileInputStream(photoFile)) {
				pstmt.setBinaryStream(2, fis, (int) photoFile.length());
			}

			// Set CLOB
			File descriptionFile = new File("\"C:\\Users\\yuvra\\Documents\\BE project\\109.ys.docx\"");
			try (FileReader fr = new FileReader(descriptionFile)) {
				pstmt.setCharacterStream(3, fr, (int) descriptionFile.length());
			}

			pstmt.executeUpdate();
		}
	}

	private static void fetchData(Connection connection) throws SQLException, IOException {
		String selectSQL = "SELECT id, name, photo, description FROM assjdbc150";
		try (PreparedStatement pstmt = connection.prepareStatement(selectSQL)) {
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");

				// Retrieve BLOB
				File photoFile = new File("retrieved_photo.jpg");
				try (FileOutputStream fos = new FileOutputStream(photoFile)) {
					InputStream is = rs.getBinaryStream("photo");
					byte[] buffer = new byte[1024];
					while (is.read(buffer) > 0) {
						fos.write(buffer);
					}
				}

				// Retrieve CLOB
				File descriptionFile = new File("retrieved_description.docx");
				try (FileWriter fw = new FileWriter(descriptionFile)) {
					Reader reader = rs.getCharacterStream("description");
					char[] buffer = new char[1024];
					while (reader.read(buffer) > 0) {
						fw.write(buffer);
					}
				}

				System.out.println("ID: " + id + ", Name: " + name);
				System.out.println("Photo and description retrieved.");
			}
		}
	}
}
