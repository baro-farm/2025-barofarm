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
import vo.ProdReviewVO;
import vo.ProductVO;

/**
 * Servlet implementation class DetailProduct
 */
@WebServlet("/detailProduct")
public class DetailProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DetailProduct() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
request.setCharacterEncoding("utf-8");
		
		Long productNum = Long.parseLong(request.getParameter("productNum"));
		String reviewPageStr = request.getParameter("reviewPage");
		String ajaxHeader = request.getHeader("X-Requested-With");
		boolean isAjax = "XMLHttpRequest".equals(ajaxHeader);

		// 페이징
		Integer curPage = (reviewPageStr == null || reviewPageStr.trim().equals("")) ? 1 : Integer.parseInt(reviewPageStr);
		PageInfo pageInfo = new PageInfo(curPage, 5);
		
		UserProductService service = new UserProductServiceImpl();
	
		try {
			List<ProdReviewVO> reviewList = service.ProdReview(productNum, pageInfo);
			List<ProductOption> prodOption = service.ProdOption(productNum);
			ProductVO detailProduct = service.DetailProduct(productNum);
			
			if (isAjax) {
				request.setAttribute("product", detailProduct);
	            request.setAttribute("review", reviewList);
	            request.setAttribute("pageInfo", pageInfo);
	            
	            request.getRequestDispatcher("detailReviewSection.jsp").forward(request, response);
	        }	        
	         else {
	 			request.setAttribute("product", detailProduct);
				request.setAttribute("option", prodOption);
		        request.setAttribute("review", reviewList);
		        request.setAttribute("pageInfo", pageInfo);
	 			
	            request.getRequestDispatcher("detailProduct.jsp").forward(request, response);
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
