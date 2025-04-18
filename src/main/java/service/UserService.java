package service;

import java.util.List;

import dto.User;
import dto.admin.AdminQuestion;
import dto.buyer.Address;
import vo.AdminQuestionVO;

public interface UserService {
	void join(User user,Address address) throws Exception;
	User login(String userId, String pwd) throws Exception;
	boolean checkDoubleId(String userId) throws Exception;
	User deleteUser(String userId) throws Exception;
	void insertAdminQ(AdminQuestion adminQuestion) throws Exception;
	List<AdminQuestionVO> adminQList() throws Exception;
	AdminQuestionVO detailAdminQA(Long qNum) throws Exception;
}
