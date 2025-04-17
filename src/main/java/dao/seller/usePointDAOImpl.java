package dao.seller;

import org.apache.ibatis.session.SqlSession;

import dto.seller.UsePoint;
import util.MybatisSqlSessionFactory;

public class usePointDAOImpl implements usePointDAO {
	SqlSession sqlSession = MybatisSqlSessionFactory.getSqlSessionFactory().openSession();
	@Override
	public boolean insertUsePoint(UsePoint usePoint) throws Exception {
		return false;
	}

}
