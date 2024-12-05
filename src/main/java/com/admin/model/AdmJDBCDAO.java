package com.admin.model;

import java.util.*;
import java.sql.*;
	
public class AdmJDBCDAO implements AdmDAO_interface {
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/tia104_05_webapp?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "123456";

	private static final String INSERT_STMT = 
			"INSERT INTO admin (adm_email,adm_password,adm_name,adm_phone,hr_date,adm_bday, supvsr) VALUES (?, ?, ?, ?, ?, ?,?)";
	private static final String GET_ALL_STMT = 
			"SELECT adm_id,adm_email,adm_password,adm_name,adm_phone,hr_date, adm_sta, adm_bday, supvsr FROM admin order by adm_id";
	private static final String GET_ONE_STMT = 
			"SELECT adm_id,adm_email,adm_password,adm_name,adm_phone,hr_date, adm_sta, adm_bday, supvsr FROM admin where adm_id = ?";
	private static final String DELETE = 
			"DELETE FROM admin where adm_id = ?";
	private static final String UPDATE = 
			"UPDATE admin set adm_email=?, adm_password=?, adm_name=?, adm_phone=?, adm_sta=?, supvsr=? where adm_id = ?";

	@Override
	public void insert(AdmVO admVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {	

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, admVO.getAdmEmail());
			pstmt.setString(2, admVO.getAdmPassword());
			pstmt.setString(3, admVO.getAdmName());
			pstmt.setString(4, admVO.getAdmPhone());
			pstmt.setDate(5, admVO.getHrDate());
			pstmt.setDate(6, admVO.getAdmBday());
			pstmt.setBoolean(7, admVO.getSupvsr());

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, admVO.getAdmEmail());
			pstmt.setString(2, admVO.getAdmPassword());
			pstmt.setString(3, admVO.getAdmName());
			pstmt.setString(4, admVO.getAdmPhone());
			pstmt.setBoolean(5, admVO.getAdmSta());
			pstmt.setBoolean(6, admVO.getSupvsr());

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, admId);

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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

		AdmVO admVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, admId);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				
				admVO = new AdmVO();
				admVO.setAdmId(rs.getInt("adm_id"));
				admVO.setAdmEmail(rs.getString("adm_email"));
				admVO.setAdmPassword(rs.getString("adm_password"));
				admVO.setAdmName(rs.getString("adm_name"));
				admVO.setAdmPhone(rs.getString("adm_phone"));
				admVO.setHrDate(rs.getDate("hr_date"));		
				admVO.setAdmSta(rs.getBoolean("adm_sta"));
				admVO.setAdmBday(rs.getDate("adm_bday"));
				admVO.setSupvsr(rs.getBoolean("supvsr"));
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 也稱為 Domain objects
				admVO = new AdmVO();
				admVO.setAdmId(rs.getInt("adm_id"));
				admVO.setAdmEmail(rs.getString("adm_email"));
				admVO.setAdmPassword(rs.getString("adm_password"));
				admVO.setAdmName(rs.getString("adm_name"));
				admVO.setAdmPhone(rs.getString("adm_phone"));
				admVO.setHrDate(rs.getDate("hr_date"));		
				admVO.setAdmSta(rs.getBoolean("adm_sta"));
				admVO.setAdmBday(rs.getDate("adm_bday"));
				admVO.setSupvsr(rs.getBoolean("supvsr"));
				list.add(admVO); // Store the row in the list
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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

	public static void main(String[] args) {

		AdmJDBCDAO dao = new AdmJDBCDAO();

		// 新增
//		EmpVO empVO1 = new EmpVO();
//		empVO1.setEname("吳永志1");
//		empVO1.setJob("MANAGER");
//		empVO1.setHiredate(java.sql.Date.valueOf("2005-01-01"));
//		empVO1.setSal(new Double(50000));
//		empVO1.setComm(new Double(500));
//		empVO1.setDeptno(10);
//		dao.insert(empVO1);

		// 修改
//		EmpVO empVO2 = new EmpVO();
//		empVO2.setEmpno(7001);
//		empVO2.setEname("吳永志2");
//		empVO2.setJob("MANAGER2");
//		empVO2.setHiredate(java.sql.Date.valueOf("2002-01-01"));
//		empVO2.setSal(new Double(20000));
//		empVO2.setComm(new Double(200));
//		empVO2.setDeptno(20);
//		dao.update(empVO2);

		// 刪除
//		dao.delete(7014);

		// 查詢
//		EmpVO empVO3 = dao.findByPrimaryKey(7001);
//		System.out.print(empVO3.getEmpno() + ",");
//		System.out.print(empVO3.getEname() + ",");
//		System.out.print(empVO3.getJob() + ",");
//		System.out.print(empVO3.getHiredate() + ",");
//		System.out.print(empVO3.getSal() + ",");
//		System.out.print(empVO3.getComm() + ",");
//		System.out.println(empVO3.getDeptno());
//		System.out.println("---------------------");

		// 查詢
		List<AdmVO> list = dao.getAll();
		for (AdmVO aAdm : list) {
			System.out.print(aAdm.getAdmId() + ",");
			System.out.print(aAdm.getAdmEmail() + ",");
			System.out.print(aAdm.getAdmPassword() + ",");
			System.out.print(aAdm.getAdmName() + ",");
			System.out.print(aAdm.getAdmPhone() + ",");
			System.out.print(aAdm.getHrDate() + ",");
			System.out.print(aAdm.getAdmSta()+ ",");
			System.out.print(aAdm.getAdmBday()+ ",");
			System.out.print(aAdm.getSupvsr());
			System.out.println();
		}
	}
}
