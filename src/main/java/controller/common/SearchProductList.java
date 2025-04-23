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
 * Servlet implementation class SearchProductList
 */
@WebServlet("/searchProductList")
public class SearchProductList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchProductList() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");

		String keyword = request.getParameter("keyword");
		String pageStr = request.getParameter("page");
		String sort = request.getParameter("sort");
		
		int curPage = (pageStr == null || pageStr.trim().equals("")) ? 1 : Integer.parseInt(pageStr);
		
		if (sort == null || sort.trim().equals("")) {
		    sort = "salesVolume";
		}

		PageInfo pageInfo = new PageInfo(curPage, 20);
		UserProductService service = new UserProductServiceImpl();

		try {
			List<ProductVO> productList = service.searchProducts(pageInfo, keyword, sort);

			request.setAttribute("pageInfo", pageInfo);
			request.setAttribute("productList", productList);
			request.setAttribute("keyword", keyword); 
			request.setAttribute("listType", "search");
			request.setAttribute("sort", sort);

			request.getRequestDispatcher("productList.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("err", "검색 결과 조회 실패");
		}
	}

}
