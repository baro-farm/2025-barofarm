package service.admin;

import java.util.List;

import dao.admin.AdminPointDAO;
import dto.seller.Point;

public class AdminPointServiceImpl implements AdminPointService {
	public AdminPointDAO adminPointDao;

	@Override
	public List<Point> totalSalesPointList() throws Exception {
		return adminPointDao.totalSalesPointList();
	}

}
