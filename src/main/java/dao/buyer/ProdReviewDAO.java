package dao.buyer;

import java.util.List;
import java.util.Map;

import dto.buyer.ProdReview;
import vo.ProdReviewVO;

public interface ProdReviewDAO {
	List<ProdReviewVO> selectWritableReviewList(String userId) throws Exception;
	List<ProdReview> selectWrittenReviewList(String userId) throws Exception;
	void inserProdReview(ProdReview prodReview) throws Exception; 
	
	//sellerList
	List<ProdReviewVO> selectProdReviewList(Map<String,Object> param) throws Exception;
	Integer selectCountProdReview(Map<String,Object> param) throws Exception;
	
	void insertSellerProdReviewComment(Map<String,Object> param) throws Exception;
}
