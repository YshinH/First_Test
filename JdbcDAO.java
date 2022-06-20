package pack04.odjbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class JdbcDAO {
	//데이터베이스에서 데이터를 가지고오는 모든 처리를 담당하는 클래스(객체)
	//가지고 온 데이터를 묶어서 저장하기 위한 클래스(DTO)
	
	// Connection <= 연결로를 만드는 객체( 1.연결 객체)
	// ...StateMent <= ↑ 연결로를 통해서 전송을 하는 객체 (2. 전송 객체★)
	// ResultSet <= 전송객체가 어떤 쿼리를 실행하고나서 결과를 가지고오면 담기위한 객체 (3. 결과)
	
	//Java										Oracle
	private Connection conn;	//열결객체
	private PreparedStatement ps;	//전송객체
	private ResultSet rs; //결과객체
	
	//연결 객체가 필요할때마다 호출을 해서 연결객체를 리턴받는 메소드.
	public Connection getConn() {
		String url = "jdbc:oracle:thin:@221.144.89.105:3301:XE";		//선생님포트번호
		String user = "hanul";
		String password = "0000";
		//oracle.jdbc.driver.OracleDriver ※
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(url, user, password);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("getConn Error !");
		}
		
		return conn;
	}
	

	
	// getConn메소드를 활용해서 221 <= 시작하는 디비에서 dual 테이블 이용 1을 조회해오자.
//	public boolean getInt() {
//		JdbcDAO dao = new JdbcDAO();
//		Connection conn  = dao.getConn();
//		
//		try {
//			PreparedStatement ps = conn.prepareCall("select 3 from student ");
//			ResultSet rs = ps.executeQuery();
//			
//			while (rs.next()) {
//				System.out.println(rs.getInt(1));
//			}
//			conn.close();
//			if(conn.isClosed()) System.out.println("닫힘(내가 닫음)");
//			
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		
//		
//		return false;
//	}
	
	public boolean getInt() {
		dbClose();
		conn = getConn();
		String sql = " select 1 num from dual ";
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				System.out.println(rs.getInt("num"));
				
			}
			//conn.close(); // <= 열었으면 닫는다.
			
		//dbClose();
			
		} catch (Exception e) {
			System.out.println("getInt");
			e.printStackTrace();
		}
		
		return false;
	}
	
	public void dbClose() {
		//어떤 순서대로 닫아야할까? 열때 통신연결로를 열고 전송객체를 보내고 결과 객체 받음.
		//통신을 열때랑 역순으로 닫아주면 된다.
		try {	// == < 참조를 시작했는지를 알수있는
			if(rs != null) {
				rs.close();//3
			}
			if(ps != null) {
				ps.close();//2
			}
			if(conn != null) {
				conn.close();//1
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	//메소드접근 제한자 리턴타입 메소드이름
	//리턴타입있음 => 리턴해줘야함.
	
	//메소드나 멤버(필드) 리터타입제한 X
	
	
	//학생정보를 조회해서 내정보만 따로 가지고오는 메소드를 만든다.
	//DTO를 만드는 이유(하나의 데이터 타입만 return이 가능함
	//Database에는 여러 데이터타입으로 데이터가 존재함(NUMBER,VARCHAR2,Date....)이런것들을
		//한번에 묶어서 처리하기 위한 객체 (DTO) 
	public JdbcDTO getInfo() {
		JdbcDTO dto = null;
		conn = getConn();
		String sql = "Select * "		// 쿼리에서 복붙해오기
				+ "from student";
		
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				System.out.println(rs.getInt("STUDENT_NO"));	//oracle number => int
				System.out.println(rs.getString("STUDENT_NAME"));	//varchar2 => String
				
				dto = new JdbcDTO(rs.getInt("STUDENT_NO"), rs.getString("STUDENT_NAME"));
			}// 이 결과를 JdbcDTO에 담고 return하게 만들어보기
			
//			JdbcDTO dto = new JdbcDTO(rs.getInt(1), rs.getString("STUDENT_NAME"));
//			dto.getStudent_name();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			dbClose();
		}

		return null;	//<= null이 아니라 내가 조회한 데이터를 어디서든 사용할수 있게 리턴해주는 메소드
	}
	
	//학생정보를 조회해서 내정보만 따로 가지고 오는 메소드를 만든다.
//	public JdbcDTO getInfo() {
//		
//		
//		
//		return null;
//	}
//	
	
	
	//학생정보를 전체 조회해서 Array타입을 리턴하는 메소드를 만든다.
	//메소드 이름 형식 등등 마음대로 하기.
	public ArrayList<JdbcDTO> getDTO(){
		 
		JdbcDTO dto = getInfo();
		ArrayList<JdbcDTO> ar = new ArrayList<JdbcDTO>();
		ar.add(dto);
		
		System.out.println(ar.get(0));
		
		return new ArrayList<JdbcDTO>();
	}
	
	public ArrayList<JdbcDTO> getList(){
		
		ArrayList<JdbcDTO> list = new ArrayList<JdbcDTO>();
		
		conn = getConn();
		String sql = "Select * "		// 쿼리에서 복붙해오기
				+ "from student";
		
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				System.out.println(rs.getInt("STUDENT_NO"));	//oracle number => int
				System.out.println(rs.getString("STUDENT_NAME"));	//varchar2 => String
				
				list.add(new JdbcDTO(rs.getInt("STUDENT_NO"), rs.getString("STUDENT_NAME")));
			}// 이 결과를 JdbcDTO에 담고 return하게 만들어보기
			
//			JdbcDTO dto = new JdbcDTO(rs.getInt(1), rs.getString("STUDENT_NAME"));
//			dto.getStudent_name();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			dbClose();
		}

		return null;	//<= null이 아니라 내가 조회한 데이터를 어디서든 사용할수 있게 리턴해주는 메소드
		
	}
	
	
	//JAVA에서 JDBC를 이용해서 insert Query를 ORACLE에 전송하고 oracle은 전송받은 쿼리를 
	//실행하고 그결과를 다시 java쪽에 보내줌
	//연결 <-> 전송 -> 결과
	public int insertUser() {
		conn = getConn();	//커넥션 객체 초기화(연결)
//		Scanner sc = new Scanner(System.in);
//		int num = Integer.parseInt(sc.nextLine());
//		String id = sc.nextLine();
		//String sql = "INSERT INTO USER_INFO(STUDENT_NO,USER_ID,USER_PW,FIRST_NAME,LAST_NAME,CREATE_YMD,UPDATE_YMD) VALUES(7,'YSH','0000','Shin','YOON','22/06/20','22/06/20')";//실행할 SQL
		String sql = "INSERT INTO user_info values(7,'YSH','7979','SHINHYANG','YOON',sysdate,sysdate)";
		try {
			ps = conn.prepareStatement(sql);	//전송객체 초기화
			//SELECT했을때는 executeQuery라는 메소드를 이용하지만
			//I,U,D를 할때는 데이터의 수정이 이루어졌는지의 결과만 필요하기 때문에 다른 메소드사용
			// executeUpdate();<=사용 ( 성공1, 실패 0,-1 ) 
			System.out.println(ps.executeUpdate());	//AUTO COMMIT(자동으로 커밋해버림)

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return 0;
	}
	
	
	//updateUser메소드를 혼자서 만들고 수정되는지 확인해보기(auto commit)
	public int updateUser() {
		conn = getConn();
		String sql = "UPDATE USER_INFO SET FIRST_NAME = 'Shin-Hyang' WHERE STUDENT_NO = 7";
		
		try {
			ps = conn.prepareStatement(sql);
			System.out.println(ps.executeUpdate());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return 0;
	}
	
	//deleteUser메소드
	public int deleteUser() {
		conn = getConn();
		String sql = "DELETE FROM user_info WHERE student_no = 7 and user_id='YSH'";
		
		try {
			ps = conn.prepareStatement(sql);
			System.out.println(ps.executeUpdate());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return 0;
	}
	
	
	
	
	
	
	
	
	
}
