package service.buyer;

import java.util.List;

import dao.buyer.ProdReviewDAO;
import dao.buyer.ProdReviewDAOImple;
import dto.buyer.ProdReview;
import vo.ProdReviewVO;

public class ProdReviewServiceImpl implements ProdReviewSerivce{
	
	private ProdReviewDAO pdReivewDao;
	
	public ProdReviewServiceImpl() {
		pdReivewDao = new ProdReviewDAOImple();
	}

	@Override
	public List<ProdReviewVO> selectUserWritableReviewList(String userId) throws Exception {
		return pdReivewDao.selectWritableReviewList(userId);
	}

	@Override
	public List<ProdReview> selectUserWrittenReviewList(String userId) throws Exception {
		// TODO Auto-generated method stub
		return pdReivewDao.selectWrittenReviewList(userId);
	}

	@Override
	public void insertUserProdReview(ProdReview prodReview) throws Exception {
		pdReivewDao.inserProdReview(prodReview);		
	}


}
