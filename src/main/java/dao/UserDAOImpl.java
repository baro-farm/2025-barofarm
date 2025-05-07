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
    private final SqlSession sqlSession;

    public UserDAOImpl(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }
    
	@Override
	public void insertUser(User user) throws Exception {
			sqlSession.insert("mapper.user.insertUser", user);
	}

	@Override
	public User selectUser(String userId) throws Exception {
			return sqlSession.selectOne("mapper.user.selectUser", userId);
	}

	@Override
	public void deleteUser(String userId) throws Exception {
			sqlSession.update("mapper.user.deleteUser", userId);
	}

	@Override
	public void insertAdminQA(AdminQuestion adminQuestion) throws Exception {
		sqlSession.insert("mapper.adminQuestion.insertAdminQA", adminQuestion);
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
	}
	
	@Override
	public void resetPwd(Map<String, Object> param) throws Exception {
		sqlSession.update("mapper.user.resetPwd", param);
	}

	@Override
	public User existingPwd(String resetPwdToken) throws Exception {
		return sqlSession.selectOne("mapper.user.existingPwd", resetPwdToken);
	}
	
	@Override
	public User selectUserById(String userId) throws Exception {
		return sqlSession.selectOne("mapper.user.selectUser", userId);
	}

	@Override
	public String getPasswordByUserNum(Long userNum) {
		return sqlSession.selectOne("mapper.user.getPasswordByUserNum", userNum);
	}

	@Override
	public void updateFcmToken(Long userNum, String newFcmToken) throws Exception {
        Map<String, Object> param = new HashMap<>();
        param.put("userNum", userNum);
        param.put("fcmToken", newFcmToken);
        
		sqlSession.update("mapper.user.updateFcmToken", param);
	}

	@Override
	public String selectFcmToken(Long userNum) throws Exception {
		return sqlSession.selectOne("mapper.user.selectFcmToken", userNum);
	}

	@Override
	public void deleteAdminQA(Long questionNum) throws Exception {
		sqlSession.delete("mapper.adminQuestion.deleteAdminQA", questionNum);
	}

	@Override
	public boolean updateSellerAccountInfo(Long userNum, String pwd, String phone, String email, String storeName,
			String postCode)throws Exception  {
		Map<String, Object> param = new HashMap<>();
        param.put("userNum", userNum);
        param.put("pwd", pwd);
        param.put("phone", phone);
        param.put("email", email);
        param.put("storeName", storeName);
        param.put("postCode", postCode);

        int res = sqlSession.update("mapper.user.updateSellerAccountInfo", param);
        return res ==1;
	}

	@Override
	public void deleteFcmToken(Long userNum) throws Exception{
		sqlSession.update("mapper.user.deleteFcmToken",userNum);
	}

}
