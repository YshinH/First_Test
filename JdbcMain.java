package pack04.odjbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcMain {
	public static void main(String[] args) {
		
		JdbcDAO dao = new JdbcDAO();
		
//		String val = null;	//null (식별자를 미리 지정해둠, 멤버들이 메모리에 안올라감) 변수이름으로 예약만 하겠다.
		// new 객체타입();
//		if(val != null) {
//		System.out.println(val.length());
//		}else {
//			System.out.println("null");
//		}
		
		//Connection conn = dao.getConn();
//		System.out.println( dao.getInfo().getStudent_name());
		
		//CRUD(Create Read(완) Update Delete)
		//	  (INSERT SELECT(완) UPDATE DELETE 의 기능을 수행한다)
		
		dao.insertUser();
		//dao.updateUser();
		//dao.deleteUser();
		
		//	  (새글쓰기, 조회하기, 수정, 삭제)
		
		
		
//		 try { if (!conn.isClosed()) { System.out.println("열림");
//		  
//		  } else { System.out.println("닫힘"); } } catch (SQLException e) {
//		  e.printStackTrace(); }
		
		//dao.getList();


	}
}
