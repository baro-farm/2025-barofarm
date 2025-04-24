package controller.common;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import dto.User;
import service.UserService;
import service.UserServiceImpl;

/**
 * Servlet implementation class FindId
 */
@WebServlet("/findId")
public class FindId extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FindId() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	request.getRequestDispatcher("findId.jsp").forward(request, response);
    
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BufferedReader reader = request.getReader();
	    Gson gson = new Gson();
	    Map<String, String> param = gson.fromJson(reader, Map.class);

	    String email = param.get("email");
	    String phone = param.get("phone");
	    
	    UserService service = new UserServiceImpl();
	    
	    try {
	    	User user = service.findId(email, phone);
	    	
	    	Map<String, Object> result = new HashMap<>();
		    if (user != null) {
		      result.put("success", true);
		      result.put("userId", user.getUserId());
		      result.put("userName", user.getUserName());
		    } else {
		      result.put("success", false);
		    }

		    response.setContentType("application/json");
		    response.getWriter().write(gson.toJson(result));
	    } catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("err", "아이디 조회 실패");
		}
	  }
}
