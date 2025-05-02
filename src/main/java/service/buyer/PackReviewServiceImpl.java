package service.buyer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dao.buyer.PackReviewDAO;
import dao.buyer.PackReviewDAOImple;
import vo.PackReviewVO;

public class PackReviewServiceImpl implements PackReviewSerivce{
	
	private PackReviewDAO pkReviewDao;
	
	public PackReviewServiceImpl() {
		pkReviewDao = new PackReviewDAOImple() ;
	}

	@Override
	public List<PackReviewVO> selectUserWritableReviewList(String userId) throws Exception {
		return pkReviewDao.selectWritableReviewList(userId);
	}

	@Override
	public List<PackReviewVO> selectUserWrittenReviewList(String userId) throws Exception {
		// TODO Auto-generated method stub
		return pkReviewDao.selectWrittenReviewList(userId);
	}

	@Override
	public void insertUserPackReview(PackReviewVO prodReview) throws Exception {
		pkReviewDao.inserPackReview(prodReview);		
	}
	
	//seller List

	@Override
	public List<PackReviewVO> selectSellerPackReviewList(Long sellerNum, String commentStat, String sort, String ratingFilter, int offset, int pageSize) throws Exception {
	    Map<String, Object> param = new HashMap<>();
	    param.put("sellerNum", sellerNum);
	    param.put("commentStat", commentStat);
	    param.put("sort", sort);
	    param.put("ratingFilter", ratingFilter);
	    param.put("offset", offset);
	    param.put("pageSize", pageSize);		
	    return pkReviewDao.selectPackReviewList(param);
	}

	@Override
	public Integer selectSellerCountPackReview(Long sellerNum, String commentStat, String ratingFilter)
			throws Exception {
	    Map<String, Object> param = new HashMap<>();
	    param.put("sellerNum", sellerNum);
	    param.put("commentStat", commentStat);
	    param.put("ratingFilter", ratingFilter);
		return pkReviewDao.selectCountPackReview(param);
	}
	
	@Override
	public void insertPackReviewComment(List<Long> pkReviewNums, String commentContent) throws Exception {
		Map<String,Object> param = new HashMap<>();
		param.put("pkReviewNums", pkReviewNums);
		param.put("commentContent", commentContent);
		pkReviewDao.insertSellerPackReviewComment(param);
	}

	@Override
	public PackReviewVO selectPackReviewDetailByReviewNum(Long pkReviewNums) {
		return pkReviewDao.selectPackReviewDetailByReviewNum(pkReviewNums);
	}


}
