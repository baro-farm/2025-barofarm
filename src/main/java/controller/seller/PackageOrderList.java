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
import service.buyer.PackOrderService;
import service.buyer.PackOrderServiceImpl;
import service.seller.SellerDetailService;
import service.seller.SellerDetailServiceImpl;
import vo.PackOrderVO;

/**
 * Servlet implementation class packageOrderList
 */
@WebServlet("/sellerPackOrderList")
public class PackageOrderList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PackageOrderList() {
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
		
		List<PackOrderVO> packOrderList = new ArrayList<>();
		SellerDetailService sellerService = new SellerDetailServiceImpl();
		PackOrderService service = new PackOrderServiceImpl();
		int page=1;
		int pageSize=10;
		int totalCount=0;
		try {
			
			System.out.println("서블릿 doget");
            String dateType = request.getParameter("dateType") != null ? request.getParameter("dateType") : "paymentDate";
            String startDate = request.getParameter("startDate");
            String endDate = request.getParameter("endDate");
            String deliveryDay = request.getParameter("deliveryDay");
            String searchType = request.getParameter("searchType") != null ? request.getParameter("searchType") : "all";
            String searchKeyword = request.getParameter("searchKeyword");
            
            if (request.getParameter("page") != null) {
                page = Integer.parseInt(request.getParameter("page"));
            }
            
            Long sellerNum = sellerService.selectSellerNumById(sessionUser.getUserId());
			System.out.println(sellerNum);
			
            totalCount =  service.selectCountSellerPackOrderList(sellerNum, dateType, startDate, endDate, deliveryDay, searchType, searchKeyword);
            System.out.println(totalCount);
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

			packOrderList = service.selectSellerPackOrderList(sellerNum, offset, pageSize, dateType, startDate, endDate, deliveryDay, searchType, searchKeyword);
			System.out.println(packOrderList.size()+"size.");
			
			int pageGroupSize = 5;
			int currentGroup = (int) Math.ceil((double) page / pageGroupSize);
			int groupStartPage = (currentGroup - 1) * pageGroupSize + 1;
			int groupEndPage = Math.min(groupStartPage + pageGroupSize - 1, totalPages);
			
            // 값 전달
            request.setAttribute("packOrderList", packOrderList);
            request.setAttribute("totalPages", totalPages);
            request.setAttribute("currentPage", page);
            request.setAttribute("pageGroupSize", pageGroupSize);
            request.setAttribute("groupStartPage", groupStartPage);
            request.setAttribute("groupEndPage", groupEndPage);

            // 검색 조건 유지
            request.setAttribute("dateType", dateType);
            request.setAttribute("startDate", startDate);
            request.setAttribute("endDate", endDate);
            request.setAttribute("deliveryDay", deliveryDay);
            request.setAttribute("searchType", searchType);
            request.setAttribute("searchKeyword", searchKeyword);
            
    		request.getRequestDispatcher("/seller/packageOrderList.jsp").forward(request, response);

            
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
