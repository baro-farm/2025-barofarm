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
import vo.ProdOrderVO;

/**
 * Servlet implementation class ProductOrderList
 */
@WebServlet("/sellerProdOrderList")
public class ProductOrderList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductOrderList() {
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
		
		List<ProdOrderVO> prodOrderList = new ArrayList<>();
		SellerDetailService detailService = new SellerDetailServiceImpl();
		ProdOrderService service = new ProdOrderServiceImpl();
		
		int page=1;
		int pageSize=10;
		int totalCount=0;
		
		try {
			
            String dateType = request.getParameter("dateType") != null ? request.getParameter("dateType") : "paymentDate";
            String startDate = request.getParameter("startDate");
            String endDate = request.getParameter("endDate");
            String searchType = request.getParameter("searchType") != null ? request.getParameter("searchType") : "all";
            String searchKeyword = request.getParameter("searchKeyword");
            if (request.getParameter("page") != null) {
                page = Integer.parseInt(request.getParameter("page"));
            }
            

			Long sellerNum = detailService.selectSellerNumById(sessionUser.getUserId());
			System.out.println(sellerNum);
			totalCount =  service.countProductOrderList(sellerNum,dateType,startDate,endDate,searchType,searchKeyword);
			
			int totalPages = (int) Math.ceil((double) totalCount / pageSize);
			
			if (page > totalPages && totalPages > 0) {
		        page = totalPages;
		    }
		    if (page <= 0) {
		        page = 1;  // 페이지가 0 이하일 경우
		    }
			
			int offset = (page - 1) * pageSize;
			
			System.out.println("totalCount = " + totalCount);
			System.out.println("totalPages = " + totalPages);
			System.out.println("startDate: " + startDate);
			System.out.println("endDate: " + endDate);			
			prodOrderList = service.selectProductOrderList(sellerNum, offset, pageSize, dateType, startDate, endDate, searchType, searchKeyword);
            
            // 페이지 그룹 계산
            int pageGroupSize = 5;
            int currentGroup = (int) Math.ceil((double) page / pageGroupSize);
            int groupStartPage = (currentGroup - 1) * pageGroupSize + 1;
            int groupEndPage = Math.min(groupStartPage + pageGroupSize - 1, totalPages);

            request.setAttribute("prodOrderList", prodOrderList);
            request.setAttribute("totalPages", totalPages);
            request.setAttribute("currentPage", page);
            request.setAttribute("pageGroupSize", pageGroupSize);
            request.setAttribute("groupStartPage", groupStartPage);
            request.setAttribute("groupEndPage", groupEndPage);

            // 검색 조건 유지용
            request.setAttribute("dateType", dateType);
            request.setAttribute("startDate", startDate);
            request.setAttribute("endDate", endDate);
            request.setAttribute("searchType", searchType);
            request.setAttribute("searchKeyword", searchKeyword);

            request.getRequestDispatcher("/seller/productOrderList.jsp").forward(request, response);

            
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
