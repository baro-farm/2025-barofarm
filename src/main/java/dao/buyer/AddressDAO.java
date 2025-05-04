package dao.buyer;

import dto.buyer.Address;

public interface AddressDAO {
	 void insertAddress(Address address) throws Exception;
	 boolean updateDefaultAddressSeller(Long userNum,String addr1,String addr2) throws Exception;
}
