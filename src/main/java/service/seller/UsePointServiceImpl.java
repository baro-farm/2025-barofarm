package service.seller;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import dao.seller.UsePointDAO;
import dao.seller.UsePointDAOImpl;
import dto.seller.Point;
import dto.seller.UsePoint;
import util.MybatisSqlSessionFactory;
import util.SearchDtoSoy;

public class UsePointServiceImpl implements UsePointService {

	private UsePointDAO usePointDAO(SqlSession sqlSession) {
		return new UsePointDAOImpl(sqlSession);
	}

	@Override
	public void handlePointByAds(UsePoint usePoint) throws Exception {
		try (SqlSession sqlSession = MybatisSqlSessionFactory.getSqlSessionFactory().openSession(false)) {

			usePointDAO(sqlSession).insertUsePointHistory(usePoint);
			sqlSession.commit();
		}
	}

	@Override
	public List<UsePoint> selectUsePointListBySearchDto(SearchDtoSoy dto) throws Exception {
		try (SqlSession sqlSession = MybatisSqlSessionFactory.getSqlSessionFactory().openSession()) {

			return usePointDAO(sqlSession).selectUsePointListBySearchDto(dto);
		}
	}

	@Override
	public int countUsePointBySearchDto(SearchDtoSoy dto) throws Exception {
		try (SqlSession sqlSession = MybatisSqlSessionFactory.getSqlSessionFactory().openSession()) {

			return usePointDAO(sqlSession).countUsePointBySearchDto(dto);
		}
	}

	@Override
	public void chargePointByPayment(UsePoint usePoint) throws Exception {
		try (SqlSession sqlSession = MybatisSqlSessionFactory.getSqlSessionFactory().openSession(false)) {

			// 1. 최신 잔액 얻어오기
			PointService pointService = new PointServiceImpl();
			// 2. 기존 포인트 업데이트
			Point point = pointService.getPoint(usePoint.getUserNum());
			Integer currPoint;
			if (point == null) {
				Point newPoint = new Point(null, usePoint.getUserNum(), usePoint.getUsedPoint(), null, null);
				currPoint = usePoint.getUsedPoint();
				pointService.addPoint(newPoint);
			} else {
				currPoint = point.getPoint() + usePoint.getUsedPoint();
				pointService.updatePoint(usePoint.getUsedPoint(), usePoint.getUserNum());
			}
			usePoint.setCurrPoint(currPoint);
			usePointDAO(sqlSession).insertUsePointHistory(usePoint);
			sqlSession.commit();
		}
	}

	@Override
	public void useByKockFarmAlarm(UsePoint usePoint) throws Exception {
		try (SqlSession sqlSession = MybatisSqlSessionFactory.getSqlSessionFactory().openSession(false)) {

			usePointDAO(sqlSession).insertKockFarmAlarm(usePoint);
			sqlSession.commit();
		}
	}

}
