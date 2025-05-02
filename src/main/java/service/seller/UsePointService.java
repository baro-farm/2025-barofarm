package service.seller;

import java.util.List;

import dto.seller.UsePoint;
import util.SearchDtoSoy;

public interface UsePointService {
	void handlePointByAds(UsePoint usePoint) throws Exception;
	List<UsePoint> selectUsePointListBySearchDto(SearchDtoSoy dto) throws Exception;
	int countUsePointBySearchDto(SearchDtoSoy dto) throws Exception;
	void chargePointByPayment(UsePoint usePoint) throws Exception;
	void useByKockFarmAlarm (UsePoint usePoint) throws Exception;
}
