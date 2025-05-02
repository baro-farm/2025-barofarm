package controller.seller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.buyer.ProdReview;
import service.buyer.PackReviewSerivce;
import service.buyer.PackReviewServiceImpl;
import service.buyer.ProdReviewSerivce;
import service.buyer.ProdReviewServiceImpl;
import vo.PackReviewVO;
import vo.ProdReviewVO;

/**
 * Servlet implementation class DetailReviewComment
 */
@WebServlet("/detailPackReviewComment")
public class DetailPackReviewComment extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DetailPackReviewComment() {
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
		
		PackReviewSerivce service = new PackReviewServiceImpl();
		PackReviewVO review;
		try {
			review = service.selectPackReviewDetailByReviewNum(reviewNum);
			request.setAttribute("review",review);
			request.getRequestDispatcher("/seller/detailPackReviewComment.jsp").forward(request, response);	

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		
		
		// 화면 띄워주기

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
