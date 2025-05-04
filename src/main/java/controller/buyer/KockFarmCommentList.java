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
		
		int page = 1;
		int pageSize = 10; // ✅ 한 페이지 10개
		int totalCount = 0;
		
		try {

			Long userNum=userService.selectUserNumByUserId(sessionUser.getUserId());
			totalCount = service.selectCountAllComment(userNum);
			
			if(request.getParameter("page")!= null) {
				page=Integer.parseInt(request.getParameter("page"));
			}
			
			int totalPages=(int)Math.ceil((double)totalCount/pageSize);
			
			if (page > totalPages && totalPages > 0) {
		        page = totalPages;
		    }
		    if (page <= 0) {
		        page = 1;  // 페이지가 0 이하일 경우
		    }

		    int offset = (page - 1) * pageSize;
			
						
			kockCommentList=service.selectUserMyCommentList(userNum, pageSize, offset);
			
			int pageGroupSize = 5;
			int currentGroup = (int) Math.ceil((double) page / pageGroupSize);
			int groupStartPage = (currentGroup - 1) * pageGroupSize + 1;
			int groupEndPage = Math.min(groupStartPage + pageGroupSize - 1, totalPages);
		
			request.setAttribute("kockCommentList", kockCommentList);
			request.setAttribute("groupStartPage", groupStartPage);
			request.setAttribute("groupEndPage", groupEndPage);
			request.setAttribute("totalPages", totalPages);
			request.setAttribute("currentPage", page);
			request.setAttribute("currentGroup", currentGroup);
			request.setAttribute("pageGroupSize", pageGroupSize);

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
