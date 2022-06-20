package pack05.ojdbc2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import pack04.odjbc.JdbcDAO;

public class StudentDAO {

	 Connection conn;
	 PreparedStatement ps;
	 ResultSet rs;
	

	//1. 학생 정보를 전체 조회할수있는 메소드 형태를 만드세요. getStudentList();
	//(전체 정보를 어떤 클래스에서 호출하든 사용가능해야함)
	// DTO = (변수)  변수 < = 한가지값만 가짐 [번호, 이름] Collection ArrayList
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
		
	}//복붙했으니 따로 익히기
	
	public ArrayList<StudentDTO> getStudentList() {
		//Connection객체 등등을 이용해서 DB에서 정보를 가지고옴 그결과를 리턴
		//출력은 메인클래스 또는 DAO에 viewList라는 메소드를 통해서 작업
	
		conn = getConn();
		String sql = " Select * From STUDENT ";
		ArrayList<StudentDTO> list = new ArrayList<StudentDTO>();
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {	//rs가 다음칸으로 이동했다면  true라고 봄
				// 조회해왔는지 확인용 System.out.println(rs.getInt("STUDENT_NO") + " " + rs.getString("STUDENT_NAME"));
				StudentDTO dto = new StudentDTO(rs.getInt("STUDENT_NO"),rs.getString("STUDENT_NAME"));
				//list.add(dto);	//list에 add로 dto의 값을 넣음
				//주석으로 처리했기 때문에 값이 없음
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			dbClose();
		}

		return list; 
	}

	//viewList 라는 메소드는 조회 된 결과를 전체 콘솔창에 출력하는 메소드임			// 다시 이해하기
	public void viewList(ArrayList<StudentDTO> list) {
		
		if(list == null || list.size() == 0) {
			System.out.println("보여줄 목록이 없습니다.");
			return ;
		}
		
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i).getStudent_no() + "." + list.get(i).getStudent_name());
		}
	}
	
	
	//2. 로그인 처리를 위한 메소드를 만들기 loginUser(); // 파라메터가 필요할까?
	//(( ↑), true false를 이용해서 true가 나오면 로그임 됨 <-이것만정답 x
	//      , -1은 확실히 실패 그외에는 성공(jdbc) ..
	public boolean loginUser(String id, String pw) {	// 네이버 : 아이디 입력, 비밀번호 입력
		
		
		
		return false;
	}
	
	//Hash맵 가능, 어레이리스트도 가능
	

	
	//?????? JDBC통해서 연결과 연결해제 등등의 기능이 필요하다면 가지고 와야함.
	//API의 경우 내가 직접 코딩하는게 적음(==> 이미 만들어진기능을 호출해서 사용)
	//따라서 어떤게 필요한지를 외우고 빠지면 복붙해도 상관, 규칙(연결, 전송, 결과)
	
	
	
//	public ArrayList<String> getListString(){
//		return null;
//		
//	}
//	
//	public ArrayList<Integer> getListInteger(){
//		return null;
//	}
//	
//	public String getString() {
//		return null;
//	}
//	
//	public void voidMethod() {
//		return new ArrayList<E>(); 리턴타입바꿔주라
//	}
	
	//내가 알고 있는 어떤 것이든 메소드의 리턴타입이 될수 있으니 한계를 두고 생각을 닫지 말자.
	
	//public, protected, default(생략), private = (접근제한자)
	//멤버의 구분( static이 붙었는지 안붙었는지) = 인스턴스화 해야 사용, 안해도 사용가능
	//리턴 타입  ( void 이 붙었는지 안붙었는지) = 리턴을 하는지 안하는지 구분
	//메소드 이름( 소문자로 시작해서 그 기능을 구별할 수 있는 단어가 좋다) aaa() X, getUserInfo()
	//파라메터부( 메소드가 실행될 때 필요한 매개변수(파라메터)가 있다면 넘겨주는 부분
	
}
