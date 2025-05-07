package service.admin;

import java.util.List;
import java.util.Map;

import dao.admin.CustomerServiceDAO;
import dao.admin.CustomerServiceDAOImpl;
import dto.admin.AdminAnswer;
import dto.admin.AdminQuestion;
import dto.admin.CustomerService;

public class CSServiceImpl implements CSService {
	private CustomerServiceDAO csDao;
	
	public CSServiceImpl() {
		csDao = new CustomerServiceDAOImpl();
	}
	
	@Override
	public List<CustomerService> allCustomerService(Map<String, Object> param) throws Exception {
		return csDao.selectCSList(param);
	}
	
	@Override
	public int countCustomerService(Map<String, Object> param) throws Exception {
		return csDao.countCustomerService(param);
	}
	
	@Override
	public AdminQuestion selectCustomerService(Long questionNum) throws Exception {
		return csDao.selectCS(questionNum);
	}
	
	@Override
	public void writeAnswer(AdminAnswer adminAnswer) throws Exception {
		csDao.insertAnswer(adminAnswer);
		
	}
	
	@Override
	public AdminAnswer selectAdminAnswer(Long questionNum) throws Exception {
		return csDao.selectAdminAnswer(questionNum);
	}
	
	@Override
	public void updateAdminAnswer(AdminAnswer adminAnswer) throws Exception {
		csDao.updateAdminAnswer(adminAnswer);		
	}
}
