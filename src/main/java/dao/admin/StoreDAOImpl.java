package dao.admin;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import dto.admin.Store;
import util.MybatisSqlSessionFactory;

public class StoreDAOImpl implements StoreDAO {
	SqlSession sqlSession = MybatisSqlSessionFactory.getSqlSessionFactory().openSession();
	
	@Override
	public List<Store> selectStoreList(Map<String, Object> param) throws Exception {
		return sqlSession.selectList("mapper.seller.selectStoreList", param);
	}

	@Override
	public int countStoreList(Map<String, Object> param) throws Exception {
		return sqlSession.selectOne("mapper.seller.countStoreList", param);
	}
}
