package service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import util.PageInfo;
import vo.AdminQuestionVO;
import vo.SellerVO;

public class UserServiceImpl implements UserService{
	private UserDAO userDao;
	private AddressDAO addressDao;
	private SellerDAO sellerDao;

	public UserServiceImpl() {
		 userDao = new UserDAOImpl();
		 addressDao = new AddressDAOImpl();
		 sellerDao = new SellerDAOImpl();
	}
	
	@Override
	public void join(User user, Address address, SellerDetail sellerDetail) throws Exception {
		 User exist = userDao.selectUser(user.getUserId());
		 if (exist != null) throw new Exception("이미 존재하는 아이디입니다.");
		    
		 userDao.insertUser(user);
		 
		 address.setUserNum(user.getUserNum());
		 addressDao.insertAddress(address);
		 
 
		 if (user.getIsSeller() == true && sellerDetail != null) {
		        sellerDetail.setUserNum(user.getUserNum());
		        sellerDao.insertSellerDetail(sellerDetail);
		 }
	}

	@Override
	public User login(String userId, String pwd) throws Exception {
		User user = userDao.selectUser(userId);
		if(user==null || user.getIsDeleted() == true) throw new Exception("아이디 오류");
		if(!user.getPwd().equals(pwd)) throw new Exception("비밀번호 오류");
		return user;
	}
	
	@Override
	public boolean checkDoubleId(String id) throws Exception {
		User user = userDao.selectUser(id);
		if(user==null) return false;
		return true;
	}

	@Override
	public boolean doubleStoreNameCheck(String storeName) throws Exception {
		 return sellerDao.doubleStoreNameCheck(storeName);
	}

	@Override
	public User deleteUser(String userId) throws Exception {
		User user = userDao.selectUser(userId);
		userDao.deleteUser(userId);
		return user;
	}
	
	@Override
	public void writeAdminQA(AdminQuestion adminQuestion) throws Exception {
		userDao.insertAdminQA(adminQuestion);
	}
	
	@Override
	public AdminQuestionVO detailAdminQA(Long questionNum) throws Exception {
		return userDao.detailAdminQA(questionNum);
	}
	
	@Override
	public List<AdminQuestionVO> adminQAListByPage(PageInfo pageInfo) throws Exception {
		Integer adminQACnt = userDao.selectAdminQACount();
		Integer allPage = (int)Math.ceil((double)adminQACnt/pageInfo.getPageSize());
		Integer startPage = (pageInfo.getCurPage()-1)/10*10+1;
		Integer endPage = startPage+10-1;
		if(endPage>allPage) endPage=allPage;
		
		pageInfo.setAllPage(allPage);
		pageInfo.setStartPage(startPage);
		pageInfo.setEndPage(endPage);
		
		Map<String, Object> param = new HashMap<>();
		param.put("start", pageInfo.getOffset());
        param.put("pageSize", pageInfo.getPageSize());		
		return userDao.selectAdminQAListByPage(param);
	}

	@Override
	public List<AdminQuestion> recentAdminQA() throws Exception {
		return userDao.selectRecentAdminQA();
	}

	@Override
	public User findId(String email, String phone) throws Exception {
		Map<String, Object> param = new HashMap<>();
		param.put("email", email);
		param.put("phone", phone);
		
		return userDao.findId(param);

	}

	@Override
	public User findPwd(String email, String userId) throws Exception {
		Map<String, Object> param = new HashMap<>();
		param.put("email", email);
		param.put("userId", userId);
		
		return userDao.findPwd(param);
	}

	@Override
	public void updateResetPwdToken(Long userNum, String resetPwdToken) throws Exception {
		 Map<String, Object> param = new HashMap<>();
		 param.put("userNum", userNum);
		 param.put("resetPwdToken", resetPwdToken);
		 
		 userDao.resetPwdToken(param);
	}
	
	@Override
	public void resetPwd(String pwd, Long userNum) throws Exception {
		 Map<String, Object> param = new HashMap<>();
		 param.put("pwd", pwd);
		 param.put("userNum", userNum);
		 
		 userDao.resetPwd(param);
		
	}

	@Override
	public User findUserById(String userId) throws Exception {
		 return userDao.selectUserById(userId);
	}
	
	

	@Override
	public Integer getAdminQACount() throws Exception {
		return userDao.selectAdminQACount();
	}

	@Override
	public User existingPwd(String resetPwdToken) throws Exception {
		return userDao.existingPwd(resetPwdToken);
		
	}

	@Override
	public boolean isSamePassword(Long userNum, String pwd) throws Exception {
		String currentPwd = userDao.getPasswordByUserNum(userNum);
        return currentPwd.equals(pwd);
	}

	//fcm
	@Override
	public void updateFcmTokenIfChanged(Long userNum, String newFcmToken) throws Exception {
		String currentToken = userDao.selectFcmToken(userNum);
		
		if (currentToken == null || !currentToken.equals(newFcmToken)) {
			userDao.updateFcmToken(userNum, newFcmToken);
		}
	}
}
