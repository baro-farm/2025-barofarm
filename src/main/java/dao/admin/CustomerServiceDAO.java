package dao.admin;

import java.util.List;
import java.util.Map;

import dto.admin.AdminAnswer;
import dto.admin.AdminQuestion;
import dto.admin.CustomerService;

public interface CustomerServiceDAO {
	List <CustomerService> selectCSList(Map<String, Object> param) throws Exception;
	int countCustomerService(Map<String, Object> param) throws Exception;
	AdminQuestion selectCS(Long questionNum) throws Exception;
	void insertAnswer(AdminAnswer adminAnswer) throws Exception;
	AdminAnswer selectAdminAnswer(Long questionNum) throws Exception;
	void updateAdminAnswer(AdminAnswer adminAnswer) throws Exception;
}
