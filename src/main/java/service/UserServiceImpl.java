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
		 System.out.println("생성된 userNum: " + user.getUserNum()); 
		 address.setUserNum(user.getUserNum());
		 addressDao.insertAddress(address); // 주소 저장
	}

	@Override
	public boolean checkDoubleId(String id) throws Exception {
		User user = userDao.selectUser(id);
		if(user==null) return false;
		return true;
	}

	@Override
	public User login(String userId, String pwd) throws Exception {
		User user = userDao.selectUser(userId);
		if(user==null) throw new Exception("아이디 오류");
		if(!user.getPwd().equals(pwd)) throw new Exception("비밀번호 오류");
		return user;
	}

}
