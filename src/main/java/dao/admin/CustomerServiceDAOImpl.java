package dao.admin;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import dto.admin.AdminAnswer;
import dto.admin.AdminQuestion;
import dto.admin.CustomerService;
import util.MybatisSqlSessionFactory;

public class CustomerServiceDAOImpl implements CustomerServiceDAO {
	SqlSession sqlSession = MybatisSqlSessionFactory.getSqlSessionFactory().openSession();
	
	@Override
	public List<CustomerService> selectCSList() throws Exception {
		return sqlSession.selectList("mapper.customerService.selectAdminQuestionList");
	}

	@Override
	public AdminQuestion selectCS(Long questionNum) throws Exception {
		return sqlSession.selectOne("mapper.customerService.selectAdminQuestion", questionNum);
	}
	
	@Override
	public void insertAnswer(AdminAnswer adminAnswer) throws Exception {
		sqlSession.insert("mapper.customerService.insertAdminAnswer", adminAnswer);
		sqlSession.commit();		
	}
	
	public AdminAnswer selectAdminAnswer(Long questionNum) throws Exception {
		return sqlSession.selectOne("mapper.customerService.selectAdminAnswer", questionNum);
	}
	
	public void updateAdminAnswer(AdminAnswer adminAnswer) throws Exception {
		sqlSession.update("mapper.customerService.updateAdminAnswer", adminAnswer);
		sqlSession.commit();
	}
}
