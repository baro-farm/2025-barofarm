package service.buyer;

import java.util.ArrayList;
import java.util.List;

import dao.buyer.UserDAO;
import dao.buyer.UserDAOImpl;
import dto.buyer.Address;
import vo.UserVO;

public class UserServiceImpl implements UserService {
	private UserDAO userDao;
	 
	public UserServiceImpl() {
		userDao = new UserDAOImpl();
	}
	
	@Override
	public UserVO selectUserInfo(String userId) throws Exception {
		// TODO Auto-generated method stub
		return userDao.selectUser(userId);
	}

	@Override
	public void updateUserInfo(UserVO user) throws Exception {
		userDao.updateUser(user);
	}
	
	public void insertUserAddress(Address address) throws Exception{
		userDao.insertAddress(address);
	}

	@Override
	public List<Address> selectUserAddressList(String userId) throws Exception {
		List<Address> addressList = new ArrayList<>();
		addressList = userDao.selectAddressList(userId);
		return addressList;
	}

	@Override
	public Address selectUserAddress(Long addrNum) throws Exception {
		return userDao.selectAddress(addrNum);
	}

	@Override
	public void updateUserAddress(Address address) throws Exception {
		 userDao.updateAddress(address);
	}

	@Override
	public void deleteUserAddress(Long addrNum) throws Exception {
		userDao.deleteAddress(addrNum);
	}

	@Override
	public Long selectUserNumByUserId(String userId) throws Exception {
		// TODO Auto-generated method stub
		return userDao.selectUserNumById(userId);
	}

}
