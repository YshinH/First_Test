package pack05.ojdbc2;

import java.sql.SQLException;

public class UserInfoDAO extends StudentDAO{
	//현 StrudentDAO를 상속받아서 썼다면 나중에는 접속객체를 초기화 접속객체닫기 등등을
	//하나의 공통 클래스로 콜아 넣기.(재사용이 편함)
	
	//공통으로 사용되는 로직은 commons, common 등등 공통이라는 패키지를 별도로 두고
	//프로젝트 인원들이 같이 사용하도록 유도함.(디비적속, 숫자를 리턴하는 메소드 등등)
	
	//로그인
	public UserInfoDTO getLogin() {
		conn = getConn();
		String sql = "";	// ? SqlDeveloper에서 로그인 처리를 하게 만들려면 어떻게 해야할까?
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return null;
	}
	
	//회원가입
	public boolean joinUser() {
		
		
		
		return false;
		
	}
		
}
