package controller.seller;

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
 * Servlet implementation class DeleteUserBySeller
 */
@WebServlet("/deleteUserBySeller")
public class DeleteUserBySeller extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteUserBySeller() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=UTF-8");

        // 1. JSON 파싱
        BufferedReader reader = request.getReader();
        Gson gson = new Gson();
        JsonObject json = gson.fromJson(reader, JsonObject.class);

        String userId = json.get("userId").getAsString();
        UserService userService = new UserServiceImpl();

        // 2. 탈퇴 처리
        User user=null;
		try {
			user = userService.deleteUser(userId);
			
			// 3. 응답 생성
	        JsonObject result = new JsonObject();

	        if (user != null) {
	            // ✅ 세션 초기화 (로그아웃)
	            HttpSession session = request.getSession(false);
	            if (session != null) session.invalidate();

	            result.addProperty("msg", "탈퇴가 완료되었습니다.");
	            result.addProperty("redirect", request.getContextPath() + "/main"); // 메인 페이지로 이동
	        } else {
	            result.addProperty("msg", "탈퇴 처리 중 오류가 발생했습니다.");
	        }

	        // 4. 응답 전송
	        response.getWriter().write(result.toString());	
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		    JsonObject result = new JsonObject();
		    result.addProperty("msg", "서버 오류로 탈퇴에 실패했습니다.");
		    response.getWriter().write(result.toString());
		}
	}	
}
