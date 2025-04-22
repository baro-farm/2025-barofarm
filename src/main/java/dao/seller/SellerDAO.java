package dao.seller;

import dto.seller.SellerDetail;

public interface SellerDAO {
	void insertSeller(SellerVO seller) throws Exception;
	Long selectSellerNum(Long userNum) throws Exception;
	void insertSellerDetail(SellerDetail seller) throws Exception;
	boolean doubleStoreNameCheck(String storeName) throws Exception;
}
