package service.seller;

import java.util.List;

import dto.seller.Advertisement;
import util.SearchDtoSoy;

public interface AdsService {
	void insertAds(Advertisement ads) throws Exception;
	List<Advertisement> selectAdsByUserNum(Long userNum) throws Exception;
	void cancelAdsAndRefund(Long adsNum, String status) throws Exception;
	Advertisement selectAdsByAdsNum(Long adsNum) throws Exception;
	void updateAds(Advertisement ads) throws Exception;
	void updateExpiredAdsStatus() throws Exception;
	
	//관리자
	List<Advertisement> selectAdsWithPosting() throws Exception;
	int countAdsWithPosting() throws Exception;
	List<Advertisement> selectAdsBySearchDto(SearchDtoSoy dto) throws Exception;
	int countAdsBySearchDtoSoy(SearchDtoSoy dto) throws Exception;
	boolean updateAdsStatusByAdmin(Long adsNum, String status) throws Exception;
}
