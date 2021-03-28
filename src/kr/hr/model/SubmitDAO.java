package kr.hr.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SubmitDAO {
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

	public ArrayList<SubmitVO> vacSelect() {
		ArrayList<SubmitVO> list = new ArrayList<SubmitVO>();
		try {
			getConnect();
			String sql = "select * from submitTable where vac_type='정기휴가(9박 10일)' ";
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			while (rs.next()) {
				String mil_id = rs.getString(1);
				String rank = rs.getString(2);
				String name = rs.getString(3);
				String job = rs.getString(4);
				String vac_type = rs.getString(5);
				int sub_date = rs.getInt(6);
				int end_date = rs.getInt(7);
				String reason = rs.getString(8);
				SubmitVO vo = new SubmitVO(sub_date, end_date, reason, vac_type, mil_id, rank, name, job);
				list.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			getClose();
		}
		return list;
	}

	public ArrayList<SubmitVO> nightSelect() {
		ArrayList<SubmitVO> list = new ArrayList<SubmitVO>();
		try {
			getConnect();
			String sql = "select * from submitTable where vac_type = '정기외박(3박 4일)' or vac_type like '%특별%' ";
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			while (rs.next()) {
				String mil_id = rs.getString(1);
				String rank = rs.getString(2);
				String name = rs.getString(3);
				String job = rs.getString(4);
				String vac_type = rs.getString(5);
				int sub_date = rs.getInt(6);
				int end_date = rs.getInt(7);
				String reason = rs.getString(8);
				SubmitVO vo = new SubmitVO(sub_date, end_date, reason, vac_type, mil_id, rank, name, job);
				list.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			getClose();
		}
		return list;
	}

	public ArrayList<SubmitVO> outSelect() {
		ArrayList<SubmitVO> list = new ArrayList<SubmitVO>();
		try {
			getConnect();
			String sql = "select * from submitTable where vac_type like'%정기외출%'";
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			while (rs.next()) {
				String mil_id = rs.getString(1);
				String rank = rs.getString(2);
				String name = rs.getString(3);
				String job = rs.getString(4);
				String vac_type = rs.getString(5);
				int sub_date = rs.getInt(6);
				int end_date = rs.getInt(7);
				String reason = rs.getString(8);
				SubmitVO vo = new SubmitVO(sub_date, end_date, reason, vac_type, mil_id, rank, name, job);
				list.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			getClose();
		}
		return list;
	}

	public ArrayList<SubmitVO> allSelect() {
		ArrayList<SubmitVO> list = new ArrayList<SubmitVO>();
		try {
			getConnect();
			String sql = "select * from submitTable where yn is null";
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			while (rs.next()) {
				String mil_id = rs.getString(1);
				String rank = rs.getString(2);
				String name = rs.getString(3);
				String job = rs.getString(4);
				String vac_type = rs.getString(5);
				int sub_date = rs.getInt(6);
				int end_date = rs.getInt(7);
				String reason = rs.getString(8);
				int sub_number = rs.getInt(9);
				SubmitVO vo = new SubmitVO(sub_date, end_date, reason, vac_type, sub_number, mil_id, rank, name, job);
				list.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			getClose();
		}
		return list;
	}

//	public int delete_submit(int sub_number) {
//		int result = 0;
//		try {
//			getConnect();
//			String sql = "delete from submit where sub_number = ?";
//			psmt = conn.prepareStatement(sql);
//			psmt.setInt(1, sub_number);
//			result = psmt.executeUpdate();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			getClose();
//		}
//		return result;
//	}

}
