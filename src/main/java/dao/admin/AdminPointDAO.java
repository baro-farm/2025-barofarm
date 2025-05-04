package dao.admin;

import java.util.List;
import java.util.Map;

import dto.seller.Point;
import vo.PointVO;

public interface AdminPointDAO {
	List<PointVO> totalSalesPointList(Map<String, Object> param) throws Exception;
	Integer countTotalSalesPoint() throws Exception;
	List<PointVO> getMonthlyPoint() throws Exception;
	PointVO currentMonthPoint() throws Exception;
}
