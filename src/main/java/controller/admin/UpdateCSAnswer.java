package controller.admin;

import java.io.BufferedReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import dto.admin.AdminAnswer;
import service.admin.CSService;
import service.admin.CSServiceImpl;

/**
 * Servlet implementation class UpdateCSAnswer
 */
@WebServlet("/updateCSAnswer")
public class UpdateCSAnswer extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateCSAnswer() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String questionNum = request.getParameter("questionNum");
		if (questionNum == null) {
			 response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			 return;
		}
		
		CSService service = new CSServiceImpl();
		AdminAnswer aa = null;
		
		try {
			aa = service.selectAdminAnswer(Long.parseLong(questionNum));
			
			// JSON 응답 설정
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");

			// JSON으로 변환 (gson 사용)
			Gson gson = new Gson();
			String json = gson.toJson(aa);
			response.getWriter().write(json);
			System.out.println(json);
			
		} catch (Exception e) {
			e.printStackTrace();
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json; charset=UTF-8");
	    response.setCharacterEncoding("UTF-8");
		
		BufferedReader reader = request.getReader();
		Gson gson = new Gson();
		JsonObject jsonData = gson.fromJson(reader, JsonObject.class);
		
		long questionNum = jsonData.get("questionNum").getAsLong();
	    String answer = jsonData.get("answer").getAsString();
	    LocalDateTime now = LocalDateTime.now();
	    
	    CSService service = new CSServiceImpl();
	    boolean result = false;
	    
	    AdminAnswer adminAnswer = new AdminAnswer(questionNum, answer, now);
	    
	    try {
	        service.updateAdminAnswer(adminAnswer);
	        result = true;
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    
	    JsonObject jsonResponse = new JsonObject();
	    jsonResponse.addProperty("success", result);
	    response.getWriter().write(jsonResponse.toString());
	}

}
