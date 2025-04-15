package controller.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.admin.NoticeService;
import service.admin.NoticeServiceImpl;

/**
 * Servlet implementation class DeleteNotice
 */
@WebServlet("/deleteNotice")
public class DeleteNotice extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteNotice() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		Integer noticeNum = Integer.parseInt(request.getParameter("noticeNum"));
		
		NoticeService service = new NoticeServiceImpl();
		try {
			service.deleteNotice(noticeNum);
			System.out.println("게시글 삭제됨");
			
			response.setContentType("text/plain; charset=UTF-8");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(noticeNum + "번 게시글을 삭제했습니다.");
		} catch (Exception e) {
			e.printStackTrace();
			response.setContentType("text/plain; charset=UTF-8");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write("삭제 중 오류가 발생했습니다.");
		}
	}

}
