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
import service.buyer.PackOrderService;
import service.buyer.PackOrderServiceImpl;
import vo.PackOrderVO;

/**
 * Servlet implementation class ProdOrderList
 */
@WebServlet("/packOrderList")
public class PackageOrderList extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PackageOrderList() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession(false);
		User user =null;

		if (session != null) {
			user = (User) session.getAttribute("user");
		}
		
		
		PackOrderService service = new PackOrderServiceImpl();
		List<PackOrderVO> packOrderList = null;
		try {
			
			packOrderList = service.selectUserPackOrderList(user.getUserId());
			request.setAttribute("packOrderList", packOrderList);
			request.getRequestDispatcher("/buyer/packageOrderList.jsp").forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
			request.getRequestDispatcher("/login.jsp").forward(request, response);

		}
	}

}
