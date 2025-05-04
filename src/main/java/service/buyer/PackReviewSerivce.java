package service.buyer;

import java.util.List;

import dto.buyer.ProdReview;
import vo.PackReviewVO;
import vo.ProdReviewVO;

public interface PackReviewSerivce {
	List<PackReviewVO> selectUserWritableReviewList(String userId) throws Exception;
	List<PackReviewVO> selectUserWrittenReviewList(String userId) throws Exception;
	void insertUserPackReview(PackReviewVO packReview) throws Exception;
	
	//seller List
	List<PackReviewVO> selectSellerPackReviewList(Long sellerNum, String commentStat, String sort, String ratingFilter, int offset, int pageSize) throws Exception;
	Integer selectSellerCountPackReview(Long sellerNum, String commentStat, String ratingFilter) throws Exception;
	
	PackReviewVO selectPackReviewDetailByReviewNum(Long pkReviewNum) throws Exception;
	void insertPackReviewComment(List<Long> pkReviewNums,String commentContent) throws Exception;

	//buyer reviewList
    Integer selectCountUserWrittenReviews(Long userNum, String period) throws Exception;
    List<PackReviewVO> selectUserWrittenReviews(Long userNum, String period, int offset, int pageSize) throws Exception;

	//buyer reviewList
    Integer selectCountUserWritableReviews(Long userNum, String period) throws Exception;
    List<PackReviewVO> selectUserWritableReviews(Long userNum, String period, int offset, int pageSize) throws Exception;
	
}
