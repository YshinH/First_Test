package pack04.odjbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Ex01_TCH {
	
	// Oracle<->Java DataBase  Connection
	// DAO , DTO
	
	// Connection <= 연결로를 만드는 객체 ( 연결 객체 )
	// PreparedStatement <= 어떤 데이터를 전송하기위한 객체 ( 연결된 연결객체를 통해서 전송함)
	// ResultSet <= 전송 하고 나서 응답을 받으면 그결과를 담는 객체 ( 결과 객체 )
	
	public static void main(String[] args) {
		Ex01_TCH et = new Ex01_TCH();
		et.connTest();
	}
	// Connection 연결 객체 사용해서 통신 되는지 체크 
	public boolean connTest() {// url , user , password , Ojdbc Class ( 동적 로딩 =자주안씀)
		
		//oracle.jdbc.driver.OracleDriver
		String url = "jdbc:oracle:thin:@127.0.0.1:1521:XE";
		String user = "hanul";
		String password = "0000";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");//필요한 드라이버를 동적으로 로딩함.
			Connection conn = DriverManager.getConnection(url, user, password);
			if(conn.isClosed()) {// is <= boolean을 리턴하는 네이밍룰
				System.out.println("닫힘");
			}else {
				System.out.println("열림");
				
				PreparedStatement ps = 
						conn.prepareStatement(" select 1 num from dual ");
				ResultSet rs = ps.executeQuery();
				while(rs.next()) {
					System.out.println(rs.getInt("num")); // <= ↓ 보다는 안전 
					System.out.println(rs.getInt(1)); // <= 이방식은 많이 위험함 ?
					
				}
				
				conn.close();//통신을 닫음 ※ 동시에 접속할수있는 숫자가 정해져있음 ※
				if(conn.isClosed()) System.out.println("닫힘(내가 닫음)");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	
	public boolean connTest2() {// url , user , password , Ojdbc Class ( 동적 로딩 =자주안씀)
		
		//oracle.jdbc.driver.OracleDriver
		String url = "jdbc:oracle:thin:@127.0.0.1:1521:XE";
		String user = "hanul";
		String password = "0000";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");//필요한 드라이버를 동적으로 로딩함.
			Connection conn = DriverManager.getConnection(url, user, password);
			if(conn.isClosed()) {// is <= boolean을 리턴하는 네이밍룰
				System.out.println("닫힘");
			}else {
				System.out.println("열림");
				
				PreparedStatement ps = 
						conn.prepareStatement(" select 1 num from dual ");
				ResultSet rs = ps.executeQuery();
				while(rs.next()) {
					System.out.println(rs.getInt("num")); // <= ↓ 보다는 안전 
					System.out.println(rs.getInt(1)); // <= 이방식은 많이 위험함 ?
					
				}
				
				conn.close();//통신을 닫음 ※ 동시에 접속할수있는 숫자가 정해져있음 ※
				if(conn.isClosed()) System.out.println("닫힘(내가 닫음)");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	
}
