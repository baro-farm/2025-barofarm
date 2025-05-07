package service.buyer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import dao.buyer.ProdReviewDAO;
import dao.buyer.ProdReviewDAOImple;
import dto.buyer.ProdReview;
import util.MybatisSqlSessionFactory;
import vo.PackReviewVO;
import vo.ProdReviewVO;

public class ProdReviewServiceImpl implements ProdReviewSerivce {

	private ProdReviewDAO pdReviewDao(SqlSession sqlSession) {
		return new ProdReviewDAOImple(sqlSession);
	}

	@Override
	public List<ProdReviewVO> selectUserWritableReviewList(String userId) throws Exception {
		try (SqlSession sqlSession = MybatisSqlSessionFactory.getSqlSessionFactory().openSession()) {

			return pdReviewDao(sqlSession).selectWritableReviewList(userId);
		}
	}

	@Override
	public List<ProdReview> selectUserWrittenReviewList(String userId) throws Exception {
		try (SqlSession sqlSession = MybatisSqlSessionFactory.getSqlSessionFactory().openSession()) {
			return pdReviewDao(sqlSession).selectWrittenReviewList(userId);
		}
	}

	@Override
	public void insertUserProdReview(ProdReview prodReview) throws Exception {
		try (SqlSession sqlSession = MybatisSqlSessionFactory.getSqlSessionFactory().openSession(false)) {

			pdReviewDao(sqlSession).inserProdReview(prodReview);
			sqlSession.commit();
		}
	}

	// seller List

	@Override
	public List<ProdReviewVO> selectSellerProdReviewList(Long sellerNum, String commentStat, String sort,
			String ratingFilter, int offset, int pageSize) throws Exception {
		try (SqlSession sqlSession = MybatisSqlSessionFactory.getSqlSessionFactory().openSession()) {

			Map<String, Object> param = new HashMap<>();
			param.put("sellerNum", sellerNum);
			param.put("commentStat", commentStat);
			param.put("sort", sort);
			param.put("ratingFilter", ratingFilter);
			param.put("offset", offset);
			param.put("pageSize", pageSize);
			return pdReviewDao(sqlSession).selectProdReviewList(param);
		}
	}

	@Override
	public Integer selectSellerCountProdReview(Long sellerNum, String commentStat, String ratingFilter)
			throws Exception {
		try (SqlSession sqlSession = MybatisSqlSessionFactory.getSqlSessionFactory().openSession()) {

			Map<String, Object> param = new HashMap<>();
			param.put("sellerNum", sellerNum);
			param.put("commentStat", commentStat);
			param.put("ratingFilter", ratingFilter);
			return pdReviewDao(sqlSession).selectCountProdReview(param);
		}
	}

	@Override
	public void insertProdReviewComment(List<Long> reviewNums, String commentContent) throws Exception {
		try (SqlSession sqlSession = MybatisSqlSessionFactory.getSqlSessionFactory().openSession(false)) {

			Map<String, Object> param = new HashMap<>();
			param.put("reviewNums", reviewNums);
			param.put("commentContent", commentContent);
			pdReviewDao(sqlSession).insertSellerProdReviewComment(param);
			sqlSession.commit();
		}
	}

	@Override
	public ProdReviewVO selectProdReviewDetailByReviewNum(Long reviewNum) {
		try (SqlSession sqlSession = MybatisSqlSessionFactory.getSqlSessionFactory().openSession()) {

			return pdReviewDao(sqlSession).selectProdReviewDetailByReviewNum(reviewNum);
		}
	}

	@Override
	public Integer selectCountUserWrittenReviews(Long userNum, String period) throws Exception {
		try (SqlSession sqlSession = MybatisSqlSessionFactory.getSqlSessionFactory().openSession()) {

			Map<String, Object> param = new HashMap<>();
			param.put("userNum", userNum);
			param.put("period", period);
			return pdReviewDao(sqlSession).selectCountUserWrittenReviews(param);
		}
	}

	@Override
	public List<ProdReviewVO> selectUserWrittenReviews(Long userNum, String period, int offset, int pageSize)
			throws Exception {
		try (SqlSession sqlSession = MybatisSqlSessionFactory.getSqlSessionFactory().openSession()) {

			Map<String, Object> param = new HashMap<>();
			param.put("userNum", userNum);
			param.put("period", period);
			param.put("offset", offset);
			param.put("pageSize", pageSize);
			return pdReviewDao(sqlSession).selectUserWrittenReviews(param);
		}
	}

	@Override
	public Integer selectCountUserWritableReviews(Long userNum, String period) throws Exception {
		try (SqlSession sqlSession = MybatisSqlSessionFactory.getSqlSessionFactory().openSession()) {

			Map<String, Object> param = new HashMap<>();
			param.put("userNum", userNum);
			param.put("period", period);
			return pdReviewDao(sqlSession).selectCountUserWritableReviews(param);
		}
	}

	@Override
	public List<ProdReviewVO> selectUserWritableReviewList(Long userNum, String period, int offset, int pageSize)
			throws Exception {
		try (SqlSession sqlSession = MybatisSqlSessionFactory.getSqlSessionFactory().openSession()) {

			Map<String, Object> param = new HashMap<>();
			param.put("userNum", userNum);
			param.put("period", period);
			param.put("offset", offset);
			param.put("pageSize", pageSize);
			return pdReviewDao(sqlSession).selectUserWritableReviews(param);
		}
	}

}
