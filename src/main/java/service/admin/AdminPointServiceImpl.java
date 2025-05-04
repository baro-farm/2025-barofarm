package service.admin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dao.admin.AdminPointDAO;
import dao.admin.AdminPointDAOImpl;
import dto.seller.Point;
import util.PageInfo;
import vo.PointVO;

public class AdminPointServiceImpl implements AdminPointService {
	public AdminPointDAO adminPointDao;

	public AdminPointServiceImpl() {
		adminPointDao = new AdminPointDAOImpl(); // ← 반드시 생성 필요!
    }
	
	@Override
	public List<PointVO> totalSalesPointList(PageInfo pageInfo, String sort) throws Exception {
		Map<String, Object> param = new HashMap<>();
		param.put("start", pageInfo.getOffset());
        param.put("pageSize", pageInfo.getPageSize());
        param.put("sort", sort);
		return adminPointDao.totalSalesPointList(param);
	}

	@Override
	public Integer countTotalSalesPoint() throws Exception {
		return adminPointDao.countTotalSalesPoint();
	}

	@Override
	public List<PointVO> getMonthlyPoint() throws Exception {
		return adminPointDao.getMonthlyPoint();
	}

	@Override
	public PointVO currentMonthPoint() throws Exception {
		return adminPointDao.currentMonthPoint();
	}

}
