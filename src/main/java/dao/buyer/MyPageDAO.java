package dao.buyer;

import java.util.List;

import dto.buyer.ProdQuestion;
import dto.buyer.ProdReview;
import vo.MyPageSummaryVO;

public interface MyPageDAO {
    MyPageSummaryVO selectMyPageSummary(Long userNum) throws Exception;
    List<ProdQuestion> selectRecentQuestions(Long userNum) throws Exception;
    List<ProdReview> selectRecentReviews(Long userNum) throws Exception;

}
