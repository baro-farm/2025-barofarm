package dao.buyer;

import java.util.List;
import dto.buyer.ProdQuestion;
import dto.seller.QuestionAnswer;
import vo.QuestionVO;


public interface PackQuestionDAO {
	List<QuestionVO> selectQuestionListWithPackage(String userId) throws Exception;
	Integer selectPackAnswerCount(Long qaNum) throws Exception;
	QuestionAnswer selectPackAnswer(Long qaNum) throws Exception;

}
