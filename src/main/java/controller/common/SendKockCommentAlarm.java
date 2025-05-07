package controller.common;

import java.io.BufferedReader;
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
import service.seller.AlarmService;
import service.seller.AlarmServiceImpl;

/**
 * Servlet implementation class SendKockFarmAlarm
 */
@WebServlet("/sendKockCommentAlarm")
public class SendKockCommentAlarm extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SendKockCommentAlarm() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("application/json; charset=utf-8");
		
		BufferedReader br = request.getReader();
		Gson gson = new Gson();
		JsonObject jsonObj = gson.fromJson(br, JsonObject.class);
		
		HttpSession session = request.getSession();
		User user =  null;
		
		if(session != null) {
			user=(User)session.getAttribute("user");
		}
		if(user == null) {
			request.getRequestDispatcher("/login").forward(request, response);
			return;
		}
		
		Long buyerUserNum = jsonObj.get("buyerUserNum").getAsLong();
		String kockTitle = jsonObj.get("kockTitle").getAsString();
		Long kockNum = jsonObj.get("kockNum").getAsLong();
		try {
			AlarmService alarmService = new AlarmServiceImpl();
			
			int count = alarmService.sendKockCommentAlarm(user.getFcmToken(),user.getUserNum(), buyerUserNum,kockTitle, kockNum);
			
			JsonObject res = new JsonObject();
			res.addProperty("success", true);
			res.addProperty("message", "알림 발송 완료");
			res.addProperty("count", count);
			response.getWriter().write(res.toString());
		} catch (Exception e) {
			e.printStackTrace();
            JsonObject result = new JsonObject();
            result.addProperty("success", false);
            result.addProperty("message", "알림 발송 중 오류 발생");
            response.setStatus(500);
            response.getWriter().write(result.toString());
		}
	}

}
