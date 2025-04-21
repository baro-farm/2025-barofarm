package controller.buyer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dto.User;
import dto.buyer.ProdQuestion;
import dto.buyer.ProdReview;
import service.buyer.MyPageSummaryService;
import service.buyer.MyPageSummaryServiceImpl;
import service.buyer.UserService;
import service.buyer.UserServiceImpl;
import vo.MyPageSummaryVO;

/**
 * Servlet implementation class MyPageMain
 */
@WebServlet("/myPageMain")
public class MyPageMain extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyPageMain() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		HttpSession session = request.getSession(false);
		User sessionUser =null;
		
		if(session != null) {
			sessionUser=(User)session.getAttribute("user");
		}
		MyPageSummaryService service = new MyPageSummaryServiceImpl();
		UserService userService = new UserServiceImpl();

		MyPageSummaryVO myPageVo =  null;
		List<ProdQuestion> prodQuestions = new ArrayList<>();
		List<ProdReview> prodReviews = new ArrayList<>();
		
		try {
			System.out.println(sessionUser.getUserId());
			Long userNum = userService.selectUserNumByUserId(sessionUser.getUserId());
			myPageVo = service.selectUserMyPageSummary(userNum);
			prodQuestions = service.selectUserRecentQuestions(userNum);
			prodReviews=service.selectUserRecentReviews(userNum);
			
			request.setAttribute("myPageVo", myPageVo);
			request.setAttribute("prodQuestions", prodQuestions);
			request.setAttribute("prodReviews", prodReviews);
			request.getRequestDispatcher("/buyer/mypageMain.jsp").forward(request, response);

		}catch(Exception e ) {
			e.printStackTrace();
		}
				
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
