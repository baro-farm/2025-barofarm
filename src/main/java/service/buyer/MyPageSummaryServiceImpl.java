package service.buyer;

import java.util.List;

import dao.buyer.MyPageDAO;
import dao.buyer.MyPageDAOImpl;
import dto.buyer.ProdQuestion;
import dto.buyer.ProdReview;
import vo.MyPageSummaryVO;

public class MyPageSummaryServiceImpl implements MyPageSummaryService{
	private MyPageDAO myPageDao;
	
	public MyPageSummaryServiceImpl() {
		myPageDao = new MyPageDAOImpl();
	}
	
	@Override
	public MyPageSummaryVO selectUserMyPageSummary(Long userNum) throws Exception {
		// TODO Auto-generated method stub
		return myPageDao.selectMyPageSummary(userNum);
	}

	@Override
	public List<ProdQuestion> selectUserRecentQuestions(Long userNum) throws Exception {
		// TODO Auto-generated method stub
		return myPageDao.selectRecentQuestions(userNum);
	}

	@Override
	public List<ProdReview> selectUserRecentReviews(Long userNum) throws Exception {
		// TODO Auto-generated method stub
		return myPageDao.selectRecentReviews(userNum);
	}

}
