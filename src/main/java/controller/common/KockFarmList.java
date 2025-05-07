package controller.common;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dto.User;
import service.buyer.KockFarmService;
import service.buyer.KockFarmServiceImpl;
import util.PageInfoSoy;
import util.SearchDtoSoy;

/**
 * Servlet implementation class KockFarmList
 */
@WebServlet("/kockFarmList")
public class KockFarmList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public KockFarmList() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		
		KockFarmService service = new KockFarmServiceImpl();
		
		//searchDTO 세팅
		SearchDtoSoy dto = new SearchDtoSoy();
		String pageParam = request.getParameter("page");
		int page = (pageParam != null && !pageParam.isEmpty()) ? Integer.parseInt(pageParam) : 1;
		dto.setPage(page); // ← 무조건 setPage() 사용!
		dto.setKeyword(request.getParameter("keyword"));
		dto.setSearchType(request.getParameter("searchType"));
		dto.setStartDateFrom(request.getParameter("startDateFrom"));
		dto.setStartDateTo(request.getParameter("startDateTo"));
		dto.setStatus(request.getParameter("status"));
		
		try {
			request.setAttribute("kocks", service.selectKFBySearchDto(dto));
			
			int cnt = service.countKFBySearchDto(dto);
			PageInfoSoy pageInfo = new PageInfoSoy(dto.getPage(), cnt, 5, dto.getRecordSize());
			
			request.setAttribute("pi", pageInfo);
			if (user!=null) request.setAttribute("isSeller", user.getIsSeller());
			request.getRequestDispatcher("/common/kockFarmList.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("err", "콕팜 목록 조회에 실패했습니다");
			request.getRequestDispatcher("error.jsp").forward(request, response);
		
		}
	}

}
