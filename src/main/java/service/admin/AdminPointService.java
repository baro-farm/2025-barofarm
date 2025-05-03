package service.admin;

import java.util.List;

import dto.seller.Point;
import util.PageInfo;
import vo.PointVO;

public interface AdminPointService {
	List<PointVO> totalSalesPointList(PageInfo pageInfo) throws Exception;
	Integer countTotalSalesPoint() throws Exception;
}
