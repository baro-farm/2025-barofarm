package service.buyer;

import java.util.List;

import dto.buyer.ProdReview;
import vo.ProdReviewVO;

public interface ProdReviewSerivce {
	List<ProdReviewVO> selectUserWritableReviewList(String userId) throws Exception;
	List<ProdReview> selectUserWrittenReviewList(String userId) throws Exception;
	void insertUserProdReview(ProdReview prodReview) throws Exception;
}
