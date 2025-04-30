package service.buyer;

import java.util.List;

import dto.buyer.ProdReview;
import vo.ProdReviewVO;

public interface ProdReviewSerivce {
	List<ProdReviewVO> selectUserWritableReviewList(String userId) throws Exception;
	List<ProdReview> selectUserWrittenReviewList(String userId) throws Exception;
	void insertUserProdReview(ProdReview prodReview) throws Exception;
	
	//seller List
	List<ProdReviewVO> selectSellerProdReviewList(Long sellerNum, String commentStat, String sort, String ratingFilter, int offset, int pageSize) throws Exception;
	Integer selectSellerCountProdReview(Long sellerNum, String commentStat, String ratingFilter) throws Exception;
	
	void insertProdReviewComment(List<Long> reviewNums,String commentContent) throws Exception;

}
