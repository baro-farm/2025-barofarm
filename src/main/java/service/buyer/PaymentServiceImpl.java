package service.buyer;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import dao.buyer.PaymentDAO;
import dao.buyer.PaymentDAOImpl;

public class PaymentServiceImpl implements PaymentService {

//	private static final String API_KEY = "your-api-key"; // 포트원 API Key
//    private static final String API_SECRET = "your-api-secret"; // 포트원 API Secret
//    ResourceBundle bundle = ResourceBundle.getBundle("config");
//    String apiKey = bundle.getString("portone.apiKey");
//    String apiSecret = bundle.getString("portone.apiSecret");
    
//    private static final ResourceBundle bundle = ResourceBundle.getBundle("config");
//    private static final String API_KEY = bundle.getString("portone.apiKey");
//    private static final String API_SECRET = bundle.getString("portone.apiSecret");

	private final String apiKey;
    private final String apiSecret;
    private PaymentDAO paymentDao;
    
    public PaymentServiceImpl(String apiKey, String apiSecret) {
        this.apiKey = apiKey;
        this.apiSecret = apiSecret;
        paymentDao = new PaymentDAOImpl();
    }

    public Map<String, String> verifyPayment(String impUid, int amount) throws Exception {
        String token = getAccessToken();
        URL url = new URL("https://api.iamport.kr/payments/" + impUid);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Authorization", "Bearer " + token);

        BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        JsonObject response = JsonParser.parseReader(reader).getAsJsonObject();
        reader.close();

        JsonObject paymentData = response.getAsJsonObject("response");
        if (paymentData == null) {
            throw new Exception("결제 정보 조회 실패!");
        }

        int paidAmount = paymentData.get("amount").getAsInt();
        String pgTid = paymentData.get("pg_tid").getAsString();          // ✅ transactionId
        String merchantUid = paymentData.get("merchant_uid").getAsString(); // ✅ merchantUid

        if (paidAmount != amount) {
            throw new Exception("결제 금액 불일치!");
        }

        Map<String, String> result = new HashMap<>();
        result.put("isVerified", String.valueOf(paidAmount == amount));
        result.put("pg_tid", pgTid);
        result.put("merchant_uid", merchantUid);
        return result;
    }


	@Override
	public String getAccessToken() throws Exception {
		URL url = new URL("https://api.iamport.kr/users/getToken");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setDoOutput(true);

        JsonObject body = new JsonObject();
//        body.addProperty("imp_key", apiKey);      // ✅ imp_key
//        body.addProperty("imp_secret", apiSecret); // ✅ imp_secret
        body.addProperty("imp_key", apiKey.trim());
        body.addProperty("imp_secret", apiSecret.trim());

        System.out.println("Sending to PortOne:");
        System.out.println("imp_key: " + apiKey);
        System.out.println("imp_secret: " + apiSecret);

        
        try (OutputStream os = conn.getOutputStream()) {
            os.write(body.toString().getBytes());
            os.flush();
        }

        if (conn.getResponseCode() != 200) {
            BufferedReader errorReader = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
            String line;
            while ((line = errorReader.readLine()) != null) {
                System.out.println(line);  // 에러 메시지 출력
            }
            errorReader.close();
        }

        
        BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        JsonObject response = JsonParser.parseReader(reader).getAsJsonObject();
        reader.close();
        
        return response.getAsJsonObject("response").get("access_token").getAsString();
	}

	@Override
    public void insertPayment(SqlSession sqlSession, Long pdOrderNum, Long pkOrderNum, int price, String transactionId, String pay, String impUid, String merchantUid) throws Exception {
        Map<String, Object> param = new HashMap<>();
        param.put("pdOrderNum", pdOrderNum);
        param.put("pkOrderNum", pkOrderNum);
        param.put("price", price);
        param.put("transactionId", transactionId);
        param.put("pay", pay);
        param.put("impUid", impUid);
        param.put("merchantUid", merchantUid);

        paymentDao.insertPayment(sqlSession, param);
    }
}
