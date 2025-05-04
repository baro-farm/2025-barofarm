package dao.admin;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import dto.seller.Point;
import util.MybatisSqlSessionFactory;
import vo.PointVO;

public class AdminPointDAOImpl implements AdminPointDAO {
	SqlSession sqlSession = MybatisSqlSessionFactory.getSqlSessionFactory().openSession();

	@Override
	public List<PointVO> totalSalesPointList(Map<String, Object> param) throws Exception {
		return sqlSession.selectList("mapper.point.totalSalesPointList", param);
	}

	@Override
	public Integer countTotalSalesPoint() throws Exception {
		return sqlSession.selectOne("mapper.point.countTotalSalesPoint");
		
	}

	@Override
	public List<PointVO> getMonthlyPoint() throws Exception {
		return sqlSession.selectList("mapper.point.MonthlyPoint");
	}

	@Override
	public PointVO currentMonthPoint() throws Exception {
		return sqlSession.selectOne("mapper.point.currentMonthPoint");
	}

	
}
