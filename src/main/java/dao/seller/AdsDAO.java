package dao.seller;

import java.util.List;

import dto.seller.Advertisement;

public interface AdsDAO {
	boolean insertAds(Advertisement ads) throws Exception;
	List<Advertisement> selectAdsByUserNum(Long userNum) throws Exception;
	boolean updateAdsStatus(Long adsNum, String status) throws Exception;
}
