package kr.hr.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ManagerDAO {
	private Connection conn;
	private PreparedStatement psmt;
	private ResultSet rs;

	private void getConnect() {
		String URL = "jdbc:oracle:thin:@localhost:1521:xe";
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

	public String managerlogin(String manager_id, int manager_pw) {
		String name = null;
		try {
			getConnect();
			String sql = "SELECT * FROM MANAGER WHERE MANAGER_ID = ? AND MANAGER_PW = ?";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, manager_id);
			psmt.setInt(2, manager_pw);
			rs = psmt.executeQuery();
			if (rs.next()) {
				name = rs.getString("MANAGER_NAME");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			getClose();
		}
		return name;
	}
}
