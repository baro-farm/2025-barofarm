package dao.buyer;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import dto.buyer.ProdQuestion;
import dto.seller.QuestionAnswer;
import util.MybatisSqlSessionFactory;
import vo.QuestionVO;

public class PackQuestionDAOImpl implements PackQuestionDAO {
	SqlSession sqlSession = MybatisSqlSessionFactory.getSqlSessionFactory().openSession();
	
	private Long selectUserNumById(String userId) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("mapper.user.selectUserNumById",userId);
	}
	
	@Override
	public List<QuestionVO> selectQuestionListWithPackage(String userId) throws Exception {
		Long userNum = selectUserNumById(userId);
		return sqlSession.selectList("mapper.packQuestion.selectQuestionListWithPackage",userNum);
	}

	@Override
	public Integer selectPackAnswerCount(Long qaNum) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("mapper.packQuestion.selectPackAnswerCount",qaNum);
	}

	@Override
	public QuestionAnswer selectPackAnswer(Long qaNum) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("mapper.packQuestion.selectPackAnswer",qaNum);
	}



}
