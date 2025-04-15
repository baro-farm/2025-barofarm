package controller.buyer;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import dto.seller.ProdAnswer;
import service.buyer.ProdQuestionService;
import service.buyer.ProdQuestionServiceImpl;

/**
 * Servlet implementation class SelectProdAnswer
 */
@WebServlet("/selectProdAnswer")
public class SelectProdAnswer extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SelectProdAnswer() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		long qaNum = Long.parseLong(request.getParameter("qaNum"));
		ProdQuestionService service = new ProdQuestionServiceImpl();
		ProdAnswer prodAnswer = null;
		try {
			prodAnswer = service.selectQuestionAnswer(qaNum);
			if(prodAnswer != null) {
				JSONObject jsonObj = new JSONObject();
				jsonObj.put("content", prodAnswer.getContent());
				jsonObj.put("createdAt", prodAnswer.getCreatedAt().toString());
				
				response.setContentType("application/json;charset=utf-8");
				response.getWriter().write(jsonObj.toString());
			
			}
			}catch(Exception e) {
			e.printStackTrace();
		}
	
	}

}
