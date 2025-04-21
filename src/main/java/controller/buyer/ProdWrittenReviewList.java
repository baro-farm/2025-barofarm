package controller.buyer;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dto.User;
import dto.buyer.ProdReview;
import service.buyer.ProdReviewSerivce;
import service.buyer.ProdReviewServiceImpl;

/**
 * Servlet implementation class ProdWrittenReviewList
 */
@WebServlet("/prodWrittenReviewList")
public class ProdWrittenReviewList extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ProdWrittenReviewList() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");

		HttpSession session = request.getSession(false);
		User sessionUser = null;

		if (session != null) {
			sessionUser = (User) session.getAttribute("user");
		}
		
		ProdReviewSerivce service = new ProdReviewServiceImpl();
		List<ProdReview> prodReviewList = null;
		try {
			
			prodReviewList = service.selectUserWrittenReviewList(sessionUser.getUserId());
			request.setAttribute("prodReviewList", prodReviewList);
			request.getRequestDispatcher("/buyer/prodWrittenReviewList.jsp").forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
			request.getRequestDispatcher("/login.jsp").forward(request, response);

		}		
	}

}
