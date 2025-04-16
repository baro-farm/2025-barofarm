package controller.admin;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import dto.admin.AdminAnswer;
import dto.admin.AdminQuestion;
import service.admin.CSService;
import service.admin.CSServiceImpl;

/**
 * Servlet implementation class InsertCSAnswer
 */
@WebServlet("/insertCSAnswer")
public class InsertCSAnswer extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertCSAnswer() {
        super();
        // TODO Auto-generated constructor stub
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

	    CSService service = new CSServiceImpl();
	    boolean result = false;

	    AdminAnswer adminAnswer = new AdminAnswer(questionNum, answer);
	    
	    try {
	        service.writeAnswer(adminAnswer);
	        result = true;
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    
	    JsonObject jsonResponse = new JsonObject();
	    jsonResponse.addProperty("success", result);
	    response.getWriter().write(jsonResponse.toString());
	}

}
