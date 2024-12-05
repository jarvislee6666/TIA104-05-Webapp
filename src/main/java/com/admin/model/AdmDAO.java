package com.admin.model;

import java.util.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class AdmDAO implements AdmDAO_interface {

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB3");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT_STMT = "INSERT INTO admin (adm_email,adm_password,adm_name,adm_phone, adm_sta, hr_date,adm_bday, supvsr) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT adm_id, adm_name, adm_email, adm_password, adm_phone, adm_sta, hr_date, adm_bday, supvsr FROM admin order by adm_id";
	private static final String GET_ONE_STMT = "SELECT adm_id, adm_name, adm_email, adm_password, adm_phone, adm_sta, hr_date, adm_bday, supvsr FROM admin where adm_id = ?";
	private static final String DELETE = "DELETE FROM admin where adm_id = ?";
	private static final String UPDATE = "UPDATE admin set adm_email=?, adm_password=?, adm_name=?, adm_phone=?, adm_sta=?, hr_date=?, adm_bday=?, supvsr=? where adm_id = ?";

	@Override
	public void insert(AdmVO admVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, admVO.getAdmEmail());
			pstmt.setString(2, admVO.getAdmPassword());
			pstmt.setString(3, admVO.getAdmName());
			pstmt.setString(4, admVO.getAdmPhone());
			pstmt.setBoolean(5, admVO.getAdmSta());
			pstmt.setDate(6, admVO.getHrDate());
			pstmt.setDate(7, admVO.getAdmBday());
			pstmt.setBoolean(8, admVO.getSupvsr());

			pstmt.executeUpdate();

			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}

	@Override
	public void update(AdmVO admVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, admVO.getAdmEmail());
			pstmt.setString(2, admVO.getAdmPassword());
			pstmt.setString(3, admVO.getAdmName());
			pstmt.setString(4, admVO.getAdmPhone());
			pstmt.setBoolean(5, admVO.getAdmSta());
			pstmt.setDate(6, admVO.getHrDate());
			pstmt.setDate(7, admVO.getAdmBday());
			pstmt.setBoolean(8, admVO.getSupvsr());
			pstmt.setInt(9, admVO.getAdmId());

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}

		}

	}

	@Override
	public void delete(Integer admId) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, admId);

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}

	@Override
	public AdmVO findByPrimaryKey(Integer admId) {
		AdmVO admVO = new AdmVO();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, admId);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				admVO.setAdmId(rs.getInt("adm_id"));
				admVO.setAdmName(rs.getString("adm_name"));
				admVO.setAdmEmail(rs.getString("adm_email"));
				admVO.setAdmPassword(rs.getString("adm_password"));
				admVO.setAdmPhone(rs.getString("adm_phone"));
				admVO.setAdmSta(rs.getBoolean("adm_sta"));
				admVO.setHrDate(rs.getDate("hr_date"));
				admVO.setAdmBday(rs.getDate("adm_bday"));
				admVO.setSupvsr(rs.getBoolean("supvsr"));
			}

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return admVO;
	}

	@Override
	public List<AdmVO> getAll() {
		List<AdmVO> list = new ArrayList<AdmVO>();
		AdmVO admVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 也稱為 Domain objects
				admVO = new AdmVO();
				admVO.setAdmId(rs.getInt("adm_id"));
				admVO.setAdmName(rs.getString("adm_name"));
				admVO.setAdmEmail(rs.getString("adm_email"));
				admVO.setAdmPassword(rs.getString("adm_password"));
				admVO.setAdmPhone(rs.getString("adm_phone"));
				admVO.setAdmSta(rs.getBoolean("adm_sta"));
				admVO.setHrDate(rs.getDate("hr_date"));
				admVO.setAdmBday(rs.getDate("adm_bday"));
				admVO.setSupvsr(rs.getBoolean("supvsr"));
				list.add(admVO); // Store the row in the list
			}

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}
}
