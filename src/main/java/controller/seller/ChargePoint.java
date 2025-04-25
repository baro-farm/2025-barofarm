package controller.seller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import dto.User;
import dto.seller.Point;
import dto.seller.UsePoint;
import service.seller.PointService;
import service.seller.PointServiceImpl;
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
			
			//3. 비즈니스 로직 호출(포인트 충전)
			UsePointService service = new UsePointServiceImpl();
			User user = (User) request.getSession().getAttribute("user");
			UsePoint usePoint = new UsePoint(user.getUserNum(), usedPoint, type,null , payInfo, merchantUid, impUid);
			
			service.chargePointByPayment(usePoint);
			
			//4. 성공 응답
			gson.toJson(new JsonResponse(true,"충전 성공"),response.getWriter()); 
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

}
