package dao.buyer;

import java.util.List;

import dto.buyer.Address;
import dto.buyer.User;
import vo.UserVO;

public interface UserDAO {
	UserVO selectUser(String userId) throws Exception;
	void updateUser(UserVO user) throws Exception;
	void insertAddress(Address address,String userId) throws Exception;
	List<Address> selectAddressList(String userId) throws Exception;
	Address selectAddress(Long addrNum) throws Exception;
	void updateAddress(Address address) throws Exception;
	void deleteAddress(Long addrNum) throws Exception;
}
