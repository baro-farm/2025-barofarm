package dao.buyer;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import util.MybatisSqlSessionFactory;
import vo.PackOrderVO;

public class PackageOrderDAOImpl implements PackageOrderDAO{
	SqlSession sqlSession = MybatisSqlSessionFactory.getSqlSessionFactory().openSession();

	@Override
	public List<PackOrderVO> selectPackOrderList(String userId) {
		Long userNum = sqlSession.selectOne("mapper.user.selectUserNumById",userId);
		return sqlSession.selectList("mapper.packorder.packOrderList",userNum);
	}

}
