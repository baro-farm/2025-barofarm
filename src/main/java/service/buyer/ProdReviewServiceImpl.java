package service.buyer;

import java.util.List;

import dao.buyer.ProdReviewDAO;
import dao.buyer.ProdReviewDAOImple;
import vo.ProdReviewVO;

public class ProdReviewServiceImpl implements ProdReviewSerivce{
	
	private ProdReviewDAO pdReivewDao;
	
	public ProdReviewServiceImpl() {
		pdReivewDao = new ProdReviewDAOImple();
	}

	@Override
	public List<ProdReviewVO> selectUserWritableReviewList(String userId) throws Exception {
		return pdReivewDao.selectWritableReview(userId);
	}

}
