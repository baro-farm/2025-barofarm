
package controller.buyer;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dto.User;
import dto.buyer.Address;
import service.buyer.UserService;
import service.buyer.UserServiceImpl;

/**
 * Servlet implementation class AddressList
 */
@WebServlet("/addressList")
public class AddressList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddressList() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession(false);
		User sessionUser =null;
		
		if(session != null) {
			sessionUser=(User)session.getAttribute("user");
		}
		if(sessionUser == null) {
			request.getRequestDispatcher("/login").forward(request, response);
			return;
		}
		
		UserService service = new UserServiceImpl();
		List<Address> addressList = null;
		try {
			addressList = service.selectUserAddressList(sessionUser.getUserId());
			System.out.println(addressList);
			request.setAttribute("addressList", addressList);
			request.getRequestDispatcher("/buyer/addressList.jsp").forward(request, response);

			
		}catch(Exception e) {
			e.printStackTrace();
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
