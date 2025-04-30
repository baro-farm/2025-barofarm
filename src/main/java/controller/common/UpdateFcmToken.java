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
import service.UserService;
import service.UserServiceImpl;

/**
 * Servlet implementation class UpdateFcmToken
 */
@WebServlet("/updateFcmToken")
public class UpdateFcmToken extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateFcmToken() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		Long userNum = user.getUserNum();
		
		if(userNum==null) {
			response.setStatus(401); //로그인 안됨
			return;
		}
		
        BufferedReader br = request.getReader();
        Gson gson = new Gson();
        JsonObject jsonObj = gson.fromJson(br, JsonObject.class);
        String newFcmToken = jsonObj.get("fcmToken").getAsString();
		
        try {
        	UserService userService = new UserServiceImpl();
        	userService.updateFcmTokenIfChanged(userNum, newFcmToken);
        	response.setStatus(200);
        }catch (Exception e) {
        	e.printStackTrace();
        	response.setStatus(500);
        }
	}

}
