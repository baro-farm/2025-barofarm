package controller.admin;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.admin.Notice;
import service.admin.NoticeService;
import service.admin.NoticeServiceImpl;

/**
 * Servlet implementation class UpdateNotice
 */
@WebServlet("/updateNotice")
public class UpdateNotice extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateNotice() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		Integer noticeNum = Integer.parseInt(request.getParameter("noticeNum"));
		NoticeService service = new NoticeServiceImpl();
		
		try {
			Notice notice = service.selectNotice(noticeNum);
			request.setAttribute("notice", notice);
			request.getRequestDispatcher("/admin/updateNotice.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		Integer noticeNum = Integer.parseInt(request.getParameter("noticeNum"));
		String title = request.getParameter("title");
		String content = request.getParameter("notice_content");
		String fixedParam = request.getParameter("fixed");
		Integer fixed = fixedParam != null && fixedParam.equals("on") ? 1 : 0;
		Date currentDate = new Date(System.currentTimeMillis());
		
		// user 임시값
		Notice notice = new Notice(noticeNum, 1, title, content, currentDate, fixed);
		NoticeService service = new NoticeServiceImpl();
		try {
			service.updateNotice(notice);
			response.sendRedirect("noticeList");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}
