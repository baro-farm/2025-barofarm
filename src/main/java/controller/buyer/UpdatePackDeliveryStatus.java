package controller.buyer;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import service.buyer.PackOrderService;
import service.buyer.PackOrderServiceImpl;

/**
 * Servlet implementation class ConfirmOrder
 */
@WebServlet("/updatePkDeliveryStatus")
public class UpdatePackDeliveryStatus extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdatePackDeliveryStatus() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		Long pkOrderNum = Long.parseLong(request.getParameter("pkOrderNum"));
		String deleveryStatus = request.getParameter("deleveryStatus");
		System.out.println(pkOrderNum +" "+deleveryStatus);
		PackOrderService service = new PackOrderServiceImpl();
		try {
			service.updateUserPackDeliveryStatus(pkOrderNum, deleveryStatus);
			response.setContentType("application/json; charset=utf-8");
			JSONObject json = new JSONObject();
			json.put("success", true);
			json.put("pkOrderNum", pkOrderNum);
			json.put("status", deleveryStatus);

			response.getWriter().write(json.toString());
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		
	}

}
