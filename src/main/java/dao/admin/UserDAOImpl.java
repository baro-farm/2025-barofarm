package dao.admin;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import dto.User;
import util.MybatisSqlSessionFactory;

public class UserDAOImpl implements UserDAO {
	SqlSession sqlSession = MybatisSqlSessionFactory.getSqlSessionFactory().openSession();
	
	@Override
	public List<User> selectUserList(Map<String, Object> param) throws Exception {
		return sqlSession.selectList("mapper.user.selectUserList", param);
	}

	@Override
	public int countUser(Map<String, Object> param) throws Exception {
		return sqlSession.selectOne("mapper.user.countUserList");
	}
}
