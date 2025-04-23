package service;

import java.util.List;
import java.util.Map;

import dto.User;
import dto.admin.AdminQuestion;
import dto.buyer.Address;
import dto.seller.SellerDetail;
import util.PageInfo;
import vo.AdminQuestionVO;

public interface UserService {
	void join(User user,Address address, SellerDetail sellerDetail) throws Exception;
	User login(String userId, String pwd) throws Exception;
	boolean checkDoubleId(String userId) throws Exception;
	boolean doubleStoreNameCheck(String storeName) throws Exception;
	
	User deleteUser(String userId) throws Exception;
	
	void writeAdminQA(AdminQuestion adminQuestion) throws Exception;
	AdminQuestionVO detailAdminQA(Long qNum) throws Exception;
	List<AdminQuestionVO> adminQAListByPage(PageInfo pageInfo) throws Exception;
	List<AdminQuestion> recentAdminQA() throws Exception;
	
	String findId(String email, String phone) throws Exception; 
	

}
