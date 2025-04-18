package dao.seller;

import dto.seller.SellerDetail;

public interface SellerDAO {
	void insertSellerDetail(SellerDetail seller) throws Exception;
	boolean doubleStoreNameCheck(String storeName) throws Exception;
}
