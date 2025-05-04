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
	Integer getAdminQACount() throws Exception;
	List<AdminQuestion> recentAdminQA() throws Exception;
	
	User findId(String email, String phone) throws Exception;
	User findPwd(String email, String userId) throws Exception;
	void updateResetPwdToken(Long userNum, String resetPwdToken) throws Exception;
	void resetPwd(String pwd, Long userNum) throws Exception;
	User existingPwd(String resetPwdToken) throws Exception;
	boolean isSamePassword(Long userNum, String pwd) throws Exception;
	
	public User findUserById(String userId) throws Exception;

	//fcm
	void updateFcmTokenIfChanged(Long userNum, String newFcmToken) throws Exception;
	void deleteFcmToken(Long userNum) throws Exception;
	
	void deleteAdminQA(Long questionNum) throws Exception;

	//스토어 정보 수정
	 boolean updateSellerAccountInfo(Long userNum, String pwd, String phone, String email,String storeName, String postCode, String addr1, String addr2) throws Exception;

}
