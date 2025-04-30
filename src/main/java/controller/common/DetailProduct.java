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
		System.out.println("DetailProduct/productNum: "+productNum);
		
		String pageStr = request.getParameter("page");
		System.out.println("DetailProductReview/productNum: "+productNum+", pageStr: "+pageStr);
		Integer curPage = (pageStr == null || pageStr.trim().equals("")) ? 1 : Integer.parseInt(pageStr);
		
		UserProductService service = new UserProductServiceImpl();
		try {
			List<ProductOption> prodOption = service.ProdOption(productNum);
			ProductVO detailProduct = service.DetailProduct(productNum);
			
			Integer totalCount = service.CountReview(productNum);
			PageInfo pageInfo = new PageInfo(curPage, 5,totalCount);
			List<ProdReviewVO> reviewList = service.ProdReview(productNum, pageInfo);
			
	 		request.setAttribute("product", detailProduct);
			request.setAttribute("option", prodOption);
			request.setAttribute("reviewList", reviewList);
			request.setAttribute("pageInfo", pageInfo);
	 			
	        request.getRequestDispatcher("detailProduct.jsp").forward(request, response);
		}catch (Exception e) {
			e.printStackTrace();
		        request.setAttribute("err", "상품 상세 조회 실패");
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
