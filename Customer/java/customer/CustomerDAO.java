package customer;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class CustomerDAO {
	//기존 - JDBC api(lib)사용 -> Connection초기화, Statement(전송)초기화 쿼리 넣기 => 전송 => ResultSet(결과), int(결과)
	
	//Mybatis↑(이용, 관리, 가독성 등등의 장점을 추가한 API)
	//환경설정이 좀 까다롭다는 단점이 있음
	//xml파일로 환경설정, String sql x => xml mapper라는 쿼리의 집합 파일을 만들어서 관리 ※
	//자바 파일이나 여러가지 방식으로 환경설정을 할수가 있으나 제일 간단한 방법은 xml(선생님생각)
	
	//환경설정
	//1. maven에서 해당하는 api를 프로젝트에 넣어주기(jar) pom.xml ※ 프로젝트에  JDBC(ojdbc11)는 반드시 있어야함 
	//2. mybatis 문서를 보고 dtd(xml)먼저 만들기 https://mybatis.org/mybatis-3/ko/getting-started.html
	
	private static SqlSession sql; //전송 결과 처리를 하는 객체(sqlSessionFactory를 이용해서 초기화)
	static {
		try {
			String resource = "mybatis/config.xml"; //mybatis dtd 설정이 들어있는 파일(이파일을 이용해서 환경설정을 로딩)
			InputStream inputStream = Resources.getResourceAsStream(resource); //InputStream에서 해독하여
			SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);	//데이터베이스와 연결하는부분	
			sql = sqlSessionFactory.openSession(); //Connection객체 이용해서 통신열기랑 같은 처리
			} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void test() {//sql.selectOne("mapperNamespace.sql(id)")
		int result = sql.selectOne("test.selectTest");//목록이 아니라( 데이터베이스 조회했을때 행갯수가 1개인것 조회)
		System.out.println(result);
	}
	
	public List<CustomerDTO> getList(){
		//sql.update, sq.delete, sql.selectone, selectlist(return 타입이 List)
		List<CustomerDTO> list = sql.selectList("cus.listselect");
		System.out.println(list.size());
		

		return list;
	}
	
	
	
}//class