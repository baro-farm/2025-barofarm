package service.seller;

import java.util.List;

import dao.seller.UsePointDAO;
import dao.seller.UsePointDAOImpl;
import dto.seller.Point;
import dto.seller.UsePoint;
import util.SearchDtoSoy;

public class UsePointServiceImpl implements UsePointService {
	private UsePointDAO usePointDAO;
	
	public UsePointServiceImpl() {
		usePointDAO = new UsePointDAOImpl();
	}
	@Override
	public void handlePointByAds(UsePoint usePoint) throws Exception {
		usePointDAO.insertUsePointHistory(usePoint);
	}
	@Override
	public List<UsePoint> selectUsePointListBySearchDto(SearchDtoSoy dto) throws Exception {
		return usePointDAO.selectUsePointListBySearchDto(dto);
	}
	@Override
	public int countUsePointBySearchDto(SearchDtoSoy dto) throws Exception {
		return usePointDAO.countUsePointBySearchDto(dto);
	}
	@Override
	public void chargePointByPayment(UsePoint usePoint) throws Exception {
		//1. 최신 잔액 얻어오기
		PointService pointService = new PointServiceImpl();
		//2. 기존 포인트 업데이트
		Point point  = pointService.getPoint(usePoint.getUserNum());
		Integer currPoint;
		if (point==null) {
			Point newPoint = new Point(null, usePoint.getUserNum(), usePoint.getUsedPoint(), null, null);
			currPoint = usePoint.getUsedPoint();
			pointService.addPoint(newPoint);
		} else {
			currPoint = point.getPoint()+usePoint.getUsedPoint();
			pointService.updatePoint( usePoint.getUsedPoint(), usePoint.getUserNum());
		}
		usePoint.setCurrPoint(currPoint);
		usePointDAO.insertUsePointHistory(usePoint);
	}

}
