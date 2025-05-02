package controller.buyer;

import java.io.BufferedReader;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import service.buyer.ProdOrderService;
import service.buyer.ProdOrderServiceImpl;
import vo.ProdCancelVO;

/**
 * Servlet implementation class ProductCancle
 */
@WebServlet("/cancelProdOrder")
public class ProductCancle extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductCancle() {
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
		    response.setContentType("application/json;charset=utf-8");

		    BufferedReader reader = request.getReader();
		    JsonObject body = JsonParser.parseReader(reader).getAsJsonObject();

		    Long pdOrderNum = body.get("pdOrderNum").getAsLong();
		    String reason = body.get("cancelReason").getAsString();
		    String reasonDetail = body.get("cancelReasonDetail").getAsString();
		    
		    ProdOrderService service = new ProdOrderServiceImpl();
		    
		    ProdCancelVO cancel = new ProdCancelVO();
		    try {
		    	service.cancelProdOrder(pdOrderNum,reason,reasonDetail);
		    	JsonObject result = new JsonObject();
		    	result.addProperty("result", "success");
	            response.getWriter().write(result.toString());
		    	
		    }catch (Exception e) {
				// TODO: handle exception
			}
		    cancel.setOrderItem(pdOrderNum);
	
	}

}
