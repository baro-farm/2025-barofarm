package dao.buyer;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import util.MybatisSqlSessionFactory;
import vo.PackSubVO;

public class PackageSubDAOImpl implements PackageSubDAO {
	private final SqlSession sqlSession;

	public PackageSubDAOImpl(SqlSession sqlSession) {

		this.sqlSession = sqlSession;

	}

	@Override
	public List<PackSubVO> selectUserPackSubList(Map<String, Object> param) throws Exception {
		return sqlSession.selectList("mapper.packageProduct.selectPackSubList", param);
	}

	@Override
	public Integer selectUserPackSubCount(Map<String, Object> param) throws Exception {
		return sqlSession.selectOne("mapper.packageProduct.selectPackSubCount", param);
	}

}
