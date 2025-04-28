package controller.common;

import java.io.IOException;
import java.io.PrintWriter;
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
 * Servlet implementation class ChangePwd
 */
@WebServlet("/resetPwd")
public class ResetPwd extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ResetPwd() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("resetPwd.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		Gson gson = new Gson();
	    Map<String, String> params = gson.fromJson(request.getReader(), HashMap.class);
		
		String changePwd =  params.get("changePwd");
		String checkPwd =  params.get("checkPwd");
		String token =  params.get("resetPwdToken");
		System.out.println("ResetPwd(params)/"+changePwd+"/"+checkPwd+"/"+token);
		
		Map<String, Object> result = new HashMap<>();
		UserService service = new UserServiceImpl();
		
		try {
			User user = service.existingPwd(token);
			
			if (user == null) {
		        result.put("success", false);
		        result.put("message", "유효하지 않거나 만료된 링크입니다.");
		    } else if (service.isSamePassword(user.getUserNum(), checkPwd)) {
				result.put("success", false);
	            result.put("message", "기존과 동일한 비밀번호는 사용할 수 없습니다.");
	        } else {
	        	// 비밀번호 변경 및 토큰 초기화
	        	service.resetPwd(changePwd,user.getUserNum());
	            result.put("success", true);
	            result.put("message", "비밀번호가 성공적으로 변경되었습니다.");
	            System.out.println("ResetPwd/"+changePwd);
	        }
		
		}catch (Exception e) {
			result.put("success", false);
	        result.put("message", "서버 오류: " + e.getMessage());
            e.printStackTrace();
		}
		
		 response.setContentType("application/json;charset=UTF-8");
		 PrintWriter out = response.getWriter();
		 out.print(new Gson().toJson(result));
		 out.flush();
		
	}

}
