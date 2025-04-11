package dao.buyer;

import org.apache.ibatis.session.SqlSession;

import dto.buyer.User;
import util.MybatisSqlSessionFactory;

public class UserDAOImpl implements UserDAO {
	SqlSession sqlSession = MybatisSqlSessionFactory.getSqlSessionFactory().openSession();

	@Override
	public User selectUserInfo(String userId) throws Exception {
		return sqlSession.selectOne("mapper.user.selectUser",userId);
	}
}
