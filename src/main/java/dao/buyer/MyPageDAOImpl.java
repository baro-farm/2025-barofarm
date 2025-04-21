package dao.buyer;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import dto.buyer.ProdQuestion;
import dto.buyer.ProdReview;
import util.MybatisSqlSessionFactory;
import vo.MyPageSummaryVO;

public class MyPageDAOImpl implements MyPageDAO {
	SqlSession sqlSession = MybatisSqlSessionFactory.getSqlSessionFactory().openSession();

	@Override
	public MyPageSummaryVO selectMyPageSummary(Long userNum) throws Exception {
        return sqlSession.selectOne("mapper.myPageSummary.selectMyPageSummary", userNum);
	}

	@Override
	public List<ProdQuestion> selectRecentQuestions(Long userNum) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectList("mapper.myPageSummary.selectRecentQuestions",userNum);
	}

	@Override
	public List<ProdReview> selectRecentReviews(Long userNum) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectList("mapper.myPageSummary.selectRecentReviews",userNum);
	}

}
