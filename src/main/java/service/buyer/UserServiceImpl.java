package service.buyer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import dao.buyer.UserDAO;
import dao.buyer.UserDAOImpl;
import dto.buyer.Address;
import util.MybatisSqlSessionFactory;
import vo.UserVO;

public class UserServiceImpl implements UserService {

	private UserDAO userDao(SqlSession sqlSession) {
		return new UserDAOImpl(sqlSession);
	}

	@Override
	public UserVO selectUserInfo(String userId) throws Exception {
		try (SqlSession sqlSession = MybatisSqlSessionFactory.getSqlSessionFactory().openSession()) {
			return userDao(sqlSession).selectUser(userId);
		}
	}

	@Override
	public void updateUserInfo(UserVO user) throws Exception {
		try (SqlSession sqlSession = MybatisSqlSessionFactory.getSqlSessionFactory().openSession(false)) {

			userDao(sqlSession).updateUser(user);
			sqlSession.commit();
		}
	}

	public void insertUserAddress(Address address) throws Exception {
		try (SqlSession sqlSession = MybatisSqlSessionFactory.getSqlSessionFactory().openSession(false)) {

			userDao(sqlSession).insertAddress(address);
			sqlSession.commit();
		}
	}

	@Override
	public List<Address> selectUserAddressList(String userId) throws Exception {
		try (SqlSession sqlSession = MybatisSqlSessionFactory.getSqlSessionFactory().openSession()) {

			List<Address> addressList = new ArrayList<>();
			addressList = userDao(sqlSession).selectAddressList(userId);
			return addressList;
		}
	}

	@Override
	public Address selectUserAddress(Long addrNum) throws Exception {
		try (SqlSession sqlSession = MybatisSqlSessionFactory.getSqlSessionFactory().openSession()) {

			return userDao(sqlSession).selectAddress(addrNum);
		}
	}

	@Override
	public Address selectDefaultAddress(Long userNum) throws Exception {
		try (SqlSession sqlSession = MybatisSqlSessionFactory.getSqlSessionFactory().openSession()) {

			return userDao(sqlSession).selectDefaultAddress(userNum);
		}
	}

	@Override
	public void updateUserAddress(Address address) throws Exception {
		try (SqlSession sqlSession = MybatisSqlSessionFactory.getSqlSessionFactory().openSession(false)) {

			userDao(sqlSession).updateAddress(address);
			sqlSession.commit();
		}
	}

	@Override
	public void deleteUserAddress(Long addrNum) throws Exception {
		try (SqlSession sqlSession = MybatisSqlSessionFactory.getSqlSessionFactory().openSession(false)) {

			userDao(sqlSession).deleteAddress(addrNum);
			sqlSession.commit();
		}
	}

	@Override
	public Long selectUserNumByUserId(String userId) throws Exception {
		try (SqlSession sqlSession = MybatisSqlSessionFactory.getSqlSessionFactory().openSession()) {
			return userDao(sqlSession).selectUserNumById(userId);
		}
	}

}
