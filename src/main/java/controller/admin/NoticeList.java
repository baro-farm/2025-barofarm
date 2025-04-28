package controller.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.admin.Notice;
import service.admin.NoticeService;
import service.admin.NoticeServiceImpl;
import util.PageInfo;

/**
 * Servlet implementation class NoticeList
 */
@WebServlet("/noticeList")
public class NoticeList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeList() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		NoticeService service = new NoticeServiceImpl();
		List<Notice> noticeList = null;
		try {
			int currentPage = request.getParameter("page") != null ? Integer.parseInt(request.getParameter("page")) : 1;
			int itemsPerPage = 10;
			int offset = (currentPage - 1) * itemsPerPage;

			// 총 게시글 수 구하기
			Integer totalNotices = service.getNoticeCount();  // 이 메서드 추가 필요!

			// 공지사항 목록 가져오기 (LIMIT, OFFSET 적용)
			PageInfo pageInfo = new PageInfo(currentPage, itemsPerPage);
			try {
				noticeList = service.NoticeListByPage(pageInfo);  // 이 메서드도 LIMIT/OFFSET 적용!
 			} catch (Exception e) {
 				e.printStackTrace();
 			}

			request.setAttribute("noticeList", noticeList);
			request.setAttribute("totalNotices", totalNotices);
			request.setAttribute("currentPage", currentPage);

			request.getRequestDispatcher("/admin/noticeList.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		// TODO Auto-generated method stub
//		doGet(request, response);
//	}

}
