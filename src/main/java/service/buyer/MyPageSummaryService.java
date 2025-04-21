package service.buyer;

import java.util.List;

import dto.buyer.ProdQuestion;
import dto.buyer.ProdReview;
import vo.MyPageSummaryVO;

public interface MyPageSummaryService {
    MyPageSummaryVO selectUserMyPageSummary(Long userNum) throws Exception;
    List<ProdQuestion> selectUserRecentQuestions(Long userNum) throws Exception;
    List<ProdReview> selectUserRecentReviews(Long userNum) throws Exception;
   
}
