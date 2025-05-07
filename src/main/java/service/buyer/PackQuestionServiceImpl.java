package service.buyer;

import java.util.ArrayList;
import java.util.List;

import dao.buyer.PackQuestionDAO;
import dao.buyer.PackQuestionDAOImpl;
import dao.buyer.ProdQuestionDAO;
import dao.buyer.ProdQuestionDAOImpl;
import dto.buyer.ProdQuestion;
import dto.seller.QuestionAnswer;
import vo.QuestionVO;

public class PackQuestionServiceImpl implements PackQuestionService {
	
	private PackQuestionDAO packQuestDao;
	
	public PackQuestionServiceImpl() {
		packQuestDao = new PackQuestionDAOImpl();
	}
	
	@Override
	public List<QuestionVO> selectUserPackQuestionList(String userId) throws Exception {
		List<QuestionVO> questionList = new ArrayList<>();
		questionList = packQuestDao.selectQuestionListWithPackage(userId);
		for(QuestionVO question :  questionList) {
			Long qaNum = question.getQaNum();
			Integer answerCount = packQuestDao.selectPackAnswerCount(qaNum);
			question.setAnswerCount(answerCount);
		}
		return questionList;
	}

	@Override
	public QuestionAnswer selectPackQuestionAnswer(Long qaNum) throws Exception {
		// TODO Auto-generated method stub
		return packQuestDao.selectPackAnswer(qaNum);
	}

}
