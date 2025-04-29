package controller.seller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import service.buyer.PackOrderService;
import service.buyer.PackOrderServiceImpl;
import service.buyer.ProdOrderService;
import service.buyer.ProdOrderServiceImpl;

/**
 * Servlet implementation class UpdateProductTrackNum
 */
@WebServlet("/updatePackTrackNum")
public class UpdatePackageTrackNum extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdatePackageTrackNum() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		Long pkOrderNum = Long.parseLong(request.getParameter("pkOrderNum"));
		Integer trackingNum = Integer.parseInt(request.getParameter("trackingNum"));
		System.out.println(pkOrderNum +" "+trackingNum);
		PackOrderService service = new PackOrderServiceImpl();
		try {
			service.updateSellerPackTrackingNum(pkOrderNum, trackingNum);
			response.setContentType("application/json; charset=utf-8");
			JSONObject json = new JSONObject();
			json.put("success", true);
			json.put("pkOrderNum", pkOrderNum);
			json.put("trackingNum", trackingNum);

			response.getWriter().write(json.toString());
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}

}
