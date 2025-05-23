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
import vo.PackReviewVO;
import vo.ProdReviewVO;

/**
 * Servlet implementation class DetailProductReview
 */
@WebServlet("/detailPackReview")
public class DetailPackageReview extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DetailPackageReview() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		long packNum = Long.parseLong(request.getParameter("packageNum"));
		String pageStr = request.getParameter("page");
		System.out.println("DetailPackReview/packNum: "+packNum+", pageStr: "+pageStr);
		
		Integer curPage = (pageStr == null || pageStr.trim().equals("")) ? 1 : Integer.parseInt(pageStr);
		
		UserProductService service = new UserProductServiceImpl();
		try {
			Integer totalCount = service.CountPackReview(packNum);
			PageInfo pageInfo = new PageInfo(curPage, 4,totalCount);
			
			List<PackReviewVO> reviewList = service.PackReview(packNum, pageInfo);
			
			request.setAttribute("reviewList", reviewList);
			request.setAttribute("pageInfo", pageInfo);
			request.setAttribute("section", "review");
			request.getRequestDispatcher("detailPackage.jsp").forward(request, response);
		}catch (Exception e){
			e.printStackTrace();
			request.setAttribute("err", "상품 리뷰 목록 조회에 실패했습니다.");			
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
