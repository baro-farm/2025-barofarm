package service.seller;

import java.util.List;

import dto.seller.Advertisement;

public interface AdsService {
	void insertAds(Advertisement ads) throws Exception;
	List<Advertisement> selectAdsByUserNum(Long userNum) throws Exception;
	boolean cancelAdsAndRefund(Long adsNum, String status) throws Exception; 
}
