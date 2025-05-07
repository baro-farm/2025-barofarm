package dao.admin;

import java.util.List;
import java.util.Map;

import dto.User;

public interface UserDAO {
	List<User> selectUserList(Map<String, Object> param) throws Exception;
	int countUser(Map<String, Object> param) throws Exception;
}
