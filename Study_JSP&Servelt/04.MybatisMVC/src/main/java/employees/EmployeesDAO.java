package employees;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class EmployeesDAO {

	private static SqlSession sql;
	static {
		try {
			String resource = "mybatis/config2.xml";
			InputStream inputStream = Resources.getResourceAsStream(resource);
			SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
			sql = sqlSessionFactory.openSession();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void test2() {
		int result = sql.selectOne("test2.selectTest");
		
		System.out.println(result);
	}
	
	public List<EmployeesDTO> getList(){
		
		List<EmployeesDTO> list = sql.selectList("hr.selectlist");
		System.out.println(list.size());
		
		return list;
	}
	
	
	
	
	
}//class
