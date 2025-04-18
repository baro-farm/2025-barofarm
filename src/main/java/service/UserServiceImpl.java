package service;

import java.util.List;

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
	public boolean checkDoubleId(String id) throws Exception {
		User user = userDao.selectUser(id);
		if(user==null) return false;
		return true;
	}

	@Override
	public User login(String userId, String pwd) throws Exception {
		User user = userDao.selectUser(userId);
		if(user==null || user.getIsDeleted() == true) throw new Exception("아이디 오류");
		if(!user.getPwd().equals(pwd)) throw new Exception("비밀번호 오류");
		return user;
	}

	@Override
	public User deleteUser(String userId) throws Exception {
		User user = userDao.selectUser(userId);
		userDao.deleteUser(userId);
		return user;
	}
	
	@Override
	public void insertAdminQ(AdminQuestion adminQuestion) throws Exception {
		userDao.insertAdminQ(adminQuestion);
	}

	@Override
	public List<AdminQuestionVO> adminQList() throws Exception {
		return userDao.AdminQList();
	}

	@Override
	public AdminQuestionVO detailAdminQA(Long questionNum) throws Exception {
		return userDao.detailAdminQA(questionNum);
	}

	@Override
	public List<AdminQuestion> selectAdminQ() throws Exception {
		return userDao.selectRecentAdminQ();
	}

	@Override
	public boolean doubleStoreNameCheck(String storeName) throws Exception {
		 return sellerDao.doubleStoreNameCheck(storeName);
	}

	

	

}
