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
@WebServlet("/productList")
public class ProductList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductList() {
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
		    case 1: cateName = "배추/무/대파/부추"; break;
		    case 2: cateName = "오이/호박/가지"; break;
		    case 3: cateName = "고추/피망/파프리카/열매채소"; break;
		    case 4: cateName = "감자/고구마"; break;
		    case 5: cateName = "양상추/양배추/새싹채소"; break;
		    case 6: cateName = "당근/연근/뿌리채소"; break;
		    case 7: cateName = "마늘/양파/생강/파"; break;
		    case 8: cateName = "혼밥 꾸러미"; break;
		    case 9: cateName = "커플 꾸러미"; break;
		    case 10: cateName = "트리오 꾸러미"; break;
		    case 11: cateName = "패밀리 꾸러미"; break;
		}
		
		// 페이징
		Integer curPage = (pageStr == null || pageStr.trim().equals("")) ? 1 : Integer.parseInt(pageStr);
		
		UserProductService service = new UserProductServiceImpl();
		try {
			Integer totalCount = service.ProductCount(cateNum);
			PageInfo pageInfo = new PageInfo(curPage, 16,totalCount);
			
			if(cateNum >= 8) {
				List<PackageVO> packageList = service.PackageByCategory(pageInfo, cateNum, sort);
				request.setAttribute("packageList", packageList);
			}else {
				List<ProductVO> productList = service.ProductByCategory(pageInfo, cateNum, sort);
				request.setAttribute("productList", productList);
			}

			request.setAttribute("pageInfo", pageInfo);
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