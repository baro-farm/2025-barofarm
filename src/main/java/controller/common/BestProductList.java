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
 * Servlet implementation class BestProductList
 */
@WebServlet("/bestProductList")
public class BestProductList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BestProductList() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String pageStr = request.getParameter("page");
		int page = (pageStr == null) ? 1 : Integer.parseInt(pageStr);
		
		PageInfo pageInfo = new PageInfo(page);

		UserProductService service = new UserProductServiceImpl();
		try {
			List<ProductVO> bestList = service.BestProductByPage(pageInfo);

			request.setAttribute("pageInfo", pageInfo);
			request.setAttribute("productList", bestList);
			request.setAttribute("cateName", "베스트");

			request.getRequestDispatcher("productList.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("err", "베스트 상품 조회 실패");
		}
	}

}
