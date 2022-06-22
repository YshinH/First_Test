package pack05.ojdbc2;

import java.sql.SQLException;
import java.util.Scanner;

public class UserInfoDAO extends StudentDAO{
	//현 StrudentDAO를 상속받아서 썼다면 나중에는 접속객체를 초기화 접속객체닫기 등등을
	//하나의 공통 클래스로 몰아 넣기.(재사용이 편함)
	
	//공통으로 사용되는 로직은 commons, common 등등 공통이라는 패키지를 별도로 두고
	//프로젝트 인원들이 같이 사용하도록 유도함.(디비적속, 숫자를 리턴하는 메소드 등등)
	
	
	//<로그인>
	
	//2. 로그인 처리를 위한 메소드를 만들기 loginUser(); // 파라메터가 필요할까?
	//(( ↑), true false를 이용해서 true가 나오면 로그임 됨 <-이것만정답 x
	//      , -1은 확실히 실패 그외에는 성공(jdbc) ..
	String sql;

	public UserInfoDTO loginUser(String id, String pw) {	// 네이버 : 아이디 입력, 비밀번호 입력
		//연결 - > 전송-> 결과
		
		UserInfoDTO dto = new UserInfoDTO();
		conn = getConn();
		sql = "SELECT * FROM user_info WHERE user_id = ? AND user_pw = ? ";	// ? SqlDeveloper에서 로그인 처리를 하게 만들려면 어떻게 해야할까?
		//물음표 갯수 <=> ps.set갯수 맞춰줌
		try {
			//엔터를 안치고 활용함
			ps = conn.prepareStatement(sql);
			ps.setString(1, id); //왼쪽을 기준으로 첫번째 물음표를 찾고 그 물음표에 어떤 값을 넣어서 보냄
			ps.setString(2, pw); //왼쪽을 기준으로 두번째 물음표를 찾고 그 물음표에 어떤 값을 넣어서 보냄
			rs = ps.executeQuery(); //정보를 받음
			while(rs.next()) {
				dto.setUser_id(rs.getString("user_id"));
				dto.setUser_pw(rs.getString("user_pw"));
				dto.setFirst_name(rs.getString("first_name"));
				dto.setLast_name(rs.getString("last_name"));
			}//while
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			dbClose();
		}

		return dto;
	}
	
	
	//회원가입
	public UserInfoDTO joinUser(UserInfoDTO dto) {	
		//1.sqld <- 되는 insert 쿼리를 만든다. rollback, commit을 꼭함
		//2.java 작업(dto에 사용자가 입력한 정보들을 묶어서 저장)
		//3.java 작업(dto를 메소드에 넘기고 출력해보기)
		
		//메인 회원정보를 입력받고 => 입력받은 결과로 회원가입처리.
		//회원 가입 처리(CREATE_YMD, sysdate)
		conn = getConn();
		
		sql = "INSERT INTO USER_INFO VALUES(7,?,?,?,?,sysdate,sysdate,'N',0)";
		try {
			ps = conn.prepareStatement(sql);			
			ps.setString(1, dto.getUser_id());
			ps.setString(2, dto.getUser_pw());
			ps.setString(3, dto.getFirst_name());
			ps.setString(4, dto.getLast_name());
			System.out.println(ps.executeUpdate());
			
			System.out.println("회원가입을 성공하셨습니다.");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			dbClose();
		}

	
		return dto;
	}
	
	//2.1 회원정보 보기
	public void infoUser(UserInfoDTO dto) {
		System.out.println("번호 : " + dto.getStudent_no());
		System.out.println("아이디 : " + dto.getUser_id());
		System.out.println("비밀번호 : " + dto.getUser_pw());
		System.out.println("이름 : "+ dto.getFirst_name());
		System.out.println("성 : " + dto.getLast_name());

	}
	
	//2.2 수정
	public UserInfoDTO updateUser(UserInfoDTO dto) {
		
		conn = getConn();
		
		sql = "UPDATE user_info SET user_pw =?,first_name = ?, last_name = ? WHERE user_id = ?";
		try {
			ps = conn.prepareStatement(sql);
			//if()
		    ps.setString(4, dto.getUser_id());
			ps.setString(1, dto.getUser_pw());
			ps.setString(2, dto.getFirst_name());
			ps.setString(3, dto.getLast_name());
			ps.executeUpdate();
			System.out.println("회원정보가 수정되었습니다.");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			dbClose();
		}

		return dto;
	}//updateUser()
	
	//삭제
	public void deleteUser(UserInfoDTO joinDto) {
		conn = getConn();
		sql = "DELETE FROM user_info WHERE user_id = ? and user_pw = ?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, joinDto.getUser_id());
			ps.setString(2, joinDto.getUser_pw());
			ps.executeUpdate();
			System.out.println("삭제가 완료되었습니다.");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	
	//사용자에게 어떤 메세지를 보여줌과 동시에 스캐너로 값을 입력받음.
	public String rtnStrMsg(Scanner sc , String msg) {
		System.out.println(msg);
		return sc.nextLine();
	}


	//DAO에 메소드 이름 등등 전부다 맘대로 작성
	//메뉴추가 후 회원정보 수정하기.
	//※ 키값은 수정이 되면 x
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}//class