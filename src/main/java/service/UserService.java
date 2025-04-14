package service;

import dto.User;

public interface UserService {
	void join(User user) throws Exception;
	void login(String id, String password) throws Exception;
	/* boolean checkDoubleId(String id) throws Exception; */
}
