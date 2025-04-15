package dao;

import org.apache.ibatis.session.SqlSession;

import dto.User;
import util.MybatisSqlSessionFactory;

public class UserDAOImpl implements UserDAO {
	SqlSession sqlSession = MybatisSqlSessionFactory.getSqlSessionFactory().openSession();

	@Override
	public void insertUser(User user) throws Exception {
		sqlSession.insert("mapper.user.insertUser");
		sqlSession.commit();
	}

	@Override
	public User selectUser(String id) throws Exception {
		return sqlSession.selectOne("mapper.user.selectUser",id);
	}

}
