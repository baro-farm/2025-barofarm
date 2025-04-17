package controller.common;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.admin.Notice;
import service.admin.NoticeService;
import service.admin.NoticeServiceImpl;

/**
 * Servlet implementation class DetailNotice
 */
@WebServlet("/detailNotice")
public class DetailNotice extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DetailNotice() {
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
			request.getRequestDispatcher("/detailNotice.jsp").forward(request, response);
		}catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("err", "게시글 조회에 실패했습니다.");
		}
	}

}
