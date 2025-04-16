package dao.buyer;

import java.util.List;
import dto.buyer.ProdQuestion;
import dto.seller.ProdAnswer;
import vo.QuestionVO;


public interface ProdQuestionDAO {
	List<QuestionVO> selectQuestionList(String userId) throws Exception;
	Integer selectAnswerCount(Long qaNum) throws Exception;
	ProdAnswer selectAnswer(Long qaNum) throws Exception;

}
