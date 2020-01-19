package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BookStDAO {

	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String user = "hr";
	private String password = "hr";
	private Connection conn = null;
	private PreparedStatement psmt = null;
	private ResultSet rs = null;
	private Book b;

	public ArrayList<Book> rankFive(String login_id) {

		ArrayList<Book> list = new ArrayList<>();

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(url, user, password);
			String sql = "SELECT b_title, b_count, ROW_NUMBER() OVER (ORDER BY b_count DESC) FROM Book WHERE b_isbn = ? AND ROWNUM <= 5";
			psmt = conn.prepareStatement(sql);

			File file2 = new File("D:\\BookIsbn.txt");
			FileReader file_reader2 = new FileReader(file2);
			int cur2 = 0;
			String strBookISBN = "";
			while ((cur2 = file_reader2.read()) != -1) {
				strBookISBN += (char) cur2;
			}
			psmt.setLong(1, Long.parseLong(strBookISBN));

			rs = psmt.executeQuery();

			while (rs.next()) {
				String title = rs.getString("B_TITLE");
				int cnt = rs.getInt("b_COUNT");
				list.add(new Book(title, cnt));
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (psmt != null)
					psmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
}
