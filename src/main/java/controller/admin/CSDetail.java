package controller.admin;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import dto.admin.AdminQuestion;
import service.admin.CSService;
import service.admin.CSServiceImpl;

/**
 * Servlet implementation class CSDetail
 */
@WebServlet("/csDetail")
public class CSDetail extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CSDetail() {
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
		AdminQuestion aq = null;
		
		try {
			aq = service.selectCustomerService(Long.parseLong(questionNum));

			// JSON 응답 설정
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");

			// JSON으로 변환 (gson 사용)
			Gson gson = new Gson();
			String json = gson.toJson(aq);
			response.getWriter().write(json);
//			System.out.println(json);

		} catch (Exception e) {
			e.printStackTrace();
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
	}

}
