package controller.seller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import service.buyer.ProdOrderService;
import service.buyer.ProdOrderServiceImpl;

/**
 * Servlet implementation class UpdateProductTrackNum
 */
@WebServlet("/updateProdTrackNum")
public class UpdateProductTrackNum extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateProductTrackNum() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		Long pdOrderNum = Long.parseLong(request.getParameter("pdOrderNum"));
		String deleveryStatus = request.getParameter("deleveryStatus");
		System.out.println(pdOrderNum +" "+deleveryStatus);
		ProdOrderService service = new ProdOrderServiceImpl();
		try {
			service.updateSellerProdTrackingNum(pdOrderNum, deleveryStatus);
			response.setContentType("application/json; charset=utf-8");
			JSONObject json = new JSONObject();
			json.put("success", true);
			json.put("pdOrderNum", pdOrderNum);
			json.put("deleveryStatus", deleveryStatus);

			response.getWriter().write(json.toString());
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}

}
