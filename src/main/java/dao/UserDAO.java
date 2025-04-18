package dao;

import java.util.List;

import dto.User;
import dto.admin.AdminQuestion;
import vo.AdminQuestionVO;

public interface UserDAO {
	void insertUser(User user) throws Exception;
	User selectUser(String userId) throws Exception;
	void deleteUser(String userId) throws Exception;
	void insertAdminQ(AdminQuestion adminQuestion) throws Exception;
	List<AdminQuestionVO> AdminQList() throws Exception;
	AdminQuestionVO detailAdminQA(Long questionNum) throws Exception;
}
