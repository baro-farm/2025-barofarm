package service;

import dto.User;
import dto.buyer.Address;

public interface UserService {
	void join(User user,Address address) throws Exception;
	User login(String userId, String pwd) throws Exception;
	boolean checkDoubleId(String userId) throws Exception;
	User deleteUser(String userId) throws Exception;
}
