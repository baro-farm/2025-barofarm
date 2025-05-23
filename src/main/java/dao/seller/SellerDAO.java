package dao.seller;

import dto.seller.SellerDetail;
import vo.SellerVO;

public interface SellerDAO {
	
	Long selectSellerNumByUserID(String userId) throws Exception;
	Long selectSellerNum(Long userNum) throws Exception;
	void insertSellerDetail(SellerDetail seller) throws Exception;
	boolean doubleStoreNameCheck(String storeName) throws Exception;
	SellerVO selectSerllerDetail(Long userNum) throws Exception;
	void updateIsAlarm(Long userNum) throws Exception;
	SellerDetail selectSellerDetail(Long sellerNum);
}
