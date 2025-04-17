package controller.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.admin.UserService;
import service.admin.UserServiceImpl;

/**
 * Servlet implementation class UserList
 */
@WebServlet("/userList")
public class UserList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserList() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserService service = new UserServiceImpl();
		try {
			request.setAttribute("userList", service.allUser());
			request.getRequestDispatcher("/admin/userList.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}

}
