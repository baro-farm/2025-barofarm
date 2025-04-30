package controller.seller;

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
import service.buyer.ProdReviewSerivce;
import service.buyer.ProdReviewServiceImpl;
import service.seller.SellerDetailService;
import service.seller.SellerDetailServiceImpl;
import vo.ProdReviewVO;

/**
 * Servlet implementation class ProdReviewList
 */
@WebServlet("/sellerProdReviewList")
public class ProdReviewList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProdReviewList() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		
		HttpSession session = request.getSession(false);
		User sessionUser =null;
		
		if(session != null) {
			sessionUser=(User)session.getAttribute("user");
		}
		if(sessionUser == null) {
			request.getRequestDispatcher("/login").forward(request, response);
			return;
		}
		
		List<ProdReviewVO> reviewList = new ArrayList<>();
		
		int page=1;
		int pageSize=10;
		int totalCount = 0;
		
		String commentStat = null;
		String sort = null;
		String ratingFilter = null;		
		
		SellerDetailService sellerService = new SellerDetailServiceImpl();
		ProdReviewSerivce service = new ProdReviewServiceImpl();
		
		try {
			Long sellerNum = sellerService.selectSellerNumById(sessionUser.getUserId());
			
			totalCount = service.selectSellerCountProdReview(sellerNum,commentStat,ratingFilter);
			
			if(request.getParameter("page")!= null) {
				page=Integer.parseInt(request.getParameter("page"));
			}
			
			int totalPages=(int)Math.ceil((double)totalCount/pageSize);
			
			if (page > totalPages && totalPages > 0) {
		        page = totalPages;
		    }
		    if (page <= 0) {
		        page = 1;  // 페이지가 0 이하일 경우
		    }

		    int offset = (page - 1) * pageSize;		
		    
		    sort = request.getParameter("sort");
			if (sort == null) {
				sort = "new";
			}

			commentStat = request.getParameter("commentStat");
			if (commentStat == null) {
				commentStat = "all";
			}

			ratingFilter = request.getParameter("ratingFilter");
			if (ratingFilter == null) {
				ratingFilter = "all";
			}

			reviewList = service.selectSellerProdReviewList(sellerNum, commentStat, sort, ratingFilter, offset, pageSize);
			System.out.println(reviewList);

			int pageGroupSize = 5;
			int currentGroup = (int) Math.ceil((double) page / pageGroupSize);
			int groupStartPage = (currentGroup - 1) * pageGroupSize + 1;
			int groupEndPage = Math.min(groupStartPage + pageGroupSize - 1, totalPages);

			request.setAttribute("reviewList", reviewList);

			request.setAttribute("groupStartPage", groupStartPage);
			request.setAttribute("groupEndPage", groupEndPage);
			request.setAttribute("totalPages", totalPages);
			request.setAttribute("currentPage", page);
			request.setAttribute("currentGroup", currentGroup);
			request.setAttribute("pageGroupSize", pageGroupSize);
			
			request.getRequestDispatcher("/seller/reviewList.jsp").forward(request, response);
			
			
		}catch(Exception e) {
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
