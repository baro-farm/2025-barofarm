package controller.seller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.buyer.ProdReview;
import service.buyer.ProdReviewSerivce;
import service.buyer.ProdReviewServiceImpl;
import vo.ProdReviewVO;

/**
 * Servlet implementation class DetailReviewComment
 */
@WebServlet("/detailReviewComment")
public class DetailReviewComment extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DetailReviewComment() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Long reviewNum = Long.parseLong(request.getParameter("reviewNum"));
		request.setAttribute("reviewNum", reviewNum);
		
		ProdReviewSerivce service = new ProdReviewServiceImpl();
		ProdReviewVO review = service.selectProdReviewDetailByReviewNum(reviewNum);
		request.setAttribute("review",review);
		
		
		// 화면 띄워주기
		request.getRequestDispatcher("/seller/detailReviewComment.jsp").forward(request, response);	
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
