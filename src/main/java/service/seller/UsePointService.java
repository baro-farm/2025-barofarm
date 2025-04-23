package service.seller;

import java.util.List;

import dto.seller.UsePoint;
import util.SearchDtoSoy;

public interface UsePointService {
	void insertUsePoint(UsePoint usePoint) throws Exception;
	List<UsePoint> selectUsePointListBySearchDto(SearchDtoSoy dto) throws Exception;
	int countUsePointBySearchDto(SearchDtoSoy dto) throws Exception;
}
