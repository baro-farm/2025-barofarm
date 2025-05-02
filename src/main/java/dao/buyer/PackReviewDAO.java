package dao.buyer;

import java.util.List;
import java.util.Map;

import vo.PackReviewVO;

public interface PackReviewDAO {
	List<PackReviewVO> selectWritableReviewList(String userId) throws Exception;
	List<PackReviewVO> selectWrittenReviewList(String userId) throws Exception;
	void inserPackReview(PackReviewVO packReview) throws Exception; 
	
	//sellerList
	List<PackReviewVO> selectPackReviewList(Map<String,Object> param) throws Exception;
	Integer selectCountPackReview(Map<String,Object> param) throws Exception;
	
	void insertSellerPackReviewComment(Map<String,Object> param) throws Exception;
	PackReviewVO selectPackReviewDetailByReviewNum(Long pkReviewNums);
}
