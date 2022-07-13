package member;

import java.io.IOException;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MemberDAO {
	//쿼리문실행기능을 가진 클래스. SqlSessionTemplate
	public static SqlSession sql;
	static {
		String resource = "mybatis/config.xml";
		try {
			SqlSessionFactory factory
			= new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream(resource));
			sql = factory.openSession();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}//static
	
	//CRUD(Create, Read, Update, Delete)
	//회원가입
	public int member_join(MemberDTO dto) {
		
		return 0;
	}
	
	//내정보보기
	public MemberDTO member_myinfo(String userid) {
		
		return null;
	}
	
	//아이디 중복확인 count로 숫자로 돌려주기
	public int member_id_check(String userid) {
		
		return 0;	
	}
	
	//내정보변경
	public int member_update(MemberDTO dto) {
		
		return 0;
	}
	
	//회원탈퇴
	public int member_delete(String userid) {
	
		return 0;
	}
	
	
	
	
	
	
}//class
