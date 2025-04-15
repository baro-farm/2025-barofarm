package service;

import dao.UserDAO;
import dao.UserDAOImpl;
import dao.buyer.AddressDAO;
import dao.buyer.AddressDAOImpl;
import dto.User;
import dto.buyer.Address;

public class UserServiceImpl implements UserService{
	private UserDAO userDao;
	private AddressDAO addressDao;

	public UserServiceImpl() {
		 userDao = new UserDAOImpl();
		 addressDao = new AddressDAOImpl();
	}
	
	@Override
	public void join(User user, Address address) throws Exception {
		
		 User exist = userDao.selectUser(user.getUserId());
		 if (exist != null) throw new Exception("이미 존재하는 아이디입니다.");
		    
		 userDao.insertUser(user);         // 유저 저장
		 address.setUserNum(user.getUserNum());
		 System.out.println("생성된 userNum: " + user.getUserNum()); 
		 addressDao.insertAddress(address); // 주소 저장
	}
	
	@Override
	public void login(String id, String password) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean checkDoubleId(String id) throws Exception {
		User user = userDao.selectUser(id);
		if(user==null) return false;
		return true;
	}

}
