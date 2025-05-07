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
import vo.PackageVO;
import vo.ProductVO;

/**
 * Servlet implementation class ProductList
 */
@WebServlet("/packageListByAll")
public class PackageListByAll extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PackageListByAll() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String pageStr = request.getParameter("page");
		String sort = request.getParameter("sort");
		
		// 페이징
		Integer curPage = (pageStr == null || pageStr.trim().equals("")) ? 1 : Integer.parseInt(pageStr);
		
		UserProductService service = new UserProductServiceImpl();
		try {
			Integer totalCount = service.countPackageByAll();
			PageInfo pageInfo = new PageInfo(curPage, 16,totalCount);

			List<PackageVO> packageList = service.PackageByAll(pageInfo, sort);

			request.setAttribute("packageList", packageList);
			request.setAttribute("pageInfo", pageInfo);
			request.setAttribute("cateName", "꾸러미");
			request.setAttribute("listType", "package");
			request.setAttribute("sort", sort);
			
			request.getRequestDispatcher("productList.jsp").forward(request, response);
		}catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("err", "상품 목록 조회를 실패했습니다.");			
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
