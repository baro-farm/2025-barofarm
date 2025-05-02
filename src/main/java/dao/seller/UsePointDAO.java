package dao.seller;

import java.util.List;

import dto.seller.UsePoint;
import util.SearchDtoSoy;

public interface UsePointDAO {
	void insertUsePointHistory(UsePoint usePoint) throws Exception;
	List<UsePoint> selectUsePointListBySearchDto(SearchDtoSoy dto) throws Exception;
	int countUsePointBySearchDto(SearchDtoSoy dto) throws Exception;
	void insertKockFarmAlarm(UsePoint usePoint) throws Exception;
}
