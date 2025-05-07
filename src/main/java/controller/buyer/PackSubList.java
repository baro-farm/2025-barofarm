package controller.buyer;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dto.User;
import service.buyer.PackOrderService;
import service.buyer.PackOrderServiceImpl;
import service.buyer.PackSubService;
import service.buyer.PackSubServiceImpl;
import service.buyer.UserService;
import service.buyer.UserServiceImpl;
import vo.PackSubVO;

/**
 * Servlet implementation class PackSubList
 */
@WebServlet("/packSubList")
public class PackSubList extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PackSubList() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession(false);
		User sessionUser = null;

		if (session != null) {
			sessionUser = (User) session.getAttribute("user");
		}
		if (sessionUser == null) {
			request.getRequestDispatcher("/login").forward(request, response);
			return;
		}

		UserService userService = new UserServiceImpl();
		PackSubService service = new PackSubServiceImpl();
		
		List<PackSubVO> packSubList = null;
		
		int page=1;
		int pageSize=5;
		int totalCount=0;
		
		try {
			String startDate = request.getParameter("searchStartDate");
			String endDate = request.getParameter("searchEndDate");
			String deliveryStatus = request.getParameter("deliveryStatus");
			
			Long userNum = userService.selectUserNumByUserId(sessionUser.getUserId());
			System.out.println(userNum);
           
			if (request.getParameter("page") != null) {
                page = Integer.parseInt(request.getParameter("page"));
            }
            totalCount = service.selectPackSubsCount(userNum, startDate, endDate, deliveryStatus);
            
			int totalPages = (int) Math.ceil((double) totalCount / pageSize);
			
			if (page > totalPages && totalPages > 0) {
		        page = totalPages;
		    }
		    if (page <= 0) {
		        page = 1;  // 페이지가 0 이하일 경우
		    }
			
			int offset = (page - 1) * pageSize;
		
			packSubList= service.selectPackSubscribeList(userNum, startDate, endDate, deliveryStatus, offset, pageSize);
			System.out.println(packSubList);
			
			// 페이지 그룹 계산
            int pageGroupSize = 5;
            int currentGroup = (int) Math.ceil((double) page / pageGroupSize);
            int groupStartPage = (currentGroup - 1) * pageGroupSize + 1;
            int groupEndPage = Math.min(groupStartPage + pageGroupSize - 1, totalPages);
			
            request.setAttribute("packSubList", packSubList);
            request.setAttribute("currentPage", page);
            request.setAttribute("totalPages", totalPages);
            request.setAttribute("groupStartPage", groupStartPage);
            request.setAttribute("groupEndPage", groupEndPage);
            request.setAttribute("pageGroupSize", pageGroupSize);

            
            // 검색 조건 유지용
            request.setAttribute("searchStartDate", startDate);
            request.setAttribute("searchEndDate", endDate);
            request.setAttribute("deliveryStatus", deliveryStatus);

            
            request.getRequestDispatcher("/buyer/packSubList.jsp").forward(request, response);
			
		}catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
