package customer;

import java.util.List;

import common.CommonDAO;

public class CustomerDAO extends CommonDAO{

	CustomerDTO dto = new CustomerDTO();
//	static SqlSession sql;	//CommonDAO를 상속받아서 필요없다
//	static {
//
//		try {
//			String resource = "mybatis/config.xml";
//			InputStream inputStream = Resources.getResourceAsStream(resource);
//			SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
//			sql = sqlSessionFactory.openSession(true);	//AutoCommit설정을 true
//			
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
	
	
	public List<CustomerDTO> getList(){
		List<CustomerDTO> list = sql.selectList("cus.list");

		return list;
		
	}

	public int delete(String id) {
		
	return sql.delete("cus.delete", id);
	
	}

	public void update(String parameter) {
		// TODO Auto-generated method stub
		
	}
}
