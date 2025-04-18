package dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import dto.User;
import dto.admin.AdminAnswer;
import dto.admin.AdminQuestion;
import util.MybatisSqlSessionFactory;
import vo.AdminQuestionVO;

public class UserDAOImpl implements UserDAO {
	SqlSession sqlSession = MybatisSqlSessionFactory.getSqlSessionFactory().openSession();
	
	@Override
	public void insertUser(User user) throws Exception {
			sqlSession.insert("mapper.user.insertUser", user);
			sqlSession.commit();
			
	}

	@Override
	public User selectUser(String userId) throws Exception {
			return sqlSession.selectOne("mapper.user.selectUser", userId);
	}

	@Override
	public void deleteUser(String userId) throws Exception {
			sqlSession.update("mapper.user.deleteUser", userId);
			sqlSession.commit();
	}

	@Override
	public void insertAdminQ(AdminQuestion adminQuestion) throws Exception {
		sqlSession.insert("mapper.adminQuestion.insertAdminQ", adminQuestion);
		sqlSession.commit();
	}

	@Override
	public List<AdminQuestionVO> AdminQList() throws Exception {
		return sqlSession.selectList("mapper.adminQuestion.adminQList");
	}

	@Override
	public AdminQuestionVO detailAdminQA(Long questionNum) throws Exception {
		return sqlSession.selectOne("mapper.adminQuestion.selectAdminQA", questionNum);
	}

	
}
