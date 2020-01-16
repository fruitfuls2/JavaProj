package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MemberDAO {
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String user = "hr";
	private String password = "hr";
	private Connection conn = null;
	private PreparedStatement psmt = null;
	private ResultSet rs = null;

	public int insert(Member m) { // 가입하기
		int rows = 0;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(url, user, password);
			String sql = "INSERT INTO member VALUES (?, ?, ?, ?, ?, ?)";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, m.getId());
			psmt.setString(2, m.getPw());
			psmt.setString(3, m.getName());
			psmt.setString(4, m.getSocialNumber());
			psmt.setString(5, m.getAddress());
			psmt.setString(6, m.getPhone());
			rows = psmt.executeUpdate();
			if (rows == 0) {
				System.out.println("SQL문을 확인하세요.");
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (psmt != null)
					psmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return rows;
	}

	public Member selectOne(Member m) { // 로그인하기
		Member loginUser = null;

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(url, user, password);
			String sql = "SELECT * FROM member WHERE member_id = ? AND member_pw = ?";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, m.getId());
			psmt.setString(2, m.getPw());
			rs = psmt.executeQuery();

			if (rs.next()) {
				String id = rs.getString("MEMBER_ID");
				String pw = rs.getString("MEMBER_PW");
				String name = rs.getString("MEMBER_NAME");
				String socialNumber = rs.getString("MEMBER_SOCIALNUMBER");
				String address = rs.getString("MEMBER_ADDRESS");
				String phone = rs.getString("MEMBER_PHONE");

				loginUser = new Member(id, pw, name, socialNumber, address, phone);
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (psmt != null)
					psmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return loginUser;
	}

	public int updateMember(Member loginUser) { // 수정
		int rows = 0;
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "hr";
		String password = "hr";
		Connection conn = null;
		PreparedStatement psmt = null;

		try { // try ~ catch 예외처리
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(url, user, password);
			String sql = "UPDATE member SET member_name = ?, member_pw = ?, member_address = ?, member_phone= ?  WHERE member_id = ?";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, loginUser.getName());
			psmt.setString(2, loginUser.getPw());
			psmt.setString(3, loginUser.getAddress());
			psmt.setString(4, loginUser.getPhone());
			psmt.setString(5, loginUser.getId());
			rows = psmt.executeUpdate();

		} catch (ClassNotFoundException e) { //
			e.printStackTrace();
		} catch (SQLException e) { // url, id, pw이 틀리면 예외사항을 걸어두는 곳
			e.printStackTrace();
		} finally {
			try {
				if (psmt != null)
					psmt.close(); // 가장 최근에 열었던 객체부터 닫아줘야 함.
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return rows;
	}

	private static void delete(Member loginUser) { // 삭제
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "hr";
		String password = "hr";
		Connection conn = null;
		PreparedStatement psmt = null;

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(url, user, password); // 얘도 try catch를 새로. connection은 인터페이스
			String sql = "DELETE FROM member WHERE member_id = ?";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, loginUser.getId());

			int rows = psmt.executeUpdate(); // 실행. 이 문장에 영향받은 rows가 리턴된다. 한 줄이니까 1 / 딜리트로 3개가 삭제되면 3
			if (rows == 0) {
				System.out.println("SQL문을 확인하세요.");
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) { // id,비번,url등이 틀렸을 때
			e.printStackTrace();
		} finally {
			// 가장 나중에 한 것부터 닫는다.
			try {
				if (psmt != null) // 객체가 생성됐을때만 닫아야한다.
					psmt.close();

				if (conn != null)
					conn.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	public ArrayList<Member> selectAll(String login_id) {
		ArrayList<Member> list = new ArrayList<>();

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(url, user, password);
			String sql = "SELECT * FROM member WHERE member_id != ?";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, login_id);
			rs = psmt.executeQuery();

			while (rs.next()) {
				String id = rs.getString("MEMBER_ID");
				String name = rs.getString("MEMBER_NAME");
				String socialNumber = rs.getString("MEMBER_SOCIALNUMBER");
				String address = rs.getString("MEMBER_ADDRESS");
				String phone = rs.getString("MEMBER_PHONE");
				String pw = rs.getString("MEMBER_PW");

				list.add(new Member(id, name, socialNumber, address, phone, pw));
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (psmt != null)
					psmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	public static ArrayList<Member> selectName(String login_id) { // 이름 검색 기능
		ArrayList<Member> list = new ArrayList<>();
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "hr";
		String password = "hr";
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null; // 인터페이스(ResultSet)

		try { // 실행 도중 잡아낼 수 있는 예외 처리
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(url, user, password);
			String sql = "select * FROM member WHERE member_name like '%' || ? || '%' ";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, login_id);
			rs = psmt.executeQuery();
			// 다음 읽어드릴것이 있느냐 물어보는 말(계속 커서가 내려가면서 읽는 느낌)

			while (rs.next()) {
				String id = rs.getString("MEMBER_ID");
				String name = rs.getString("MEMBER_NAME");
				String socialNumber = rs.getString("MEMBER_SOCIALNUMBER");
				String address = rs.getString("MEMBER_ADDRESS");
				String phone = rs.getString("MEMBER_PHONE");
				String pw = rs.getString("MEMBER_PW");

				Member m = new Member(id, name, socialNumber, address, phone, pw);
				list.add(m);
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) { // id,비번,url등이 틀렸을 때
			e.printStackTrace();
		} finally {
			try {
				if (psmt != null) // 객체가 생성됐을때만 닫아야한다.
					psmt.close();

				if (conn != null)
					conn.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return list;
	}

	public static ArrayList<Member> selectAddress(String login_id) { // 주소 검색 기능
		ArrayList<Member> list = new ArrayList<>();
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "hr";
		String password = "hr";
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null; // 인터페이스(ResultSet)

		try { // 실행 도중 잡아낼 수 있는 예외 처리
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(url, user, password);
			String sql = "select * FROM member WHERE member_address like '%' || ? || '%' ";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, login_id);
			rs = psmt.executeQuery();
			// 다음 읽어드릴것이 있느냐 물어보는 말(계속 커서가 내려가면서 읽는 느낌)

			while (rs.next()) {
				String id = rs.getString("MEMBER_ID");
				String name = rs.getString("MEMBER_NAME");
				String socialNumber = rs.getString("MEMBER_SOCIALNUMBER");
				String address = rs.getString("MEMBER_ADDRESS");
				String phone = rs.getString("MEMBER_PHONE");
				String pw = rs.getString("MEMBER_PW");

				Member m = new Member(id, name, socialNumber, address, phone, pw);
				list.add(m);
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) { // id,비번,url등이 틀렸을 때
			e.printStackTrace();
		} finally {
			try {
				if (psmt != null) // 객체가 생성됐을때만 닫아야한다.
					psmt.close();

				if (conn != null)
					conn.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return list;
	}

	public static ArrayList<Member> selectPhone(String login_id) { // 전화번호 검색 기능
		System.out.println(login_id);
		ArrayList<Member> list = new ArrayList<>();
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "hr";
		String password = "hr";
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null; // 인터페이스(ResultSet)

		try { // 실행 도중 잡아낼 수 있는 예외 처리
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(url, user, password);
			String sql = "select * FROM member WHERE member_phone like '%' || ? || '%' ";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, login_id);
			rs = psmt.executeQuery();
			// 다음 읽어드릴것이 있느냐 물어보는 말(계속 커서가 내려가면서 읽는 느낌)

			while (rs.next()) {
				String id = rs.getString("MEMBER_ID");
				String name = rs.getString("MEMBER_NAME");
				String socialNumber = rs.getString("MEMBER_SOCIALNUMBER");
				String address = rs.getString("MEMBER_ADDRESS");
				String phone = rs.getString("MEMBER_PHONE");
				String pw = rs.getString("MEMBER_PW");

				Member m = new Member(id, name, socialNumber, address, phone, pw);
				list.add(m);
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) { // id,비번,url등이 틀렸을 때
			e.printStackTrace();
		} finally {
			try {
				if (psmt != null) // 객체가 생성됐을때만 닫아야한다.
					psmt.close();

				if (conn != null)
					conn.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return list;
	}

	public static ArrayList<Member> selectId(String login_id) { // 아이디 검색 기능
		ArrayList<Member> list = new ArrayList<>();
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "hr";
		String password = "hr";
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null; // 인터페이스(ResultSet)

		try { // 실행 도중 잡아낼 수 있는 예외 처리
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(url, user, password);
			String sql = "select * FROM member WHERE member_id like '%' || ? || '%' ";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, login_id);
			rs = psmt.executeQuery(); // 리턴받아와서 데이터를 사용할 객체 생성
			// 다음 읽어드릴것이 있느냐 물어보는 말(계속 커서가 내려가면서 읽는 느낌)

			while (rs.next()) { // 각각 값을 가져와서 테이블값들을 추가
				String id = rs.getString("MEMBER_ID");
				String name = rs.getString("MEMBER_NAME");
				String pw = rs.getString("MEMBER_PW");
				String address = rs.getString("MEMBER_ADDRESS");
				String phone = rs.getString("MEMBER_PHONE");

				Member m = new Member(id, name, pw, address, phone);
				list.add(m);
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) { // id,비번,url등이 틀렸을 때
			e.printStackTrace();
		} finally {
			try {
				if (psmt != null) // 객체가 생성됐을때만 닫아야한다.
					psmt.close();

				if (conn != null)
					conn.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return list;
	}

}
