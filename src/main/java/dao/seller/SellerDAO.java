package dao.seller;

import vo.SellerVO;

public interface SellerDAO {
	void insertSeller(SellerVO seller) throws Exception;
	
	Long selectSellerNumByUserID(String userId) throws Exception;
}
