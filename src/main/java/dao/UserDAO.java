package dao;

import dto.User;

public interface UserDAO {
	void insertUser(User user) throws Exception;
	User selectUser(String id) throws Exception;
}
