package controller.buyer;

import java.io.IOException;
import java.util.ArrayList;
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
import service.buyer.KockFarmService;
import service.buyer.KockFarmServiceImpl;
import service.buyer.UserService;
import service.buyer.UserServiceImpl;
import vo.KockCommentVO;

/**
 * Servlet implementation class KockFarmCommentList
 */
@WebServlet("/kockCommentList")
public class KockFarmCommentList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public KockFarmCommentList() {
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
		KockCommentService service = new KockCommentServiceImpl();
		List<KockCommentVO> kockCommentList = new ArrayList<>();
		
		try {
			int currentPage=1;
			int limit = 15;
			
			String pageParam=request.getParameter("page");
			if (pageParam != null) {
			    try {
			        currentPage = Integer.parseInt(pageParam);
			        if (currentPage < 1) currentPage = 1; // 최소 1페이지 보장
			    } catch (NumberFormatException e) {
			        currentPage = 1;
			    }
			}
			
			Long userNum=userService.selectUserNumByUserId(sessionUser.getUserId());
			
			int totalCommentCount = service.selectCountAllComment(userNum);
			int maxPage=(int)Math.ceil((double)totalCommentCount/limit);
			int offset = (currentPage-1)*limit;
			if (offset < 0) offset = 0;
			
			kockCommentList=service.selectUserMyCommentList(userNum, limit, offset);
			
			request.setAttribute("kockCommentList", kockCommentList);
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("maxPage", maxPage);
			request.setAttribute("totalCommentCount", totalCommentCount);
	        request.getRequestDispatcher("/buyer/kockFarmCommentList.jsp").forward(request, response);

		}catch(Exception e) {
			e.printStackTrace();
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
