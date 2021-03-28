package kr.hr.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AllowDAO {
	private Connection conn;
	private PreparedStatement psmt;
	private ResultSet rs;

	private void getConnect() {
		String URL = "jdbc:oracle:thin:@127.0.0.1:1521:XE";
		String user = "hr";
		String password = "hr";
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(URL, user, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void getClose() {
		try {
			if (rs != null)
				rs.close();
			if (psmt != null)
				psmt.close();
			if (conn != null)
				conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public int insertAllow(AllowVO vo) {
		int result = -1;
		System.out.println("여기는 result 선언");
		try {
			getConnect();
			System.out.println("여기는 SQL문 선언 전");
			String sql = "insert into allow" + " values (?,?,?,?)";
			psmt = conn.prepareStatement(sql);
			System.out.println("여기는 prepareStatement 후");
			psmt.setString(1, vo.getManager_id());
			psmt.setInt(2, vo.getSub_number());
			psmt.setString(3, vo.getYn());
			psmt.setString(4, vo.getNote());
			result = psmt.executeUpdate();
			System.out.println("여기는 executeUpdate 후");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			getClose();
		}
		System.out.println("result : " + result);
		return result;
	}

	
}
