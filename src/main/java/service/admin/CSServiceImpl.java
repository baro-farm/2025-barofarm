package service.admin;

import java.util.List;

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
	public List<CustomerService> allCustomerService() throws Exception {
		return csDao.selectCSList();
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
