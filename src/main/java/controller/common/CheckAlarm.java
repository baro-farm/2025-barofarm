package controller.common;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import service.seller.AlarmService;
import service.seller.AlarmServiceImpl;

/**
 * Servlet implementation class CheckAlarm
 */
@WebServlet("/checkAlarm")
public class CheckAlarm extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckAlarm() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("application/json; charset=utf-8");

		try {
			BufferedReader br = request.getReader();
			Gson gson = new Gson();
			JsonObject jsonObj = gson.fromJson(br, JsonObject.class);
			Long alarmNum = jsonObj.get("alarmNum").getAsLong();

			AlarmService alarmService = new AlarmServiceImpl();
			boolean success = alarmService.markAlarmAsRead(alarmNum);

			JsonObject res = new JsonObject();
			res.addProperty("success", success);
			response.getWriter().write(res.toString());

		} catch (Exception e) {
			e.printStackTrace();
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			JsonObject errorRes = new JsonObject();
			errorRes.addProperty("success", false);
			errorRes.addProperty("message", "알림 확인 처리 실패");
			response.getWriter().write(errorRes.toString());
		}
	}

}
