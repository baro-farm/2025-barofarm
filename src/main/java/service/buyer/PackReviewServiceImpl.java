package service.buyer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import dao.buyer.PackReviewDAO;
import dao.buyer.PackReviewDAOImple;
import util.MybatisSqlSessionFactory;
import vo.PackReviewVO;

public class PackReviewServiceImpl implements PackReviewSerivce {

	private PackReviewDAO pkReviewDao(SqlSession sqlSession) {
		return new PackReviewDAOImple(sqlSession);
	}

	@Override
	public List<PackReviewVO> selectUserWritableReviewList(String userId) throws Exception {
		try (SqlSession sqlSession = MybatisSqlSessionFactory.getSqlSessionFactory().openSession()) {
			return pkReviewDao(sqlSession).selectWritableReviewList(userId);
		}
	}

	@Override
	public List<PackReviewVO> selectUserWrittenReviewList(String userId) throws Exception {
		// TODO Auto-generated method stub
		try (SqlSession sqlSession = MybatisSqlSessionFactory.getSqlSessionFactory().openSession()) {
			return pkReviewDao(sqlSession).selectWrittenReviewList(userId);
		}
	}

	@Override
	public void insertUserPackReview(PackReviewVO prodReview) throws Exception {
		try (SqlSession sqlSession = MybatisSqlSessionFactory.getSqlSessionFactory().openSession(false)) {

			pkReviewDao(sqlSession).inserPackReview(prodReview);
			sqlSession.commit();

		}
	}

	// seller List

	@Override
	public List<PackReviewVO> selectSellerPackReviewList(Long sellerNum, String commentStat, String sort,
			String ratingFilter, int offset, int pageSize) throws Exception {
		try (SqlSession sqlSession = MybatisSqlSessionFactory.getSqlSessionFactory().openSession()) {
			Map<String, Object> param = new HashMap<>();
			param.put("sellerNum", sellerNum);
			param.put("commentStat", commentStat);
			param.put("sort", sort);
			param.put("ratingFilter", ratingFilter);
			param.put("offset", offset);
			param.put("pageSize", pageSize);
			return pkReviewDao(sqlSession).selectPackReviewList(param);
		}
	}

	@Override
	public Integer selectSellerCountPackReview(Long sellerNum, String commentStat, String ratingFilter)
			throws Exception {
		try (SqlSession sqlSession = MybatisSqlSessionFactory.getSqlSessionFactory().openSession()) {

			Map<String, Object> param = new HashMap<>();
			param.put("sellerNum", sellerNum);
			param.put("commentStat", commentStat);
			param.put("ratingFilter", ratingFilter);
			return pkReviewDao(sqlSession).selectCountPackReview(param);
		}
	}

	@Override
	public void insertPackReviewComment(List<Long> pkReviewNums, String commentContent) throws Exception {
		try (SqlSession sqlSession = MybatisSqlSessionFactory.getSqlSessionFactory().openSession(false)) {

			Map<String, Object> param = new HashMap<>();
			param.put("pkReviewNums", pkReviewNums);
			param.put("commentContent", commentContent);
			pkReviewDao(sqlSession).insertSellerPackReviewComment(param);
			sqlSession.commit();
		}
	}

	@Override
	public PackReviewVO selectPackReviewDetailByReviewNum(Long pkReviewNums) {
		try (SqlSession sqlSession = MybatisSqlSessionFactory.getSqlSessionFactory().openSession()) {

			return pkReviewDao(sqlSession).selectPackReviewDetailByReviewNum(pkReviewNums);
		}
	}

	@Override
	public Integer selectCountUserWrittenReviews(Long userNum, String period) throws Exception {
		try (SqlSession sqlSession = MybatisSqlSessionFactory.getSqlSessionFactory().openSession()) {

			Map<String, Object> param = new HashMap<>();
			param.put("userNum", userNum);
			param.put("period", period);
			return pkReviewDao(sqlSession).selectCountUserWrittenReviews(param);
		}
	}

	@Override
	public List<PackReviewVO> selectUserWrittenReviews(Long userNum, String period, int offset, int pageSize)
			throws Exception {
		try (SqlSession sqlSession = MybatisSqlSessionFactory.getSqlSessionFactory().openSession()) {

			Map<String, Object> param = new HashMap<>();
			param.put("userNum", userNum);
			param.put("period", period);
			param.put("offset", offset);
			param.put("pageSize", pageSize);
			return pkReviewDao(sqlSession).selectUserWrittenReviews(param);
		}
	}

	@Override
	public Integer selectCountUserWritableReviews(Long userNum, String period) throws Exception {
		try (SqlSession sqlSession = MybatisSqlSessionFactory.getSqlSessionFactory().openSession()) {

			Map<String, Object> param = new HashMap<>();
			param.put("userNum", userNum);
			param.put("period", period);
			return pkReviewDao(sqlSession).selectCountUserWritableReviews(param);
		}
	}

	@Override
	public List<PackReviewVO> selectUserWritableReviews(Long userNum, String period, int offset, int pageSize)
			throws Exception {
		try (SqlSession sqlSession = MybatisSqlSessionFactory.getSqlSessionFactory().openSession()) {

			Map<String, Object> param = new HashMap<>();
			param.put("userNum", userNum);
			param.put("period", period);
			param.put("offset", offset);
			param.put("pageSize", pageSize);
			return pkReviewDao(sqlSession).selectUserWritableReviews(param);
		}
	}

}
