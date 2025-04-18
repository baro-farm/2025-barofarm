package service.buyer;

import java.util.List;

import vo.ProdReviewVO;

public interface ProdReviewSerivce {
	List<ProdReviewVO> selectUserWritableReviewList(String userId) throws Exception;
}
