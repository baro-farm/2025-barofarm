package controller.admin;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import service.admin.BannerService;
import service.admin.BannerServiceImpl;
import service.seller.AdsService;
import service.seller.AdsServiceImpl;

/**
 * Servlet implementation class UpdateAdStatus
 */
@WebServlet("/updateAdStatus")
public class UpdateAdStatus extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateAdStatus() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    request.setCharacterEncoding("UTF-8");
	    response.setContentType("application/json; charset=UTF-8");
	    
	    BufferedReader reader = request.getReader();
	    Gson gson = new Gson();
		Map<String, Object> data = gson.fromJson(reader, Map.class);
		Long adsNum = Long.parseLong(String.valueOf(data.get("adsNum")));
	    String status = (String) data.get("status");
	    boolean success = false;

	    try {
			AdsService service = new AdsServiceImpl();
			success = service.updateAdsStatusByAdmin(adsNum, status);
		} catch (Exception e) {
		      e.printStackTrace();
		}
		
	    JsonObject json = new JsonObject();
	    json.addProperty("success", success);
	    response.getWriter().print(json.toString());
	    response.getWriter().flush();
	}

}
