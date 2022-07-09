package student;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import oracle.jdbc.driver.OracleDriver;


public class StudentDAO {
	Connection conn;
	PreparedStatement ps;
	ResultSet rs;
	String sql;
	
	public Connection getConn() {
		String url = "jdbc:oracle:thin:@221.144.89.105:3301:XE";
		String user = "hanul";
		String password = "0000";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(url, user, password);
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("getConn Error !");
		}
		return conn;
	
	}//DB연결
	
	public void dbClose() {
			try {
				if(rs != null) {
					rs.close();
					
				}
				if(ps != null) {
					ps.close();
				
				}
				if(conn != null) {
					conn.close();
					
				}
					
			} catch (SQLException e) {
				e.printStackTrace();
			}
		
	}//DB끄기!!
	

	
	//ArrayList<StudentDTO> 를 10건 리턴하는 메소드 작성
	//getManualList();
	
	//접근제한자 리턴타입 메소드이름(){
	//}
	
	public ArrayList<StudentDTO> getManualList(){
		ArrayList<StudentDTO> list = new ArrayList<StudentDTO>();
		
		for(int i = 0; i <10; i++) {
			list.add(new StudentDTO(0, "a", "b", "c", "d", "e"));
			
		}
		
		return list;
	}
	
	public void selecOne() {
		conn = getConn();
		String sql = "select 1 as num1 from dual";
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				System.out.println(rs.getInt("num1"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
	}//연결테스트
	
	
	
	public ArrayList<StudentDTO> getList() {
		conn = getConn();
		ArrayList<StudentDTO> list = new ArrayList<StudentDTO>();
		
		sql = "SELECT s.student_name, u.* FROM user_info u LEFT OUTER JOIN student s ON u.student_no = s.student_no";
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {				
				list.add(new StudentDTO(rs.getInt("student_no") , 
										rs.getString("student_name"), 
										rs.getString("user_id"), 
										rs.getString("user_pw"), 
										rs.getString("last_name"), 
										rs.getString("first_name")));
			
				
			}
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}finally {
			dbClose();
		}
		
		return list;
	}

	public StudentDTO getStudentInfo(String student_no, String user_id) {
		StudentDTO dto = null;
		conn = getConn();
		sql = " SELECT u.* , s.student_name  FROM USER_INFO u left outer join STUDENT s on u.STUDENT_NO = s.STUDENT_NO"
				+ " WHERE  u.STUDENT_NO= ? AND u.USER_ID=? ";
		try {
			
			ps = conn.prepareStatement(sql);
			ps.setString(1, student_no);
			ps.setString(2, user_id);
			
		//	ps.setString(1, req.getParameter("student_no")); req를 이용하면 항상 req.getparamter가 있는경우에만 이용이가능
														  //재활용이나 여러 플랫폼에서 활용하기엔 불편함
		//	ps.setString(2, req.getParameter("user_id"));
			rs = ps.executeQuery();
			
			while(rs.next()) {
				dto = new StudentDTO(
						rs.getInt("student_no"),
						rs.getString("student_name"), 
						rs.getString("user_id"), 
						rs.getString("user_pw"), 
						rs.getString("first_name"), 
						rs.getString("last_name") 
						);
				dto.setCreate_ymd(rs.getString("create_ymd"));
				dto.setUpdate_ymd(rs.getString("update_ymd"));
				dto.setAdmin_yn(rs.getString("admin_yn"));
				dto.setMoney(rs.getInt("money"));
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//데이터베이스에 접근해서 학생 한명의 정보를 얻어오는 비지니스로직을 구현(데이터 한건 얻어오기)
		
		return dto;
	}//학생정보

	public int modifyInfo(StudentDTO dto) {
		conn = getConn();
		sql = "UPDATE user_info SET last_name = ?, first_name = ? WHERE student_no = ? AND user_id = ?";
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, dto.getLast_name());
			ps.setString(2, dto.getFirst_name());
			ps.setInt(3, dto.getStudent_no());
			ps.setString(4, dto.getUser_id());
			
			return ps.executeUpdate();
		
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			dbClose();
		}
		
		return 0;
		
	}//수정

	public int deleteInfo(String student_no, String user_id) {
		conn = getConn();
		sql = "DELETE FROM user_info WHERE student_no = ? AND user_id = ?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, student_no);
			ps.setString(2, user_id);
			
			return ps.executeUpdate();
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}finally {
			dbClose();
		}
		
		return 0;
	}//삭제


	
	
	
	
}//class
