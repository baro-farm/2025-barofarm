package controller.common;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import dto.User;
import service.UserService;
import service.UserServiceImpl;
import util.Mail;
import util.MailService;


/**
 * Servlet implementation class FindPwd
 */
@WebServlet("/findPwd")
public class FindPwd extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FindPwd() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("findPwd.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BufferedReader reader = request.getReader();
		Gson gson = new Gson();
		Map<String, String> param = gson.fromJson(reader, Map.class);

		String userId = param.get("userId");
		String email = param.get("email");

		UserService service = new UserServiceImpl();
		Map<String, Object> result = new HashMap<>();
		try {
			User user = service.findPwd(email, userId);
			System.out.println("findPwd/email: "+email+", userid: "+userId);
			
			if (user != null) {
			    String token = UUID.randomUUID().toString();
			    service.updateResetPwdToken(user.getUserNum(), token);
			    
			    // 메일 전송
			    String resetLink = "http://localhost:8080/barofarm/resetPwd?token=" + token;
			    String subject = "[바로팜] 비밀번호 재설정 링크입니다.";
			    String content = "\n아래 링크를 클릭하여 비밀번호를 재설정하세요:\n" + resetLink;
			    
		        Mail resetPwd = new Mail(user.getEmail(), subject, content);
		        MailService mailService = new MailService();
		        
		        boolean mailResult = mailService.sendMail(resetPwd);
		        if (mailResult) {
		            result.put("success", true);
		        } else {
		            result.put("success", false);
		            result.put("error", "메일 전송 실패");
		        }
			} else {
			    result.put("success", false);
			    result.put("error", "사용자 정보가 일치하지 않습니다.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		    result.put("success", false);
		    result.put("error", "메일 전송 실패");
		}
		response.setContentType("application/json");
		response.getWriter().write(gson.toJson(result));

	}

}
