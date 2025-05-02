package controller.seller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dto.User;
import service.buyer.KockCommentService;
import service.buyer.KockCommentServiceImpl;
import util.PageInfoSoy;
import util.SearchDtoSoy;
import vo.KockCommentVO;

/**
 * Servlet implementation class KockCommentList
 */
@WebServlet("/sellerKCList")
public class KockCommentList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public KockCommentList() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
request.setCharacterEncoding("utf-8");
		
		HttpSession session = request.getSession(false);
		User loginUser  =null;
		
		if(session != null) {
			loginUser =(User)session.getAttribute("user");
		}
		if (loginUser  == null) {
			response.sendRedirect(request.getContextPath() + "/login.jsp");
			return;
		}
		
		// 파라미터 처리
        SearchDtoSoy dto = new SearchDtoSoy();
        dto.setUserNum(loginUser.getUserNum());

		String pageParam = request.getParameter("page");
		int page = (pageParam != null && !pageParam.isEmpty()) ? Integer.parseInt(pageParam) : 1;

		dto.setPage(page); // ← 무조건 setPage() 사용!
		dto.setKeyword(request.getParameter("keyword"));
		dto.setSearchType(request.getParameter("searchType"));
		dto.setStartDateFrom(request.getParameter("startDateFrom"));
		dto.setStartDateTo(request.getParameter("startDateTo"));
		dto.setStatus(request.getParameter("status"));

        try {
            KockCommentService service = new KockCommentServiceImpl();

            int cnt = service.countSellerComments(dto);
			PageInfoSoy pageInfo = new PageInfoSoy(dto.getPage(), cnt, 5, dto.getRecordSize());

            List<KockCommentVO> commentList = service.getSellerComments(dto);


			request.setAttribute("pi", pageInfo);
			request.setAttribute("commentList", commentList);
			System.out.println(commentList.size());

            request.getRequestDispatcher("/seller/kockCommentList.jsp").forward(request, response);
        } catch (Exception e) {
        	e.printStackTrace(); // 콘솔에 예외 로그 출력
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "서버 오류가 발생했습니다.");
		}
	}

}
