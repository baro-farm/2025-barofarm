package service.admin;

import java.util.List;

import dto.seller.Point;
import util.PageInfo;
import vo.PointVO;

public interface AdminPointService {
	List<PointVO> totalSalesPointList(PageInfo pageInfo,String sort) throws Exception;
	Integer countTotalSalesPoint() throws Exception;
	List<PointVO> getMonthlyPoint() throws Exception;
	PointVO currentMonthPoint() throws Exception;
}
