package service.buyer;

import java.util.List;

import dto.buyer.ProdQuestion;
import dto.seller.QuestionAnswer;
import vo.QuestionVO;

public interface ProdQuestionService {
	List<QuestionVO> selectUserQuestionList(String userId) throws Exception;
	QuestionAnswer selectQuestionAnswer(Long qaNum) throws Exception;
}
