package dao.seller;

import org.apache.ibatis.session.SqlSession;

import dto.seller.UsePoint;
import util.MybatisSqlSessionFactory;

public class UsePointDAOImpl implements UsePointDAO {
	SqlSession sqlSession = MybatisSqlSessionFactory.getSqlSessionFactory().openSession();
	@Override
	public void insertUsePoint(UsePoint usePoint) throws Exception {
		int result = sqlSession.insert("mapper.usePoint.insertUsePoint",usePoint);
		sqlSession.commit();
		
	}

}
