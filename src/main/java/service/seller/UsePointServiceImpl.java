package service.seller;

import java.util.List;

import dao.seller.PointDAOImpl;
import dao.seller.UsePointDAO;
import dao.seller.UsePointDAOImpl;
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
		//1. 기존 포인트 업데이트
		PointService pointService = new PointServiceImpl();
		pointService.updatePoint(usePoint.getUsedPoint(), usePoint.getUserNum());
		//2. 최신 잔액 얻어오기
		Integer currPoint = pointService.getPoint(usePoint.getUserNum()).getPoint();
		usePoint.setCurrPoint(currPoint);
		usePointDAO.insertUsePointHistory(usePoint);
	}

}
