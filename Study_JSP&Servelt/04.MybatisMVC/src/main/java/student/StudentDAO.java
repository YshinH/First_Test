package student;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

public class StudentDAO {
	Connection conn;
	PreparedStatement ps;
	ResultSet rs;
	String sql;
	StudentDTO dto;
	
	//ArrayList<StudentDTO> 를 10건 리턴하는 메소드 작성
	//getManualList();
	
	//접근제한자 리턴타입 메소드이름(){
	//}
	
	//getConn(), dbClose(), Connection, StateMent, ResulSet
	//를 여기 프로젝트에 가져와서 연결이 되는지 확인하고 dbClose해보기
	public Connection getConn() {
		String url = "jdbc:oracle:thin:@221.144.89.105:3301:XE";
		String user = "hanul";
		String password = "0000";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(url, user, password);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("getConn Error!");
		}
		return conn;
		
	}
	
	public void dbClose() {
		try {
			
			if(rs!=null) {
				rs.close();
			}
			if(ps!=null) {
				ps.close();
			}
			if(conn!=null) {
				conn.close();
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void selectOne() {
		conn = getConn();
		sql = "SELECT 1 as num FROM dual";
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				System.out.println(rs.getInt("num"));
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	

		
	
	//수동 ↓
//	public ArrayList<StudentDTO> getManualList(){
//		ArrayList<StudentDTO> list = new ArrayList<StudentDTO>();
//		
//		for(int i = 0; i <10; i++) {
//			list.add(new StudentDTO("a", "b", "c", "d", "e", i));
//			
//		}
//		
//		return list;
//	}
	
	//실제 있는 데이터를 가져오는 getList라는 메소드를 만들고 해당하는 메소드를 
	//이용해서 실제 데이터 활용하기
	public ArrayList<StudentDTO> getLIst(){
		ArrayList<StudentDTO> list = new ArrayList<StudentDTO>();
		getConn();
		String sql = "SELECT u.* , s.student_name "
				+ " FROM USER_INFO u left outer join STUDENT s on u.STUDENT_NO = s.STUDENT_NO";
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery(); 
			while(rs.next()) {
				list.add(new StudentDTO(
						rs.getString("student_name"), 
						rs.getString("user_id"), 
						rs.getString("user_pw"), 
						rs.getString("first_name"), 
						rs.getString("last_name"), 
						rs.getInt("student_no")
						));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			dbClose();
		}
		
		return list;
	}
	
	//HttpServletRequest req = Controller.req;
	//String abc = Controller."";
	//1.String student_no, user_id; ★★★★★★재활용이 가능한구조
									//2번.HttpServletRequest req						
	public StudentDTO getStudentInfo(String student_no, String user_id) {// 해당하는 메소드가 실행될때 필요한 변수를 어떤곳에 입력받아서 사용하기.
		//데이터베이스에 접근해서 학생 한명의 정보를 얻어오는 비지니스로직을 구현(데이터 한건 얻어오기)
//		StudentDTO dto = null; 전역변수로 빼줌
		getConn();
		sql = "SELECT u.*, s.student_name FROM USER_INFO u left outer join STUDENT s ON u.STUDENT_NO = s.STUDENT_NO WHERE u.student_no = ? AND u.user_id = ?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, student_no);
			ps.setString(2, user_id);
//			ps.setString(1,req.getParameter("student_no"));	req를 이용하면 항상 req.getparameter가 있는 경우에만 이용이가능
															//재활용이나 여러 플랫폼에서 활용하기엔 불편함
//			ps.setString(2,req.getParameter("user_id"));
			rs = ps.executeQuery();
			while(rs.next()) {
				System.out.println("DAO에서 DTO한건의 내용을 조회중" + rs.getString("student_name"));
				dto = new StudentDTO(rs.getString("student_name"),
									 rs.getString("user_id"), 
									 rs.getString("user_pw"), 
									 rs.getString("first_name"), 
									 rs.getString("last_name"), 
									 rs.getInt("student_no"));
				dto.setAdmin_yn(rs.getString("admin_yn"));
				dto.setMoney(rs.getInt("money"));
				dto.setCreate_ymd(rs.getString("create_ymd"));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}finally {
			dbClose();
		}

		return dto;
		
		
	}

	public StudentDTO getStudentUpdate(String student_no, String user_id) {
		conn = getConn();
		 
		
		return null;
	}
	
	
}
