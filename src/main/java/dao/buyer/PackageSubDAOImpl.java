package dao.buyer;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import util.MybatisSqlSessionFactory;
import vo.PackSubVO;

public class PackageSubDAOImpl implements PackageSubDAO{
	SqlSession sqlSession = MybatisSqlSessionFactory.getSqlSessionFactory().openSession();

	@Override
	public List<PackSubVO> selectUserPackSubList(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectList("mapper.packageProduct.selectPackSubList",param);
	}

	@Override
	public Integer selectUserPackSubCount(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("mapper.packageProduct.selectPackSubCount",param);
	}

}
