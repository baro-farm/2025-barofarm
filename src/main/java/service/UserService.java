package service;

import dto.User;
import dto.buyer.Address;

public interface UserService {
	void join(User user,Address address) throws Exception;
	void login(String id, String password) throws Exception;
	boolean checkDoubleId(String id) throws Exception;
}
