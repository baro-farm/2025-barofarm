package service.admin;

import java.util.List;

import dao.admin.UserDAO;
import dao.admin.UserDAOImpl;
import dto.User;

public class UserServiceImpl implements UserService {
	private UserDAO userDao;
	
	public UserServiceImpl() {
		userDao = new UserDAOImpl();
	}

	@Override
	public List<User> allUser() throws Exception {
		return userDao.selectUserList(); 
	}

}
