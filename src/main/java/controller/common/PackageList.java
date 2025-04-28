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
@WebServlet("/packageList")
public class PackageList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PackageList() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		Integer cateNum = Integer.parseInt(request.getParameter("cateNum"));
		String pageStr = request.getParameter("page");
		String sort = request.getParameter("sort");
		
		//카테고리
		String cateName = null;
		switch (cateNum) {
		    case 8: cateName = "1인 꾸러미"; break;
		    case 9: cateName = "2인 꾸러미"; break;
		    case 10: cateName = "3인 꾸러미"; break;
		    case 11: cateName = "4인 꾸러미"; break;
		}
		
		// 페이징
		Integer curPage = (pageStr == null || pageStr.trim().equals("")) ? 1 : Integer.parseInt(pageStr);
		
		UserProductService service = new UserProductServiceImpl();
		try {
			Integer totalCount = service.ProductCount(cateNum);
			PageInfo pageInfo = new PageInfo(curPage, 20,totalCount);

			List<PackageVO> packageList = service.PackageByCategory(pageInfo, cateNum, sort);

			request.setAttribute("packageList", packageList);
			request.setAttribute("productPageInfo", pageInfo);
			request.setAttribute("cateNum", cateNum);
			request.setAttribute("cateName", cateName);
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
