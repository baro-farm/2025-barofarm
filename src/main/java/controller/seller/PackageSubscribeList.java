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
import service.seller.PackageService;
import service.seller.PackageServiceImpl;
import service.seller.SellerDetailService;
import service.seller.SellerDetailServiceImpl;
import util.PageInfo;
import vo.PackOrderVO;
import vo.PackSubVO;

/**
 * Servlet implementation class PackageSubscribeList
 */
@WebServlet("/sellerPackSubscribeList")
public class PackageSubscribeList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PackageSubscribeList() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
		
		String pageStr = request.getParameter("page");
		Integer curPage = (pageStr == null || pageStr.trim().equals("")) ? 1 : Integer.parseInt(pageStr);
		
		SellerDetailService sellerService = new SellerDetailServiceImpl();
		PackageService service = new PackageServiceImpl();
		try {
            String startDate = request.getParameter("startDate");
            String endDate = request.getParameter("endDate");
            String searchType = request.getParameter("searchType") != null ? request.getParameter("searchType") : "all";
            String searchKeyword = request.getParameter("searchKeyword");
			System.out.println("packsubscribeList/startDate: "+startDate+", endDate: "+endDate+
					", searchType: "+searchType+", searchKeyword: "+searchKeyword);
			
			Long sellerNum = sellerService.selectSellerNumById(sessionUser.getUserId());
			System.out.println("packsublist/sellerNum: "+sellerNum);
			Integer totalCount = service.countPackageSubList(sellerNum);
			PageInfo pageInfo = new PageInfo(curPage, 15, totalCount);
			
			List<PackSubVO> packSubList = service.selectPackageSubList(pageInfo, sellerNum, startDate, endDate, searchType, searchKeyword);
			System.out.println("packsublist/packSubList: "+packSubList.size());
			request.setAttribute("pageInfo", pageInfo);
			request.setAttribute("packSubList", packSubList);
			
            request.setAttribute("startDate", startDate);
            request.setAttribute("endDate", endDate);
            request.setAttribute("searchType", searchType);
            request.setAttribute("searchKeyword", searchKeyword);
            
    		request.getRequestDispatcher("/seller/packageSubscribeList.jsp").forward(request, response);
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
