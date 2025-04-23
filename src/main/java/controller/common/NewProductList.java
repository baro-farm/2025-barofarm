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
 * Servlet implementation class NewProductList
 */
@WebServlet("/newProductList")
public class NewProductList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewProductList() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String pageStr = request.getParameter("page");
		Integer curPage = (pageStr == null || pageStr.trim().equals("")) ? 1 : Integer.parseInt(pageStr);
		PageInfo pageInfo = new PageInfo(curPage, 20);

		UserProductService service = new UserProductServiceImpl();

		try {
			List<ProductVO> newList = service.NewProductByPage(pageInfo);

			request.setAttribute("pageInfo", pageInfo);
			request.setAttribute("productList", newList);
			request.setAttribute("cateName", "신상품");
			request.setAttribute("listType", "new");
			

			request.getRequestDispatcher("productList.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("err", "신상품 목록 조회 실패");
		}
	}

}
