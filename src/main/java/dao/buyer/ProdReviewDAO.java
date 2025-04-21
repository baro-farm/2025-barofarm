package dao.buyer;

import java.util.List;

import dto.buyer.ProdReview;
import vo.ProdReviewVO;

public interface ProdReviewDAO {
	List<ProdReviewVO> selectWritableReviewList(String userId) throws Exception;
	List<ProdReview> selectWrittenReviewList(String userId) throws Exception;
	void inserProdReview(ProdReview prodReview) throws Exception; 
}
