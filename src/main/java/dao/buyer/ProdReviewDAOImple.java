package dao.buyer;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import dto.buyer.ProdReview;
import util.MybatisSqlSessionFactory;
import vo.ProdReviewVO;

public class ProdReviewDAOImple implements ProdReviewDAO {
	SqlSession sqlSession = MybatisSqlSessionFactory.getSqlSessionFactory().openSession();

	@Override
	public List<ProdReviewVO> selectWritableReviewList(String userId) throws Exception {
		Long userNum = sqlSession.selectOne("mapper.user.selectUserNumById",userId);
		return sqlSession.selectList("mapper.prodReview.selectWritableReview",userNum);
	}

	@Override
	public List<ProdReview> selectWrittenReviewList(String userId) throws Exception {
		Long userNum = sqlSession.selectOne("mapper.user.selectUserNumById",userId);
		return sqlSession.selectList("mapper.prodReview.selectWrittenReviewList",userNum);
	}

	@Override
	public void inserProdReview(ProdReview prodReview) throws Exception {
		sqlSession.insert("mapper.prodReview.insertProdReview",prodReview);	
		sqlSession.commit();
	}


}
