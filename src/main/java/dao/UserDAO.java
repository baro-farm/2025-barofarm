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
	List<AdminQuestionVO> selectAdminQAListByPage(Integer row) throws Exception;
	Integer selectAdminQACount() throws Exception;
	User findId(Map<String, Object> param) throws Exception;
	User findPwd(Map<String, Object> param) throws Exception;
	void resetPwdToken(Map<String, Object> param) throws Exception;
}
