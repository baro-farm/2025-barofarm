package controller.common;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.UserService;
import service.UserServiceImpl;

/**
 * Servlet implementation class DeleteAdminQA
 */
@WebServlet("/deleteAdminQA")
public class DeleteAdminQA extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteAdminQA() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Long questionNum = Long.parseLong(request.getParameter("questionNum"));
        System.out.println(questionNum);
        
		UserService service = new UserServiceImpl();
		try {
            service.deleteAdminQA(questionNum);

            response.sendRedirect("adminQAList");
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("err", "문의글 삭제 실패");
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
