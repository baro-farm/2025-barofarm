package controller.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.User;
import dto.admin.Notice;
import service.admin.NoticeService;
import service.admin.NoticeServiceImpl;

/**
 * Servlet implementation class InsertNotice
 */
@WebServlet("/insertNotice")
public class InsertNotice extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public InsertNotice() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/admin/insertNotice.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		// 로그인한 사용자 정보 세션에서 가져오기
	    User user = (User) request.getSession().getAttribute("user");
	    if (user == null) {
	        // 로그인 안 돼있으면 로그인 페이지로 보내기
	    	// 이상하긴 한데 바꿀 예정
	        response.sendRedirect("login.jsp");
	        return;
	    }
		
	    Long userNum = user.getUserNum();
		String title = request.getParameter("title");
		String content = request.getParameter("notice_content");
		String fixedParam = request.getParameter("fixed");
		Integer fixed = fixedParam != null && fixedParam.equals("on") ? 1 : 0;


		Notice notice = new Notice(userNum, title, content, fixed);

		NoticeService service = new NoticeServiceImpl();
		try {
			service.writeNotice(notice);
			response.sendRedirect("noticeList");
		} catch(Exception e) {
			e.printStackTrace();
		}

	}

}
