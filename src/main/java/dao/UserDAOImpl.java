package dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import dto.User;
import dto.admin.AdminAnswer;
import dto.admin.AdminQuestion;
import dto.admin.Notice;
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
	public void insertAdminQA(AdminQuestion adminQuestion) throws Exception {
		sqlSession.insert("mapper.adminQuestion.insertAdminQA", adminQuestion);
		sqlSession.commit();
	}

	@Override
	public AdminQuestionVO detailAdminQA(Long questionNum) throws Exception {
		return sqlSession.selectOne("mapper.adminQuestion.selectAdminQA", questionNum);
	}

	@Override
	public List<AdminQuestion> selectRecentAdminQA() throws Exception {
		return sqlSession.selectList("mapper.adminQuestion.selectRecentAdminQA");
	}
	
	@Override
	public List<AdminQuestionVO> selectAdminQAListByPage(Map<String, Object> param) throws Exception {
		return sqlSession.selectList("mapper.adminQuestion.selectAdminQAListByPage", param);
	}

	@Override
	public Integer selectAdminQACount() throws Exception {
		return sqlSession.selectOne("mapper.adminQuestion.selectAdminQACount");
	}

	@Override
	public User findId(Map<String, Object> param) throws Exception {
		return sqlSession.selectOne("mapper.user.findId", param);
	}

	@Override
	public User findPwd(Map<String, Object> param) throws Exception {
		return sqlSession.selectOne("mapper.user.findPwd", param);
	}

	@Override
	public void resetPwdToken(Map<String, Object> param) throws Exception {
		sqlSession.update("mapper.user.resetPwdToken", param);
		sqlSession.commit();
	}

	@Override
	public User selectUserById(String userId) throws Exception {
		return sqlSession.selectOne("mapper.user.selectUser", userId);
	}

	
}
