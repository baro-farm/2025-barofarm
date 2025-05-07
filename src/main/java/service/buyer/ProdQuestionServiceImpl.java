package service.buyer;

import java.util.ArrayList;
import java.util.List;

import dao.buyer.ProdQuestionDAO;
import dao.buyer.ProdQuestionDAOImpl;
import dto.buyer.ProdQuestion;
import dto.seller.QuestionAnswer;
import vo.QuestionVO;

public class ProdQuestionServiceImpl implements ProdQuestionService {
	
	private ProdQuestionDAO questDao;
	
	public ProdQuestionServiceImpl() {
		questDao = new ProdQuestionDAOImpl();
	}
	
	@Override
	public List<QuestionVO> selectUserQuestionList(String userId) throws Exception {
		List<QuestionVO> questionList = new ArrayList<>();
		questionList = questDao.selectQuestionList(userId);
		for(QuestionVO question :  questionList) {
			Long quNum = question.getQaNum();
			Integer answerCount = questDao.selectAnswerCount(quNum);
			question.setAnswerCount(answerCount);
		}
		return questionList;
	}

	@Override
	public QuestionAnswer selectQuestionAnswer(Long qaNum) throws Exception {
		// TODO Auto-generated method stub
		return questDao.selectAnswer(qaNum);
	}

}
