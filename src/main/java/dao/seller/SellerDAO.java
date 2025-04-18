package dao.seller;

import vo.SellerVO;

public interface SellerDAO {
	void insertSeller(SellerVO seller) throws Exception;
	Long selectSellerNum(Long userNum) throws Exception;
}
