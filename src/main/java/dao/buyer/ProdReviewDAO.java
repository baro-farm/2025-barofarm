package dao.buyer;

import java.util.List;

import vo.ProdReviewVO;

public interface ProdReviewDAO {
	List<ProdReviewVO> selectWritableReview(String userId) throws Exception;
}
