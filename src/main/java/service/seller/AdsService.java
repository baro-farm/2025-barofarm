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
	
	//관리자
	List<Advertisement> selectAdsBySearchDto(SearchDtoSoy dto) throws Exception;
	int countAdsBySearchDtoSoy(SearchDtoSoy dto) throws Exception;
}
