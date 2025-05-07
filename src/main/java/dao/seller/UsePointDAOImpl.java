package dao.seller;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import dto.seller.UsePoint;
import util.MybatisSqlSessionFactory;
import util.SearchDtoSoy;

public class UsePointDAOImpl implements UsePointDAO {

	private final SqlSession sqlSession;

	public UsePointDAOImpl(SqlSession sqlSession) {

		this.sqlSession = sqlSession;

	}

	@Override
	public void insertUsePointHistory(UsePoint usePoint) throws Exception {
		int result = sqlSession.insert("mapper.usePoint.insertUsePointHistory", usePoint);

	}

	@Override
	public List<UsePoint> selectUsePointListBySearchDto(SearchDtoSoy dto) throws Exception {
		return sqlSession.selectList("mapper.usePoint.selectUsePointListBySearchDto", dto);
	}

	@Override
	public int countUsePointBySearchDto(SearchDtoSoy dto) throws Exception {
		return sqlSession.selectOne("mapper.usePoint.countUsePointBySearchDto", dto);
	}

	@Override
	public void insertKockFarmAlarm(UsePoint usePoint) throws Exception {
		sqlSession.insert("mapper.usePoint.insertKockFarmAlarm", usePoint);
	}

}
