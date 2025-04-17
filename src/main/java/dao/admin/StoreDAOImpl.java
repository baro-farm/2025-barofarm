package dao.admin;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import dto.admin.Store;
import util.MybatisSqlSessionFactory;

public class StoreDAOImpl implements StoreDAO {
	SqlSession sqlsession = MybatisSqlSessionFactory.getSqlSessionFactory().openSession();
	
	@Override
	public List<Store> selectStoreList() throws Exception {
		return sqlsession.selectList("mapper.seller.selectStoreList");
	}

}
