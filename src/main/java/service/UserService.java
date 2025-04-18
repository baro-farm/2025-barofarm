package service;

import java.util.List;

import dto.User;
import dto.admin.AdminQuestion;
import dto.buyer.Address;
import dto.seller.SellerDetail;
import vo.AdminQuestionVO;

public interface UserService {
	void join(User user,Address address, SellerDetail sellerDetail) throws Exception;
	User login(String userId, String pwd) throws Exception;
	boolean checkDoubleId(String userId) throws Exception;
	User deleteUser(String userId) throws Exception;
	void insertAdminQ(AdminQuestion adminQuestion) throws Exception;
	List<AdminQuestionVO> adminQList() throws Exception;
	AdminQuestionVO detailAdminQA(Long qNum) throws Exception;
	List<AdminQuestion> selectAdminQ() throws Exception;
	boolean doubleStoreNameCheck(String storeName) throws Exception;

}
