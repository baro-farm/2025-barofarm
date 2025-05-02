package service.buyer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dao.buyer.ProdReviewDAO;
import dao.buyer.ProdReviewDAOImple;
import dto.buyer.ProdReview;
import vo.ProdReviewVO;

public class ProdReviewServiceImpl implements ProdReviewSerivce{
	
	private ProdReviewDAO pdReviewDao;
	
	public ProdReviewServiceImpl() {
		pdReviewDao = new ProdReviewDAOImple();
	}

	@Override
	public List<ProdReviewVO> selectUserWritableReviewList(String userId) throws Exception {
		return pdReviewDao.selectWritableReviewList(userId);
	}

	@Override
	public List<ProdReview> selectUserWrittenReviewList(String userId) throws Exception {
		// TODO Auto-generated method stub
		return pdReviewDao.selectWrittenReviewList(userId);
	}

	@Override
	public void insertUserProdReview(ProdReview prodReview) throws Exception {
		pdReviewDao.inserProdReview(prodReview);		
	}
	
	//seller List

	@Override
	public List<ProdReviewVO> selectSellerProdReviewList(Long sellerNum, String commentStat, String sort, String ratingFilter, int offset, int pageSize) throws Exception {
	    Map<String, Object> param = new HashMap<>();
	    param.put("sellerNum", sellerNum);
	    param.put("commentStat", commentStat);
	    param.put("sort", sort);
	    param.put("ratingFilter", ratingFilter);
	    param.put("offset", offset);
	    param.put("pageSize", pageSize);		
	    return pdReviewDao.selectProdReviewList(param);
	}

	@Override
	public Integer selectSellerCountProdReview(Long sellerNum, String commentStat, String ratingFilter)
			throws Exception {
	    Map<String, Object> param = new HashMap<>();
	    param.put("sellerNum", sellerNum);
	    param.put("commentStat", commentStat);
	    param.put("ratingFilter", ratingFilter);
		return pdReviewDao.selectCountProdReview(param);
	}
	
	@Override
	public void insertProdReviewComment(List<Long> reviewNums, String commentContent) throws Exception {
		Map<String,Object> param = new HashMap<>();
		param.put("reviewNums", reviewNums);
		param.put("commentContent", commentContent);
		pdReviewDao.insertSellerProdReviewComment(param);
	}

	@Override
	public ProdReviewVO selectProdReviewDetailByReviewNum(Long reviewNum) {
		return pdReviewDao.selectProdReviewDetailByReviewNum(reviewNum);
	}


}
