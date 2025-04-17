package dao.admin;

import java.util.List;

import dto.User;

public interface UserDAO {
	List<User> selectUserList() throws Exception;
}
