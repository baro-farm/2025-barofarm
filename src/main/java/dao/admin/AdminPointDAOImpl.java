package dao.admin;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import dto.seller.Point;
import util.MybatisSqlSessionFactory;

public class AdminPointDAOImpl implements AdminPointDAO {
	SqlSession sqlSession = MybatisSqlSessionFactory.getSqlSessionFactory().openSession();

	@Override
	public List<Point> totalSalesPointList() throws Exception {
		return sqlSession.selectList("mapper.point.totalSalesPointList");
	}

	
}
