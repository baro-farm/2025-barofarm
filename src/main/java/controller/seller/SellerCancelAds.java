package controller.seller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import dto.User;
import service.seller.AdsService;
import service.seller.AdsServiceImpl;
/**
 * Servlet implementation class SellerCancelAds
 */
@WebServlet("/sellerCancelAds")
public class SellerCancelAds extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SellerCancelAds() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Gson gson = new Gson();
		
		try {
        	 // 1. 요청 JSON 파싱 (최소한의 코드)
             JsonObject jsonObj = gson.fromJson(request.getReader(), JsonObject.class);
             Long adsNum = jsonObj.get("adsNum").getAsLong();
            
             AdsService service = new AdsServiceImpl();
             boolean result = service.cancelAdsAndRefund(adsNum, "취소완료");
		} catch (Exception e) {
			// TODO: handle exception
		}
        
		
	}

}
