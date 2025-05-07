package dao.seller;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import dto.seller.Point;
import util.MybatisSqlSessionFactory;

public class PointDAOImpl implements PointDAO {
	private final SqlSession sqlSession;

	public PointDAOImpl(SqlSession sqlSession) {

		this.sqlSession = sqlSession;

	}

	@Override
	public Point getPoint(Long userNum) throws Exception {
		return sqlSession.selectOne("mapper.point.getPoint", userNum);
	}

	@Override
	public void updatePoint(Integer point, Long userNum) throws Exception {
		Map<String, Object> param = new HashMap<>();
		param.put("point", point);
		param.put("userNum", userNum);
		sqlSession.update("mapper.point.updatePoint", param);
	}

	@Override
	public void insertPoint(Point point) throws Exception {
		sqlSession.insert("mapper.point.insertPoint", point);
	}

}
