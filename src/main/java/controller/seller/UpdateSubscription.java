package controller.seller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import dto.User;
import service.seller.SellerService;
import service.seller.SellerServiceImpl;

/**
 * Servlet implementation class UpdateSubscription
 */
@WebServlet("/updateSubscription")
public class UpdateSubscription extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateSubscription() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1. JSON 데이터를 읽어온다. + 파싱
		BufferedReader reader = request.getReader(); //요청된 body 읽기
		Gson gson = new Gson();
        JsonObject jsonObject = gson.fromJson(reader, JsonObject.class);//바디를 JsonObject로 파싱
        String action = jsonObject.get("action").getAsString();
        System.out.println(action);
        //2. 구독 처리
        boolean success = false;
        HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
        SellerService service = new SellerServiceImpl();
		try {
			if ("subscribe".equals(action)) {
		        service.changeIsAlarm(user.getUserNum());
				success = true;
			} else if("unsubscribe".equals(action)) {
		        service.changeIsAlarm(user.getUserNum());
				success = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//3.응답을 보낸다.
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");
		
		JsonObject result = new JsonObject();
		result.addProperty("success",success);
		
		PrintWriter out = response.getWriter();
		out.print(gson.toJson(result));
		out.flush();
	}

}
