package customer;

import java.util.List;

import common.CommonDAO;

public class CustomerDAO extends CommonDAO {
	
	public List<CustomerDTO> getList(){
		List<CustomerDTO> list = sql.selectList("cus.list");
		return list;
	}

	public int delete(String id) {
		return sql.delete("cus.delete",id);
	}
	
}
