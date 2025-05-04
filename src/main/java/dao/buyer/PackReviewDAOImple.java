package dao.buyer;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import util.MybatisSqlSessionFactory;
import vo.PackReviewVO;

public class PackReviewDAOImple implements PackReviewDAO {
	SqlSession sqlSession = MybatisSqlSessionFactory.getSqlSessionFactory().openSession();

	@Override
	public List<PackReviewVO> selectWritableReviewList(String userId) throws Exception {
		Long userNum = sqlSession.selectOne("mapper.user.selectUserNumById",userId);
		return sqlSession.selectList("mapper.packReview.selectWritableReview",userNum);
	}

	@Override
	public List<PackReviewVO> selectWrittenReviewList(String userId) throws Exception {
		Long userNum = sqlSession.selectOne("mapper.user.selectUserNumById",userId);
		return sqlSession.selectList("mapper.packReview.selectWrittenReviewList",userNum);
	}

	@Override
	public void inserPackReview(PackReviewVO packReview) throws Exception {
		sqlSession.insert("mapper.packReview.insertPackReview",packReview);	
		sqlSession.commit();
	}

	//seller List
	
	@Override
	public List<PackReviewVO> selectPackReviewList(Map<String, Object> param) throws Exception {
		return sqlSession.selectList("mapper.packReview.selectSellerPackReviewList",param);
	}

	@Override
	public Integer selectCountPackReview(Map<String, Object> param) throws Exception {
		return sqlSession.selectOne("mapper.packReview.selectSellerCountPackReview",param);
	}

	@Override
	public void insertSellerPackReviewComment(Map<String, Object> param) throws Exception {
		sqlSession.insert("mapper.packReview.insertSellerPackReviewComment",param);
		sqlSession.commit();
	}

	@Override
	public PackReviewVO selectPackReviewDetailByReviewNum(Long pkReviewNums) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("mapper.packReview.selectPackReviewDetailByReviewNum",pkReviewNums);
	}

	@Override
	public Integer selectCountUserWrittenReviews(Map<String, Object> param) throws Exception {
		return sqlSession.selectOne("mapper.packReview.selectCountPackUserWrittenReviews", param);
	}

	@Override
	public List<PackReviewVO> selectUserWrittenReviews(Map<String, Object> param) throws Exception {
		return sqlSession.selectList("mapper.packReview.selectUserPackWrittenReviews", param);
	}

	@Override
	public Integer selectCountUserWritableReviews(Map<String, Object> param) throws Exception {
		return sqlSession.selectOne("mapper.packReview.selectCountPackUserWritableReviews", param);

	}

	@Override
	public List<PackReviewVO> selectUserWritableReviews(Map<String, Object> param) throws Exception {
		return sqlSession.selectList("mapper.packReview.selectUserPackWritableReviews", param);

	}


}
