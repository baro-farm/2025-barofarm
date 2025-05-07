package controller.common;

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
 * Servlet implementation class UserNoticeList
 */
@WebServlet("/userNoticeList")
public class UserNoticeList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserNoticeList() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String pageStr = request.getParameter("page");
		Integer page = null;
		if(pageStr==null) {
			page = 1;
		} else {
			page = Integer.parseInt(pageStr);
		}
		
		NoticeService service = new NoticeServiceImpl();
		try {
			List<Notice> fnoticeList = service.getFixNoticeList();
			Integer fixCount = fnoticeList.size();
			Integer pageSize = 10;
			Integer normalPageSize = pageSize - fixCount;
			
			Integer totalCount = service.getNoticeCount();
			PageInfo pageInfo = new PageInfo(page, normalPageSize, totalCount);
			
			
			List<Notice> noticeList = service.NoticeListByPage(pageInfo);

			request.setAttribute("pageInfo", pageInfo);
			request.setAttribute("fixList", fnoticeList);
			request.setAttribute("noticeList", noticeList);
			
			request.getRequestDispatcher("/common/userNoticeList.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("err", "게시판 목록조회를 실패했습니다.");
		}
	}

}
