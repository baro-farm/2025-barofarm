package controller.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dto.User;
import service.seller.AlarmService;
import service.seller.AlarmServiceImpl;
import service.seller.SellerDetailService;
import service.seller.SellerDetailServiceImpl;
import service.seller.SellerService;
import service.seller.SellerServiceImpl;
import util.PageInfoSoy;
import util.SearchDtoSoy;
import vo.SellerVO;

/**
 * Servlet implementation class FarmRingList
 */
@WebServlet("/farmRingList")
public class FarmRingList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FarmRingList() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		
		HttpSession session = request.getSession(false);
		User sessionUser =null;
		
		if(session != null) {
			sessionUser=(User)session.getAttribute("user");
		}
		if(sessionUser == null) {
			request.getRequestDispatcher("/login").forward(request, response);
			return;
		}
		
		//searchDTO 세팅
		SearchDtoSoy dto = new SearchDtoSoy();
		String pageParam = request.getParameter("page");
		int page = (pageParam != null && !pageParam.isEmpty()) ? Integer.parseInt(pageParam) : 1;
		dto.setPage(page); // ← 무조건 setPage() 사용!
		dto.setKeyword(request.getParameter("keyword"));
		dto.setSearchType(request.getParameter("searchType"));
		dto.setStartDateFrom(request.getParameter("startDateFrom"));
		dto.setStartDateTo(request.getParameter("startDateTo"));
		dto.setUserNum(sessionUser.getUserNum());
		dto.setStatus(request.getParameter("status"));
		
		try {
			/*
			 * SellerDetailService service = new SellerDetailServiceImpl(); List<SellerVO>
			 * farmRingList = service.getAlarmList(dto);
			 * request.setAttribute("farmRingList",farmRingList );
			 * 
			 * int cnt = service.countAlarmList(dto); PageInfoSoy pageInfo = new
			 * PageInfoSoy(dto.getPage(), cnt, 5, dto.getRecordSize());
			 * 
			 * request.setAttribute("pi", pageInfo);
			 */
			request.getRequestDispatcher("/admin/farmRingList.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("err", "콕팜링 구독자 목록 조회에 실패했습니다");
			request.getRequestDispatcher("error.jsp").forward(request, response);
		
		}
	}

}
