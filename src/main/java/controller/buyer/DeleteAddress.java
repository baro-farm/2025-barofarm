package controller.buyer;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.buyer.UserService;
import service.buyer.UserServiceImpl;

/**
 * Servlet implementation class DeleteAddress
 */
@WebServlet("/deleteAddress")
public class DeleteAddress extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteAddress() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		request.setCharacterEncoding("utf-8");
		UserService service = new UserServiceImpl();
		Long addrNum = Long.parseLong(request.getParameter("addrNum"));
		System.out.println(addrNum);
		try {
			service.deleteUserAddress(addrNum);
            response.setContentType("text/plain");
            response.getWriter().write("true");

		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
