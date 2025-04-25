package controller.buyer;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import service.buyer.PaymentService;
import service.buyer.PaymentServiceImpl;

/**
 * Servlet implementation class PaymentComplete
 */
@WebServlet("/paymentComplete")
public class PaymentComplete extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PaymentComplete() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");

        BufferedReader reader = request.getReader();
        JsonObject jsonRequest = JsonParser.parseReader(reader).getAsJsonObject();

        String impUid = jsonRequest.get("impUid").getAsString();
        int amount = jsonRequest.get("amount").getAsInt();
        Gson gson = new Gson();
        Long[] cartNums = gson.fromJson(jsonRequest.get("cartNums"), Long[].class);

        PaymentService paymentService = new PaymentServiceImpl();
        boolean isVerified = false;

        try {
            // 1. 포트원 서버에 결제 검증 요청 (paymentService에서 처리)
            isVerified = paymentService.verifyPayment(impUid, amount);

            if (isVerified) {
                // 2. 주문 생성 및 장바구니 비우기 처리
//                paymentService.processOrder(impUid, cartNums);

                JsonObject jsonResponse = new JsonObject();
                jsonResponse.addProperty("success", true);
                response.getWriter().write(jsonResponse.toString());
            } else {
                JsonObject jsonResponse = new JsonObject();
                jsonResponse.addProperty("success", false);
                response.getWriter().write(jsonResponse.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
            JsonObject jsonResponse = new JsonObject();
            jsonResponse.addProperty("success", false);
            response.getWriter().write(jsonResponse.toString());
        }
	}

}
