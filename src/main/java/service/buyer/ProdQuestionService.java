package service.buyer;

import java.util.List;

import dto.buyer.ProdQuestion;
import dto.seller.ProdAnswer;
import vo.QuestionVO;

public interface ProdQuestionService {
	List<QuestionVO> selectUserQuestionList(String userId) throws Exception;
	ProdAnswer selectQuestionAnswer(Long qaNum) throws Exception;
}
