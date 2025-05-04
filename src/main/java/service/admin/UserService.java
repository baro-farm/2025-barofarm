package service.admin;

import java.util.List;
import java.util.Map;

import dto.User;

public interface UserService {
	List<User> allUser(Map<String, Object> param) throws Exception;
	int countUser(Map<String, Object> param) throws Exception;
}
