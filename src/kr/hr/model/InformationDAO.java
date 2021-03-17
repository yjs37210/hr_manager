package kr.hr.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class InformationDAO {
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

	public int insert(InformationVO vo) {
		int result = 0;
		try {
			getConnect();
			String sql = "insert into information(name, mil_id, pw, job, rank, s_date, manager_id) "
					+ "values (?,?,?,?,?,to_date(?,'YYYYMMDD'),?)";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, vo.getName());
			psmt.setString(2, vo.getMil_id());
			psmt.setInt(3, vo.getPw());
			psmt.setString(4, vo.getJob());
			psmt.setString(5, vo.getRank());
			psmt.setString(6, vo.getS_date());
			psmt.setString(7, vo.getManager_id());
			result = psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			getClose();
		}
		return result;
	}
}
