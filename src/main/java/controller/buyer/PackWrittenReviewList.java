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
import service.buyer.PackReviewSerivce;
import service.buyer.PackReviewServiceImpl;
import service.buyer.ProdReviewSerivce;
import service.buyer.ProdReviewServiceImpl;
import service.buyer.UserService;
import service.buyer.UserServiceImpl;
import vo.PackReviewVO;

/**
 * Servlet implementation class ProdWrittenReviewList
 */
@WebServlet("/packWrittenReviewList")
public class PackWrittenReviewList extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PackWrittenReviewList() {
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
		
		if (sessionUser == null) {
			request.getRequestDispatcher("/login").forward(request, response);
			return;
		}
		
		
		PackReviewSerivce service = new PackReviewServiceImpl();
		UserService userService = new UserServiceImpl();

		List<PackReviewVO> packReviewList = null;
		
		
		int page=1;
		int pageSize=5;
		int totalCount=0;
		
		try {
			
            String period = request.getParameter("reviewFilterPeriod");
            if (period == null) period = "1개월";
			
			Long userNum = userService.selectUserNumByUserId(sessionUser.getUserId());
			System.out.println(userNum);
			
			if (request.getParameter("page") != null) {
                page = Integer.parseInt(request.getParameter("page"));
            }
			
			totalCount = service.selectCountUserWrittenReviews(userNum, period);
			System.out.println(totalCount);
			int totalPages = (int) Math.ceil((double) totalCount / pageSize);
			
			if (page > totalPages && totalPages > 0) {
		        page = totalPages;
		    }
		    if (page <= 0) {
		        page = 1;  // 페이지가 0 이하일 경우
		    }
		    
		    
			
			int offset = (page - 1) * pageSize;
		
			packReviewList = service.selectUserWrittenReviews(userNum, period, offset, pageSize);
			System.out.println(packReviewList);
			// 페이지 그룹 계산
            int pageGroupSize = 5;
            int currentGroup = (int) Math.ceil((double) page / pageGroupSize);
            int groupStartPage = (currentGroup - 1) * pageGroupSize + 1;
            int groupEndPage = Math.min(groupStartPage + pageGroupSize - 1, totalPages);
			
			
			
			
			request.setAttribute("packReviewList", packReviewList);
            request.setAttribute("currentPage", page);
            request.setAttribute("totalPages", totalPages);
            request.setAttribute("groupStartPage", groupStartPage);
            request.setAttribute("groupEndPage", groupEndPage);
            request.setAttribute("pageGroupSize", pageGroupSize);
            request.setAttribute("reviewFilterPeriod", period);
			
			
			
			request.getRequestDispatcher("/buyer/packWrittenReviewList.jsp").forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
			request.getRequestDispatcher("/login.jsp").forward(request, response);

		}		
	}

}
