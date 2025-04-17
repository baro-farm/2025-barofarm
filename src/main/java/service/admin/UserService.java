package service.admin;

import java.util.List;

import dto.User;

public interface UserService {
	public List<User> allUser() throws Exception;
}
