package service.admin;

import java.util.List;
import java.util.Map;

import dao.admin.UserDAO;
import dao.admin.UserDAOImpl;
import dto.User;

public class UserServiceImpl implements UserService {
	private UserDAO userDao;
	
	public UserServiceImpl() {
		userDao = new UserDAOImpl();
	}

	@Override
	public List<User> allUser(Map<String, Object> param) throws Exception {
		return userDao.selectUserList(param); 
	}

	@Override
	public int countUser(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return userDao.countUser(param);
	}
}
