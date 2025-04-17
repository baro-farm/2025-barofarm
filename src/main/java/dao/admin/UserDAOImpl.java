package dao.admin;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import dto.User;
import util.MybatisSqlSessionFactory;

public class UserDAOImpl implements UserDAO {
	SqlSession sqlSession = MybatisSqlSessionFactory.getSqlSessionFactory().openSession();
	
	@Override
	public List<User> selectUserList() throws Exception {
		return sqlSession.selectList("mapper.user.selectUserList");
	}

}
