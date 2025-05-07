package controller.common;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.User;
import dto.admin.AdminQuestion;
import service.UserService;
import service.UserServiceImpl;

/**
 * Servlet implementation class InsertAdminQA
 */
@WebServlet("/insertAdminQA")
public class InsertAdminQA extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertAdminQA() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/common/insertAdminQ.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		User user = (User) request.getSession().getAttribute("user");
		Long userNum = user.getUserNum();
		
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String type = request.getParameter("type");
		
		AdminQuestion adminQA = new AdminQuestion(userNum,title,content,type);
		
		try {
			 UserService service = new UserServiceImpl();
			 service.writeAdminQA(adminQA);
			 
			 response.sendRedirect("adminQAList");
		}catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("err", "게시글 작성에 실패했습니다.");
		}
		
	}

}
