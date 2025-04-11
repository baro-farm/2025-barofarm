package service.buyer;

import dao.buyer.UserDAO;
import dao.buyer.UserDAOImpl;
import dto.buyer.User;

public class UserServiceImpl implements UserService {
	private UserDAO userDao;
	
	public UserServiceImpl() {
		userDao = new UserDAOImpl();
	}
	
	@Override
	public User selectUserMyInfo(String userId) throws Exception {
		// TODO Auto-generated method stub
		return userDao.selectUserInfo(userId);
	}

}
