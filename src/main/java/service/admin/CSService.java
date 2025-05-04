package service.admin;

import java.util.List;
import java.util.Map;

import dto.admin.AdminAnswer;
import dto.admin.AdminQuestion;
import dto.admin.CustomerService;

public interface CSService {
	List<CustomerService> allCustomerService(Map<String, Object> param) throws Exception;
	int countCustomerService(Map<String, Object> param) throws Exception;
	AdminQuestion selectCustomerService(Long questionNum) throws Exception;
	void writeAnswer(AdminAnswer adminAnswer) throws Exception;
	AdminAnswer selectAdminAnswer(Long questionNum) throws Exception;
	void updateAdminAnswer(AdminAnswer adminAnswer) throws Exception;
} 
