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

import service.buyer.ProdReviewSerivce;
import service.buyer.ProdReviewServiceImpl;
import vo.ProdReviewVO;

/**
 * Servlet implementation class ProdWritableReviewList
 */
@WebServlet("/prodWritableReviewList")
public class ProdWritableReviewList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProdWritableReviewList() {
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
		
		
		ProdReviewSerivce service = new ProdReviewServiceImpl();
		List<ProdReviewVO> prodReviewList = null;
		try {
			
			prodReviewList = service.selectUserWritableReviewList(sessionUser.getUserId());
			request.setAttribute("prodReviewList", prodReviewList);
			request.getRequestDispatcher("/buyer/writableReviewList.jsp").forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
			request.getRequestDispatcher("/login.jsp").forward(request, response);

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
