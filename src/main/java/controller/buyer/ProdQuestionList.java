package controller.buyer;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.buyer.Address;
import dto.buyer.ProdQuestion;
import service.buyer.ProdQuestionService;
import service.buyer.ProdQuestionServiceImpl;
import vo.QuestionVO;


/**
 * Servlet implementation class QuestionList
 */
@WebServlet("/questionList")
public class ProdQuestionList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProdQuestionList() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		ProdQuestionService service = new ProdQuestionServiceImpl();
		List<QuestionVO> questionList = null;
		try {
			questionList = service.selectUserQuestionList("hong12");
			System.out.println(questionList);
			request.setAttribute("questionList", questionList);
			request.getRequestDispatcher("/buyer/questionList.jsp").forward(request, response);

			
		}catch(Exception e) {
			e.printStackTrace();
		}	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
