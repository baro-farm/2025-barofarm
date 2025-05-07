package controller.common;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.UserProductService;
import service.UserProductServiceImpl;
import util.PageInfo;
import vo.ProductVO;

/**
 * Servlet implementation class StoreProductList
 */
@WebServlet("/storeProductList")
public class ProductListByStore extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductListByStore() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");

		Long sellerNum = Long.parseLong(request.getParameter("sellerNum"));
		String storeName = "";
		String pageStr = request.getParameter("page");
		String sort = request.getParameter("sort");
		
		if (sort == null || sort.trim().equals("")) {
		    sort = "salesVolume";
		}
		
		Integer curPage = (pageStr == null || pageStr.trim().equals("")) ? 1 : Integer.parseInt(pageStr);
		PageInfo pageInfo = new PageInfo(curPage, 20);
		
		
		
		UserProductService service = new UserProductServiceImpl();
		
		try {
			List<ProductVO> productList = service.ProductBySellerNum(pageInfo, sellerNum, sort);
			if (productList != null && !productList.isEmpty()) {
			    storeName = productList.get(0).getStoreName(); // 첫 번째 상품의 storeName!
			}

			request.setAttribute("pageInfo", pageInfo);
			request.setAttribute("productList", productList);
			request.setAttribute("sellerNum", sellerNum); 
			request.setAttribute("storeName", storeName); 
			request.setAttribute("listType", "store");
			request.setAttribute("sort", sort);

			request.getRequestDispatcher("productList.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("err", "검색 결과 조회 실패");
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
