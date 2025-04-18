package dao.seller;

import java.util.List;

import dto.seller.Advertisement;

public interface AdsDAO {
	boolean insertAds(Advertisement ads) throws Exception;
	List<Advertisement> selectAdsByUserNum(Long userNum) throws Exception;
	void updateAdsStatus(Long adsNum, String status) throws Exception;
	Advertisement selectAdsByAdsNum(Long adsNum) throws Exception;
	void updateAds(Advertisement ads) throws Exception;
}
