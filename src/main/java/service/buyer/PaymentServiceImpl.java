package service.buyer;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ResourceBundle;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import dao.buyer.ShoppingCartDAO;
import dao.buyer.ShoppingCartDAOImpl;

public class PaymentServiceImpl implements PaymentService {

//	private static final String API_KEY = "your-api-key"; // 포트원 API Key
//    private static final String API_SECRET = "your-api-secret"; // 포트원 API Secret
//    ResourceBundle bundle = ResourceBundle.getBundle("config");
//    String apiKey = bundle.getString("portone.apiKey");
//    String apiSecret = bundle.getString("portone.apiSecret");
    
    private static final ResourceBundle bundle = ResourceBundle.getBundle("config");
    private static final String API_KEY = bundle.getString("portone.apiKey");
    private static final String API_SECRET = bundle.getString("portone.apiSecret");


	@Override
	public boolean verifyPayment(String impUid, int amount) throws Exception {
		String token = getAccessToken();

        URL url = new URL("https://api.portone.io/payments/" + impUid);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Authorization", token);

        BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        JsonObject response = JsonParser.parseReader(reader).getAsJsonObject();
        reader.close();

        JsonObject paymentData = response.getAsJsonObject("response");
        int paidAmount = paymentData.get("amount").getAsInt();

        return paidAmount == amount;
	}

	@Override
	public String getAccessToken() throws Exception {
		URL url = new URL("https://api.portone.io/users/getToken");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setDoOutput(true);

        JsonObject body = new JsonObject();
        body.addProperty("apiKey", API_KEY);
        body.addProperty("apiSecret", API_SECRET);

        try (OutputStream os = conn.getOutputStream()) {
            os.write(body.toString().getBytes());
            os.flush();
        }

        BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        JsonObject response = JsonParser.parseReader(reader).getAsJsonObject();
        reader.close();

        return response.getAsJsonObject("response").get("access_token").getAsString();
	}

	@Override
	public void processOrder(String impUid, Long[] cartNums) throws Exception {
		// 주문 생성 및 장바구니 비우기 로직 (DAO 사용)
        OrderDAO orderDAO = new OrderDAOImpl();
        ShoppingCartDAO cartDAO = new ShoppingCartDAOImpl();

        // 주문 생성 (예: 주문 테이블에 insert)
        orderDAO.insertOrder(impUid, cartNums);

        // 장바구니 비우기
        cartDAO.deleteCartItems(cartNums);

	}

}
