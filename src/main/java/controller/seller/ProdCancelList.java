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
import service.buyer.ProdOrderService;
import service.buyer.ProdOrderServiceImpl;
import service.seller.SellerDetailService;
import service.seller.SellerDetailServiceImpl;
import vo.ProdCancelVO;

/**
 * Servlet implementation class ProdCancelList
 */
@WebServlet("/sellerCancelList")
public class ProdCancelList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProdCancelList() {
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
		List<ProdCancelVO> prodCancelList= new ArrayList<>();
		SellerDetailService detailService = new SellerDetailServiceImpl();
		ProdOrderService service = new ProdOrderServiceImpl();
		
		int page=1;
		int pageSize=10;
		int totalCount=0;
		String sort = null;
		
	
		try {
			
			Long sellerNum = detailService.selectSellerNumById(sessionUser.getUserId());
			totalCount = service.countSellerCancelList(sellerNum, sort);
			int totalPages=(int)Math.ceil((double)totalCount/pageSize);
			
			if (page > totalPages && totalPages > 0) {
		        page = totalPages;
		    }
		    if (page <= 0) {
		        page = 1;  // 페이지가 0 이하일 경우
		    }

		    int offset = (page - 1) * pageSize;
			
		    sort = request.getParameter("sort");
		    if(sort == null) {
		    	sort = "new";
		    }
		    
		    prodCancelList = service.selectSellerCancelList(sellerNum, sort, offset, offset);
		    
			int pageGroupSize = 5;
			int currentGroup = (int) Math.ceil((double) page / pageGroupSize);
			int groupStartPage = (currentGroup - 1) * pageGroupSize + 1;
			int groupEndPage = Math.min(groupStartPage + pageGroupSize - 1, totalPages);

			request.setAttribute("prodCancelList", prodCancelList);
			request.setAttribute("groupStartPage", groupStartPage);
			request.setAttribute("groupEndPage", groupEndPage);
			request.setAttribute("totalPages", totalPages);
			request.setAttribute("currentPage", page);
			request.setAttribute("currentGroup", currentGroup);
			request.setAttribute("pageGroupSize", pageGroupSize);
			request.setAttribute("param.sort", sort);

            request.getRequestDispatcher("/seller/prodCancelList.jsp").forward(request, response);

			
			
		}catch (Exception e) {
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
