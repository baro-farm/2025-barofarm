package dao.buyer;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import dto.buyer.Address;
import dto.User;
import util.MybatisSqlSessionFactory;
import vo.UserVO;

public class UserDAOImpl implements UserDAO {
    private final SqlSession sqlSession;

    public UserDAOImpl(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }
    
	@Override
	public UserVO selectUser(String userId) throws Exception {
		return  sqlSession.selectOne("mapper.user.selectUserWithAddress",userId);
	}
	
	public Long selectUserNumById(String userId) throws Exception{
		return sqlSession.selectOne("mapper.user.selectUserNumById",userId);
	}

	@Override
	public void updateUser(UserVO user) throws Exception {
		Long userNum = selectUserNumById(user.getUserId());
		user.setUserNum(userNum);
		user.getAddresses().get(0).setUserNum(userNum);
		sqlSession.update("mapper.user.updateUser",user);
		sqlSession.update("mapper.user.updateAddress",user.getAddresses().get(0));
	}

	@Override
	public void insertAddress(Address address) throws Exception {
		sqlSession.insert("mapper.user.insertAddress",address);
		
	}

	@Override
	public List<Address> selectAddressList(String userId) throws Exception {
		Long userNum = selectUserNumById(userId);
		return sqlSession.selectList("mapper.user.selectAddressList",userNum);
	}

	@Override
	public Address selectAddress(Long addrNum) throws Exception {
		return sqlSession.selectOne("mapper.user.selectAddress",addrNum);
	}
	
	@Override
	public Address selectDefaultAddress(Long userNum) {
		return sqlSession.selectOne("mapper.user.selectDefaultAddress", userNum);
	}

	@Override
	public void updateAddress(Address address) throws Exception {
		sqlSession.update("mapper.user.updateAddressByAddrNum",address);
	}

	@Override
	public void deleteAddress(Long addrNum) throws Exception {
		sqlSession.delete("mapper.user.deleteAddress",addrNum);
	}

}
