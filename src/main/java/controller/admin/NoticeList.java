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
		NoticeService noticeService = new NoticeServiceImpl();

		try {
			request.setCharacterEncoding("UTF-8");

			// 1. 기본값 설정
			int page = 1;
			int pageSize = 10;
			if (request.getParameter("page") != null) {
				page = Integer.parseInt(request.getParameter("page"));
			}
			int offset = (page - 1) * pageSize;

			// 2. 공지 목록 조회
			List<Notice> noticeList = noticeService.allNotice(offset, pageSize);

			// 3. 전체 공지 수 조회
			int totalCount = noticeService.getNoticeCountAll();
			int totalPages = (int) Math.ceil((double) totalCount / pageSize);
			if (page <= 0) page = 1;
			if (page > totalPages && totalPages > 0) page = totalPages;

			// 4. 페이지 그룹 계산
			int pageGroupSize = 5;
			int currentGroup = (int) Math.ceil((double) page / pageGroupSize);
			int groupStartPage = (currentGroup - 1) * pageGroupSize + 1;
			int groupEndPage = Math.min(groupStartPage + pageGroupSize - 1, totalPages);
			int jumpPrevPage = Math.max(groupStartPage - pageGroupSize, 1);
			int jumpNextPage = Math.min(groupStartPage + pageGroupSize, totalPages);

			// 5. request에 담기
			request.setAttribute("noticeList", noticeList);
			request.setAttribute("currentPage", page);
			request.setAttribute("totalPages", totalPages);
			request.setAttribute("groupStartPage", groupStartPage);
			request.setAttribute("groupEndPage", groupEndPage);
			request.setAttribute("pageGroupSize", pageGroupSize);
			request.setAttribute("jumpPrevPage", jumpPrevPage);
			request.setAttribute("jumpNextPage", jumpNextPage);

			// 6. 포워딩
			request.getRequestDispatcher("/admin/noticeList.jsp").forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", "공지사항 목록 조회 중 오류가 발생했습니다.");
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
	}
}
