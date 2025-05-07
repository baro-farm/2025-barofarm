package service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import dao.UserDAO;
import dao.UserDAOImpl;
import dao.buyer.AddressDAO;
import dao.buyer.AddressDAOImpl;
import dao.seller.SellerDAO;
import dao.seller.SellerDAOImpl;
import dto.User;
import dto.admin.AdminQuestion;
import dto.admin.Notice;
import dto.buyer.Address;
import dto.seller.SellerDetail;
import util.MybatisSqlSessionFactory;
import util.PageInfo;
import vo.AdminQuestionVO;
import vo.SellerVO;

public class UserServiceImpl implements UserService {
	public UserServiceImpl() {
		// 비어 있어도 무방
	}

	@Override
	public void join(User user, Address address, SellerDetail sellerDetail) throws Exception {
		try (SqlSession sqlSession = MybatisSqlSessionFactory.getSqlSessionFactory().openSession(false)) {
			UserDAO userDao = new UserDAOImpl(sqlSession);
			AddressDAO addressDao = new AddressDAOImpl(sqlSession);
			SellerDAO sellerDao = new SellerDAOImpl(sqlSession);
			User exist = userDao.selectUser(user.getUserId());
			if (exist != null)
				throw new Exception("이미 존재하는 아이디입니다.");

			userDao.insertUser(user);

			address.setUserNum(user.getUserNum());
			addressDao.insertAddress(address);

			if (user.getIsSeller() == true && sellerDetail != null) {
				sellerDetail.setUserNum(user.getUserNum());
				sellerDao.insertSellerDetail(sellerDetail);
			}
			sqlSession.commit(); // ✅ 전체 성공 시 커밋

		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public User login(String userId, String pwd) throws Exception {
		try (SqlSession sqlSession = MybatisSqlSessionFactory.getSqlSessionFactory().openSession()) {
			UserDAO userDao = new UserDAOImpl(sqlSession);

			User user = userDao.selectUser(userId);
			if (user == null || user.getIsDeleted() == true)
				throw new Exception("아이디 오류");
			if (!user.getPwd().equals(pwd))
				throw new Exception("비밀번호 오류");
			return user;
		}
	}

	@Override
	public boolean checkDoubleId(String id) throws Exception {
		try (SqlSession sqlSession = MybatisSqlSessionFactory.getSqlSessionFactory().openSession()) {
			UserDAO userDao = new UserDAOImpl(sqlSession);
			User user = userDao.selectUser(id);
			if (user == null)
				return false;
			return true;
		}
	}

	@Override
	public boolean doubleStoreNameCheck(String storeName) throws Exception {
		try (SqlSession sqlSession = MybatisSqlSessionFactory.getSqlSessionFactory().openSession()) {
			SellerDAO sellerDao = new SellerDAOImpl(sqlSession);
			return sellerDao.doubleStoreNameCheck(storeName);
		}
	}

	@Override
	public User deleteUser(String userId) throws Exception {
		try (SqlSession sqlSession = MybatisSqlSessionFactory.getSqlSessionFactory().openSession(false)) {
			UserDAO userDao = new UserDAOImpl(sqlSession);
			User user = userDao.selectUser(userId);
			userDao.deleteUser(userId);
			sqlSession.commit();
			return user;
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public void writeAdminQA(AdminQuestion adminQuestion) throws Exception {
		try (SqlSession sqlSession = MybatisSqlSessionFactory.getSqlSessionFactory().openSession(false)) {
			UserDAO userDao = new UserDAOImpl(sqlSession);
			userDao.insertAdminQA(adminQuestion);
			sqlSession.commit();

		}
	}

	@Override
	public AdminQuestionVO detailAdminQA(Long questionNum) throws Exception {
		try (SqlSession sqlSession = MybatisSqlSessionFactory.getSqlSessionFactory().openSession()) {
			UserDAO userDao = new UserDAOImpl(sqlSession);
			return userDao.detailAdminQA(questionNum);
		}
	}

	@Override
	public List<AdminQuestionVO> adminQAListByPage(PageInfo pageInfo) throws Exception {
		try (SqlSession sqlSession = MybatisSqlSessionFactory.getSqlSessionFactory().openSession()) {
			UserDAO userDao = new UserDAOImpl(sqlSession);
			Integer adminQACnt = userDao.selectAdminQACount();
			Integer allPage = (int) Math.ceil((double) adminQACnt / pageInfo.getPageSize());
			Integer startPage = (pageInfo.getCurPage() - 1) / 10 * 10 + 1;
			Integer endPage = startPage + 10 - 1;
			if (endPage > allPage) endPage = allPage;

			pageInfo.setAllPage(allPage);
			pageInfo.setStartPage(startPage);
			pageInfo.setEndPage(endPage);

			Map<String, Object> param = new HashMap<>();
			param.put("start", pageInfo.getOffset());
			param.put("pageSize", pageInfo.getPageSize());
			return userDao.selectAdminQAListByPage(param);
		}
	}

	@Override
	public List<AdminQuestion> recentAdminQA() throws Exception {
		try (SqlSession sqlSession = MybatisSqlSessionFactory.getSqlSessionFactory().openSession()) {
			UserDAO userDao = new UserDAOImpl(sqlSession);
			return userDao.selectRecentAdminQA();
		}
	}

	@Override
	public User findId(String email, String phone) throws Exception {
		try (SqlSession sqlSession = MybatisSqlSessionFactory.getSqlSessionFactory().openSession()) {
			UserDAO userDao = new UserDAOImpl(sqlSession);
			Map<String, Object> param = new HashMap<>();
			param.put("email", email);
			param.put("phone", phone);

			return userDao.findId(param);
		}

	}

	@Override
	public User findPwd(String email, String userId) throws Exception {
		try (SqlSession sqlSession = MybatisSqlSessionFactory.getSqlSessionFactory().openSession()) {
			UserDAO userDao = new UserDAOImpl(sqlSession);
			Map<String, Object> param = new HashMap<>();
			param.put("email", email);
			param.put("userId", userId);

			return userDao.findPwd(param);
		}
	}

	@Override
	public void updateResetPwdToken(Long userNum, String resetPwdToken) throws Exception {
		try (SqlSession sqlSession = MybatisSqlSessionFactory.getSqlSessionFactory().openSession(false)) {
			UserDAO userDao = new UserDAOImpl(sqlSession);
			Map<String, Object> param = new HashMap<>();
			param.put("userNum", userNum);
			param.put("resetPwdToken", resetPwdToken);

			userDao.resetPwdToken(param);
			sqlSession.commit();
		}
	}

	@Override
	public void resetPwd(String pwd, Long userNum) throws Exception {
		try (SqlSession sqlSession = MybatisSqlSessionFactory.getSqlSessionFactory().openSession(false)) {
			UserDAO userDao = new UserDAOImpl(sqlSession);
			Map<String, Object> param = new HashMap<>();
			param.put("pwd", pwd);
			param.put("userNum", userNum);

			userDao.resetPwd(param);
			sqlSession.commit();
		}
	}

	@Override
	public User findUserById(String userId) throws Exception {
		try (SqlSession sqlSession = MybatisSqlSessionFactory.getSqlSessionFactory().openSession()) {
			UserDAO userDao = new UserDAOImpl(sqlSession);
			return userDao.selectUserById(userId);
		}
	}

	@Override
	public Integer getAdminQACount() throws Exception {
		try (SqlSession sqlSession = MybatisSqlSessionFactory.getSqlSessionFactory().openSession()) {
			UserDAO userDao = new UserDAOImpl(sqlSession);
			return userDao.selectAdminQACount();
		}
	}

	@Override
	public User existingPwd(String resetPwdToken) throws Exception {
		try (SqlSession sqlSession = MybatisSqlSessionFactory.getSqlSessionFactory().openSession()) {
			UserDAO userDao = new UserDAOImpl(sqlSession);
			return userDao.existingPwd(resetPwdToken);

		}
	}

	@Override
	public boolean isSamePassword(Long userNum, String pwd) throws Exception {
		try (SqlSession sqlSession = MybatisSqlSessionFactory.getSqlSessionFactory().openSession()) {
			UserDAO userDao = new UserDAOImpl(sqlSession);
			String currentPwd = userDao.getPasswordByUserNum(userNum);
			return currentPwd.equals(pwd);
		}
	}

	// fcm
	@Override
	public void updateFcmTokenIfChanged(Long userNum, String newFcmToken) throws Exception {
		try (SqlSession sqlSession = MybatisSqlSessionFactory.getSqlSessionFactory().openSession(false)) {
			UserDAO userDao = new UserDAOImpl(sqlSession);
			String currentToken = userDao.selectFcmToken(userNum);

			if (currentToken == null || !currentToken.equals(newFcmToken)) {
				userDao.updateFcmToken(userNum, newFcmToken);
			}
			sqlSession.commit();
		}
	}

	@Override
	public void deleteAdminQA(Long questionNum) throws Exception {
		try (SqlSession sqlSession = MybatisSqlSessionFactory.getSqlSessionFactory().openSession(false)) {
			UserDAO userDao = new UserDAOImpl(sqlSession);
			userDao.deleteAdminQA(questionNum);
			sqlSession.commit();
		}
	}

	@Override
	public boolean updateSellerAccountInfo(Long userNum, String pwd, String phone, String email, String storeName,
			String postCode, String addr1, String addr2) throws Exception {
		try (SqlSession sqlSession = MybatisSqlSessionFactory.getSqlSessionFactory().openSession(false)) {
			UserDAO userDao = new UserDAOImpl(sqlSession);
			AddressDAO addressDao = new AddressDAOImpl(sqlSession);
			boolean res1 = userDao.updateSellerAccountInfo(userNum, pwd, phone, email, storeName, postCode);
			boolean res2 = addressDao.updateDefaultAddressSeller(userNum, addr1, addr2);
			sqlSession.commit();
			return res1 && res2;
		}
	}

	@Override
	public void deleteFcmToken(Long userNum) throws Exception {
		try (SqlSession sqlSession = MybatisSqlSessionFactory.getSqlSessionFactory().openSession(false)) {
			UserDAO userDao = new UserDAOImpl(sqlSession);
			userDao.deleteFcmToken(userNum);
			sqlSession.commit();
		}
	}
}
