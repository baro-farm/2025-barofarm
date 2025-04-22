package controller.common;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.UserService;
import service.UserServiceImpl;
import service.admin.NoticeService;
import service.admin.NoticeServiceImpl;
import util.PageInfo;
import vo.AdminQuestionVO;

/**
 * Servlet implementation class AdminQAList
 */
@WebServlet("/adminQAList")
public class AdminQAList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminQAList() {
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
		
		PageInfo pageInfo = new PageInfo(page);
		UserService service = new UserServiceImpl();
		
		try {
			List<AdminQuestionVO> adminQAList = service.adminQAListByPage(pageInfo);
			request.setAttribute("pageInfo", pageInfo);
			request.setAttribute("adminQAList", adminQAList);
			
			request.getRequestDispatcher("/common/AdminQAList.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("err", "게시판 목록조회를 실패했습니다.");
		}
	}

}
