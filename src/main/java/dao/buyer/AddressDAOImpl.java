package dao.buyer;

import org.apache.ibatis.session.SqlSession;

import dto.buyer.Address;
import util.MybatisSqlSessionFactory;

public class AddressDAOImpl implements AddressDAO {
	SqlSession sqlSession = MybatisSqlSessionFactory.getSqlSessionFactory().openSession();

	@Override
	public void insertAddress(Address address) throws Exception {
		 sqlSession.insert("mapper.address.insertAddress", address);
		 sqlSession.commit();
	}

}
