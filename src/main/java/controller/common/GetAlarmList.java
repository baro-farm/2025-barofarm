package controller.common;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import dto.User;
import dto.seller.Alarm;
import service.seller.AlarmService;
import service.seller.AlarmServiceImpl;

/**
 * Servlet implementation class GetAlarmList
 */
@WebServlet("/getAlarmList")
public class GetAlarmList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetAlarmList() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		User sessionUser = (User) session.getAttribute("user");
		Long sessionUserNum = sessionUser.getUserNum();

		Long reNum = Long.parseLong(request.getParameter("reNum"));

		if (!sessionUserNum.equals(reNum)) {
			response.setStatus(HttpServletResponse.SC_FORBIDDEN);
			response.getWriter().write("{\"error\": \"권한이 없습니다.\"}");
		    return;
		}
		AlarmService alarmService = new AlarmServiceImpl();
        
		try {
			List<Alarm> list = alarmService.recentAlarmList(reNum);
	        
			Gson gson = new GsonBuilder()
					.registerTypeAdapter(LocalDateTime.class, new JsonSerializer<LocalDateTime>() {
						@Override
						public JsonElement serialize(LocalDateTime src, java.lang.reflect.Type typeOfSrc, JsonSerializationContext context) {
							return new JsonPrimitive(src.toString());
						}
					})
					.create();
			
			response.setContentType("application/json; charset=utf-8");
			gson.toJson(list, response.getWriter());
		} catch (Exception e) {
			e.printStackTrace();
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			response.setContentType("application/json; charset=utf-8");

			response.getWriter().write("{\"error\": \"알림 조회 중 서버 오류가 발생했습니다.\"}");
		
		}

	}

}
