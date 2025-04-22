package service.buyer;

import java.util.List;

import dto.buyer.ProdQuestion;
import dto.seller.QuestionAnswer;
import vo.QuestionVO;

public interface PackQuestionService {
	List<QuestionVO> selectUserPackQuestionList(String userId) throws Exception;
	QuestionAnswer selectPackQuestionAnswer(Long qaNum) throws Exception;
}
