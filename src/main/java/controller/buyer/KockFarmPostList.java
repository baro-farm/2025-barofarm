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
		
		try {
			Long userNum=userService.selectUserNumByUserId(sessionUser.getUserId());

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
			
			
			System.out.println(userNum);
			System.out.println(startDate);
			System.out.println(isMatched);

			
			kockPostList= service.selectMyPostList(userNum,startDate,isMatched);
			
			System.out.println(kockPostList);
			request.setAttribute("kockPostList", kockPostList);
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
