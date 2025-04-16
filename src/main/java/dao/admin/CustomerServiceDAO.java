package dao.admin;

import java.util.List;

import dto.admin.AdminAnswer;
import dto.admin.AdminQuestion;
import dto.admin.CustomerService;

public interface CustomerServiceDAO {
	List <CustomerService> selectCSList() throws Exception;
	AdminQuestion selectCS(Long questionNum) throws Exception;
	void insertAnswer(AdminAnswer adminAnswer) throws Exception;
	AdminAnswer selectAdminAnswer(Long questionNum) throws Exception;
	void updateAdminAnswer(AdminAnswer adminAnswer) throws Exception;
}
