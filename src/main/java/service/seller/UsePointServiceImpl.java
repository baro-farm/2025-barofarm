package service.seller;

import dao.seller.PointDAOImpl;
import dao.seller.UsePointDAO;
import dao.seller.UsePointDAOImpl;
import dto.seller.UsePoint;

public class UsePointServiceImpl implements UsePointService {
	private UsePointDAO usePointDAO;
	
	public UsePointServiceImpl() {
		usePointDAO = new UsePointDAOImpl();
	}
	@Override
	public void insertUsePoint(UsePoint usePoint) throws Exception {
		usePointDAO.insertUsePoint(usePoint);
	}

}
