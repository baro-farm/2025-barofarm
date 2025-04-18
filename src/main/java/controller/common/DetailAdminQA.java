package controller.common;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.UserService;
import service.UserServiceImpl;
import vo.AdminQuestionVO;

/**
 * Servlet implementation class DetailAdminQA
 */
@WebServlet("/detailAdminQA")
public class DetailAdminQA extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DetailAdminQA() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		Long qNum = Long.parseLong(request.getParameter("questionNum"));
		
		UserService service = new UserServiceImpl();
		try {
			 AdminQuestionVO adminq = service.detailAdminQA(qNum);
			
			request.setAttribute("adminq", adminq);
			request.getRequestDispatcher("/common/detailAdminQA.jsp").forward(request, response);
		}catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("err", "게시글 조회에 실패했습니다.");
		}
	}

}
