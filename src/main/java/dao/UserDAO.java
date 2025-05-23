package dao;

import java.util.List;
import java.util.Map;

import dto.User;
import dto.admin.AdminQuestion;
import vo.AdminQuestionVO;

public interface UserDAO {
	void insertUser(User user) throws Exception;
	User selectUser(String userId) throws Exception;
	void deleteUser(String userId) throws Exception;
	void insertAdminQA(AdminQuestion adminQuestion) throws Exception;
	AdminQuestionVO detailAdminQA(Long questionNum) throws Exception;
	List<AdminQuestion> selectRecentAdminQA() throws Exception;
	List<AdminQuestionVO> selectAdminQAListByPage(Map<String, Object> param) throws Exception;
	Integer selectAdminQACount() throws Exception;
	User findId(Map<String, Object> param) throws Exception;
	User findPwd(Map<String, Object> param) throws Exception;
	void resetPwdToken(Map<String, Object> param) throws Exception;
	void resetPwd(Map<String, Object> param) throws Exception;
	User existingPwd(String resetPwdToken) throws Exception;
	String getPasswordByUserNum(Long userNum);
	
	public User selectUserById(String userId) throws Exception;

	//fcm
	void updateFcmToken(Long userNum, String newFcmToken) throws Exception;
	String selectFcmToken(Long userNum) throws Exception;
	void deleteFcmToken(Long userNum) throws Exception;

	void deleteAdminQA(Long questionNum) throws Exception;

	//스토어 정보 수정
	 boolean updateSellerAccountInfo(Long userNum, String pwd, String phone, String email,String storeName, String postCode) throws Exception;

}
