package dao.buyer;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import dto.buyer.Address;
import util.MybatisSqlSessionFactory;

public class AddressDAOImpl implements AddressDAO {
    private final SqlSession sqlSession;

    public AddressDAOImpl(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }
	@Override
	public void insertAddress(Address address) throws Exception {
		 sqlSession.insert("mapper.address.insertAddress", address);
	}

	@Override
	public boolean updateDefaultAddressSeller(Long userNum, String addr1, String addr2) throws Exception {
	    Map<String, Object> param = new HashMap<>();
	    param.put("addr1", addr1);
	    param.put("addr2", addr2);
        int res = sqlSession.update("mapper.address.updateDefaultAddressSeller", param);
        return res ==1;
	}

}
