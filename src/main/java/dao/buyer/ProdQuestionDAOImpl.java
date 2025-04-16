package dao.buyer;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import dto.buyer.ProdQuestion;
import dto.seller.ProdAnswer;
import util.MybatisSqlSessionFactory;
import vo.QuestionVO;

public class ProdQuestionDAOImpl implements ProdQuestionDAO {
	SqlSession sqlSession = MybatisSqlSessionFactory.getSqlSessionFactory().openSession();
	
	private Long selectUserNumById(String userId) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("mapper.user.selectUserNumById",userId);
	}
	
	@Override
	public List<QuestionVO> selectQuestionList(String userId) throws Exception {
		Long userNum = selectUserNumById(userId);
		return sqlSession.selectList("mapper.prodQuestion.selectQuestionListWithProduct",userNum);
	}

	@Override
	public Integer selectAnswerCount(Long qaNum) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("mapper.prodQuestion.selectAnswerCount",qaNum);
	}

	@Override
	public ProdAnswer selectAnswer(Long qaNum) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("mapper.prodQuestion.selectAnswer",qaNum);
	}



}
