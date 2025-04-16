package controller.admin;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.admin.CustomerService;
import service.admin.CSService;
import service.admin.CSServiceImpl;

/**
 * Servlet implementation class CustomerServiceList
 */
@WebServlet("/customerServiceList")
public class CustomerServiceList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CustomerServiceList() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CSService service= new CSServiceImpl();
		try {
			request.setAttribute("customerServiceList", service.allCustomerService());
			request.getRequestDispatcher("/admin/customerServiceList.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
