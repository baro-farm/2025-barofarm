package service.buyer;

import dto.buyer.User;

public interface UserService {
	User selectUserMyInfo(String userId) throws Exception;
}
