package dao.buyer;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import dto.buyer.ProdReview;
import util.MybatisSqlSessionFactory;
import vo.PackReviewVO;
import vo.ProdReviewVO;

public class ProdReviewDAOImple implements ProdReviewDAO {
	private final SqlSession sqlSession;

	public ProdReviewDAOImple(SqlSession sqlSession) {

		this.sqlSession = sqlSession;

	}

	@Override
	public List<ProdReviewVO> selectWritableReviewList(String userId) throws Exception {
		Long userNum = sqlSession.selectOne("mapper.user.selectUserNumById", userId);
		return sqlSession.selectList("mapper.prodReview.selectWritableReview", userNum);
	}

	@Override
	public List<ProdReview> selectWrittenReviewList(String userId) throws Exception {
		Long userNum = sqlSession.selectOne("mapper.user.selectUserNumById", userId);
		return sqlSession.selectList("mapper.prodReview.selectWrittenReviewList", userNum);
	}

	@Override
	public void inserProdReview(ProdReview prodReview) throws Exception {
		sqlSession.insert("mapper.prodReview.insertProdReview", prodReview);
	}

	// seller List

	@Override
	public List<ProdReviewVO> selectProdReviewList(Map<String, Object> param) throws Exception {
		return sqlSession.selectList("mapper.prodReview.selectSellerProdReviewList", param);
	}

	@Override
	public Integer selectCountProdReview(Map<String, Object> param) throws Exception {
		return sqlSession.selectOne("mapper.prodReview.selectSellerCountProdReview", param);
	}

	@Override
	public void insertSellerProdReviewComment(Map<String, Object> param) throws Exception {
		sqlSession.insert("mapper.prodReview.insertSellerProdReviewComment", param);
	}

	@Override
	public ProdReviewVO selectProdReviewDetailByReviewNum(Long reviewNum) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("mapper.prodReview.selectProdReviewDetailByReviewNum", reviewNum);

	}

	@Override
	public Integer selectCountUserWrittenReviews(Map<String, Object> param) throws Exception {
		return sqlSession.selectOne("mapper.prodReview.selectCountProdUserWrittenReviews", param);
	}

	@Override
	public List<ProdReviewVO> selectUserWrittenReviews(Map<String, Object> param) throws Exception {
		return sqlSession.selectList("mapper.prodReview.selectUserProdWrittenReviews", param);
	}

	@Override
	public Integer selectCountUserWritableReviews(Map<String, Object> param) throws Exception {
		return sqlSession.selectOne("mapper.prodReview.selectCountProdUserWritableReviews", param);
	}

	@Override
	public List<ProdReviewVO> selectUserWritableReviews(Map<String, Object> param) throws Exception {
		return sqlSession.selectList("mapper.prodReview.selectUserProdWritableReviews", param);
	}

}
