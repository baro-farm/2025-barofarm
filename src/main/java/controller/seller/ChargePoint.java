package controller.seller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import dto.User;
import dto.seller.UsePoint;
import service.seller.UsePointService;
import service.seller.UsePointServiceImpl;
import vo.ChargeRequest;

/**
 * Servlet implementation class ChargePoint
 */
@WebServlet("/chargePoint")
public class ChargePoint extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChargePoint() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		Gson gson = new Gson(); //json파싱을 위한 gson 객체 생성
		response.setContentType("application/json; charset=UTF-8"); //응답 컨텐츠 타입은 json으로 => 클라이언트가 json으로 받게됨!
		
		try {
			//1. 클라이언트가 보낸 json데이터를 ChargeRequest 객체로 파싱한당.
			//request.getReader() => 클라이언트가 보낸 JSON 본문을 읽어옴
			//gson.fromJson() => 읽어온 JSON을 cr 객체로 변환
			ChargeRequest cr = gson.fromJson(request.getReader(), ChargeRequest.class);

			//2. 파싱한 데이터에서 필요한 값 꺼내기
			String impUid = cr.getImp_uid();
			String merchantUid = cr.getMerchant_uid();
			int usedPoint = cr.getUsedPoint();
			String type = cr.getType();
			String payInfo = cr.getPayInfo();
			System.out.println(impUid);
			//3. 포트원 api키, 시크릿 키를 가지구 온당.
			Properties props = new Properties();
			try(InputStream input = getServletContext().getResourceAsStream("/WEB-INF/config/config.properties")){
				props.load(input);	
			}
			String apiKey = props.getProperty("portone.apiKey.soy");
			String apiSecret = props.getProperty("portone.apiSecret.soy");
			
			//4. 포트원 액세스 토큰 발급 => 내가 보낸 2개의 키가 정확히 맞을 때 액세스 토큰이 발급된다.
			// => 내가 아임포트에 등록된 가맹점인지!!
			//결제 내역 조회 (imp_uid로)	을 조회해서 결제금액 검증을 할 때 액세스 토큰이 필요하다.
			String accessToken = getAccessToken(apiKey,apiSecret,gson);
			
			//5. imp_uid로 결제 정보 조회
			JsonObject paymentInfo = getPaymentInfo(accessToken,impUid,gson);
			int amount = paymentInfo.get("amount").getAsInt();
			String status = paymentInfo.get("status").getAsString();

			//6. 비즈니스 로직 호출(포인트 충전) + 금액,상태 검증
            if (amount == usedPoint && "paid".equals(status)) {
            	//값이 같으면 검증 성공
    			UsePointService service = new UsePointServiceImpl();
    			User user = (User) request.getSession().getAttribute("user");
    			UsePoint usePoint = new UsePoint(user.getUserNum(), usedPoint, type,null , payInfo, merchantUid, impUid);
    			
    			service.chargePointByPayment(usePoint);
    			
    			//4. 성공 응답
    			gson.toJson(new JsonResponse(true,"충전 성공"),response.getWriter()); 
            } else {
            	 String failMsg = (amount != usedPoint) ? "결제 금액 불일치" : "결제 상태가 완료되지 않았습니다";
                 gson.toJson(new JsonResponse(false, failMsg), response.getWriter());
            }

		} catch (Exception e) {
			e.printStackTrace();
			gson.toJson(new JsonResponse(false,"충전 실패"),response.getWriter()); 
		}
	}
	
	//서블릿에서 클라이언트로 json응답을 보낼 때의 데이터 구조를 정의한것
	/*
 	{
	  "success": true,
	  "message": "충전 성공"
	}
	*/
	private class JsonResponse {
		private boolean success;
		private String message;
		public JsonResponse(boolean success, String message) {
			this.success = success;
			this.message = message;
		}
	}
	
	//토큰 발급 메서드
	//아임포트 서버에 키와시크릿을 post로 보내서 액세스 토큰을 발급받는 과정~
	private String getAccessToken(String apiKey, String apiSecret, Gson gson) throws Exception {
		URL url = new URL("https://api.iamport.kr/users/getToken");
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("POST");
		conn.setRequestProperty("Content-Type", "application/json");
		conn.setDoOutput(true); // OutputStream으로 데이터를 보내겠당 (바디를 작성할거임)
		
		//post 요청으로 보낸 json 데이터 생성
		String json="{\"imp_key\":\"" + apiKey + "\",\"imp_secret\":\"" + apiSecret + "\"}";
		try (OutputStream os = conn.getOutputStream()) {
			os.write(json.getBytes("UTF-8"));
		} // UTF-8로 인코딩해서,OutputStream으로 JSON 데이터 전송
		
		
		try (BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"))) {
			//서버 응답(InputStream) 받기 => 응답 json을 Gson으로 파싱한 후에 JsonObject로 변환
			JsonObject res = gson.fromJson(br, JsonObject.class);
			//액세스 토큰 꺼내기
			return res.getAsJsonObject("response").get("access_token").getAsString();
		}
		/*
		 * {
			  "response": {
			    "access_token": "발급된 토큰"
			  }
			}
		 */
	}
	
	//결제 조회 메서드 (amount + status)
	private  JsonObject getPaymentInfo(String accessToken, String impUid, Gson gson) throws IOException {
		URL url = new URL("https://api.iamport.kr/payments/" + impUid);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Authorization", accessToken);
		
        try (BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"))) {
            JsonObject res = gson.fromJson(br, JsonObject.class);
            return res.getAsJsonObject("response");
        }
	}
}
