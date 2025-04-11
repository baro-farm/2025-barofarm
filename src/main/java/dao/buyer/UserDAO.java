package dao.buyer;

import dto.buyer.User;

public interface UserDAO {
	User selectUserInfo(String userId) throws Exception;

}
