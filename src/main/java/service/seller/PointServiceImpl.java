package service.seller;

import dao.seller.PointDAO;
import dao.seller.PointDAOImpl;
import dto.seller.Point;

public class PointServiceImpl implements PointService {
	private PointDAO pointDAO;
	public PointServiceImpl() {
		pointDAO = new PointDAOImpl();
	}
	@Override
	public Point getPoint(Long userNum) throws Exception {
		return pointDAO.getPoint(userNum);
	}
	@Override
	public void updatePoint(Integer point, Long userNum) throws Exception {
		pointDAO.updatePoint(point, userNum);
	}

}
