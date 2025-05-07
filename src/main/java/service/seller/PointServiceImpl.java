package service.seller;

import org.apache.ibatis.session.SqlSession;

import dao.seller.PointDAO;
import dao.seller.PointDAOImpl;
import dto.seller.Point;
import util.MybatisSqlSessionFactory;

public class PointServiceImpl implements PointService {

	private PointDAO pointDao(SqlSession sqlSession) {
		return new PointDAOImpl(sqlSession);
	}

	@Override
	public Point getPoint(Long userNum) throws Exception {
		try (SqlSession sqlSession = MybatisSqlSessionFactory.getSqlSessionFactory().openSession()) {

			return pointDao(sqlSession).getPoint(userNum);
		}
	}

	@Override
	public void updatePoint(Integer point, Long userNum) throws Exception {
		try (SqlSession sqlSession = MybatisSqlSessionFactory.getSqlSessionFactory().openSession(false)) {

			pointDao(sqlSession).updatePoint(point, userNum);
			sqlSession.commit();
		}
	}

	@Override
	public void addPoint(Point point) throws Exception {
		try (SqlSession sqlSession = MybatisSqlSessionFactory.getSqlSessionFactory().openSession(false)) {

			pointDao(sqlSession).insertPoint(point);
			sqlSession.commit();
		}
	}

}
