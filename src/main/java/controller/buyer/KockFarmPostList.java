package controller.buyer;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dto.User;
import service.buyer.KockFarmService;
import service.buyer.KockFarmServiceImpl;
import service.buyer.UserService;
import service.buyer.UserServiceImpl;
import vo.KockFarmVO;

/**
 * Servlet implementation class KockFarmPostList
 */
@WebServlet("/kockFarmPostList")
public class KockFarmPostList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public KockFarmPostList() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		HttpSession session = request.getSession(false);
		User sessionUser =null;
		
		if(session != null) {
			sessionUser=(User)session.getAttribute("user");
		}
		if (sessionUser == null) {
			response.sendRedirect(request.getContextPath() + "/login.jsp");
			return;
		}
		
		UserService userService = new UserServiceImpl();
		KockFarmService service = new KockFarmServiceImpl();
		
		List<KockFarmVO> kockPostList = new ArrayList<>();
		int page = 1;
		int pageSize = 10; // ✅ 한 페이지 10개
		int totalCount = 0;
		
		try {

			String periodParam = request.getParameter("period");
			String matchedParam = request.getParameter("matched");

			// 기본값 설정
			int months = 6;
			if (periodParam != null) {
				try {
					months = Integer.parseInt(periodParam);
				} catch (NumberFormatException e) {
					// 잘못된 값이면 6개월 유지
				}
			}

			Boolean isMatched = null; // 전체: null, 체결: true, 미체결: false
			if ("1".equals(matchedParam)) {
				isMatched = true;
			} else if ("0".equals(matchedParam)) {
				isMatched = false;
			}

			// 날짜 계산 (예: 오늘 - 6개월)
			LocalDate startDate = LocalDate.now().minusMonths(months);
			
			

			
			Long userNum=userService.selectUserNumByUserId(sessionUser.getUserId());
			System.out.println(userNum);
			System.out.println(startDate);
			System.out.println(isMatched);
			
		    totalCount = service.selectCountKockPost(userNum, startDate, isMatched);
			System.out.println(totalCount);
		    
		    if(request.getParameter("page")!= null) {
				page=Integer.parseInt(request.getParameter("page"));
			}
			
		    
		    int totalPages = (int) Math.ceil((double) totalCount / pageSize);			
			

			totalPages=(int)Math.ceil((double)totalCount/pageSize);
			
			if (page > totalPages && totalPages > 0) {
		        page = totalPages;
		    }
		    if (page <= 0) {
		        page = 1;  // 페이지가 0 이하일 경우
		    }

		    int offset = (page - 1) * pageSize;
			
			
			kockPostList= service.selectMyPostList(userNum, startDate, isMatched, pageSize, offset);
			
			int pageGroupSize = 5;
			int groupStartPage = ((page - 1) / pageGroupSize) * pageGroupSize + 1;
			int groupEndPage = Math.min(groupStartPage + pageGroupSize - 1, totalPages);

			System.out.println(kockPostList);
			request.setAttribute("kockPostList", kockPostList);
			
			request.setAttribute("currentPage", page);
			request.setAttribute("totalPages", totalPages);
			request.setAttribute("pageGroupSize", pageGroupSize);
			request.setAttribute("groupStartPage", groupStartPage);
			request.setAttribute("groupEndPage", groupEndPage);
			request.setAttribute("totalCount", totalCount);
			
			request.setAttribute("period", periodParam); // 선택 유지용
			request.setAttribute("matched", matchedParam); // 선택 유지용
			request.getRequestDispatcher("/buyer/kockFarmPostList.jsp").forward(request, response);

		}catch(Exception e ) {
			if(session == null) {
				request.getRequestDispatcher("/login.jsp").forward(request, response);

			}
			e.printStackTrace();

		}
		
		
		
	}

}
