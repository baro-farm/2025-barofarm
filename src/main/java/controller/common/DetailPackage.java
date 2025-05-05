package controller.common;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.seller.ProductOption;
import service.UserProductService;
import service.UserProductServiceImpl;
import util.PageInfo;
import vo.PackReviewVO;
import vo.PackageVO;
import vo.ProdReviewVO;
import vo.ProductVO;

/**
 * Servlet implementation class DetailPackage
 */
@WebServlet("/detailPackage")
public class DetailPackage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DetailPackage() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
request.setCharacterEncoding("utf-8");
		
		Long packageNum = Long.parseLong(request.getParameter("packageNum"));
		String ajaxHeader = request.getHeader("X-Requested-With");
		boolean isAjax = "XMLHttpRequest".equals(request.getHeader("X-Requested-With"));

		// 페이징
		String pageStr = request.getParameter("page");
		Integer curPage = (pageStr == null || pageStr.trim().equals("")) ? 1 : Integer.parseInt(pageStr);
		
		UserProductService service = new UserProductServiceImpl();
	
		try {
			PackageVO detailPackage = service.DetailPackage(packageNum);
			
			Integer totalCount = service.CountPackReview(packageNum);
			PageInfo pageInfo = new PageInfo(curPage, 4,totalCount);
			System.out.println("DetailPackage/curPage: "+curPage+", totalCount:"+totalCount+", packageNum: "+packageNum);
			List<PackReviewVO> reviewList = service.PackReview(packageNum, pageInfo);
			System.out.println("쿼리 결과 개수: " + reviewList.size());
			if (isAjax) {
				request.setAttribute("pack", detailPackage);
	            request.setAttribute("review", reviewList);
	            request.setAttribute("pageInfo", pageInfo);
	            
	            request.getRequestDispatcher("detailPackReviewSection.jsp").forward(request, response);
	        }	        
	         else {
	 			request.setAttribute("pack", detailPackage);
		        request.setAttribute("review", reviewList);
		        request.setAttribute("pageInfo", pageInfo);
	 			
	            request.getRequestDispatcher("detailPackage.jsp").forward(request, response);
	        }
		}catch (Exception e) {
			e.printStackTrace();
		    if (isAjax) {
		        request.setAttribute("err", "리뷰 조회 실패");
		    } else {
		        request.setAttribute("err", "상품 상세 조회 실패");
		    }	
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
