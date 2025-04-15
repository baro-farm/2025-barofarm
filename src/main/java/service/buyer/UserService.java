package service.buyer;

import java.util.List;

import dto.buyer.Address;
import dto.User;
import vo.UserVO;

public interface UserService {
	UserVO selectUserInfo(String userId) throws Exception;
	void updateUserInfo(UserVO user) throws Exception;
	void insertUserAddress(Address address, String userId) throws Exception;
	List<Address> selectUserAddressList(String userId) throws Exception;
	Address selectUserAddress(Long addrNum) throws Exception;
	void updateUserAddress(Address address) throws Exception;
	void deleteUserAddress(Long addrNum) throws Exception;
	
}
