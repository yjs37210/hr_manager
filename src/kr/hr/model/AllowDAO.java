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
		try {
			getConnect();
			String sql = "insert into allow" + " values (?,?,?,?)";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, vo.getManager_id());
			psmt.setInt(2, vo.getSub_number());
			psmt.setString(3, vo.getYn());
			psmt.setString(4, vo.getNote());
			result = psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			getClose();
		}
		return result;
	}

	
}
