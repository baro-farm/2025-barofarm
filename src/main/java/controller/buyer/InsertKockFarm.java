package controller.buyer;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import dto.User;
import dto.buyer.KockFarm;
import service.buyer.KockFarmService;
import service.buyer.KockFarmServiceImpl;

/**
 * Servlet implementation class InsertKockFarm
 */
@WebServlet("/insertKockFarm")
public class InsertKockFarm extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertKockFarm() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		request.setAttribute("userName", user.getUserName());
		request.setAttribute("user", user);

		request.getRequestDispatcher("/buyer/insertKockFarm.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("application/json; charset=utf-8"); // fetch 응답을 JSON으로 세팅
		
		Gson gson = new Gson();
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");

		if (user == null) {
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			gson.toJson("로그인이 필요합니다.", response.getWriter());
			return;
		}
		

		try {
			String path = request.getServletContext().getRealPath("upload");
			int size = 10*1024*1024;
			
			MultipartRequest multi = new MultipartRequest(request, path, size, "utf-8",
					new DefaultFileRenamePolicy());
			


			Long userNum = user.getUserNum();
			Long cateNum = Long.parseLong(multi.getParameter("cateNum"));
			String title = multi.getParameter("title");
			Integer quantity = Integer.parseInt(multi.getParameter("quantity"));
			Integer price = Integer.parseInt(multi.getParameter("price"));
			
			DateTimeFormatter  fommatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			LocalDate shipDate = LocalDate.parse(multi.getParameter("shipDate"),fommatter);
			
			String content = multi.getParameter("content");
			String imgUrl = multi.getFilesystemName("ifile");
					
			KockFarm kockfarm = new KockFarm(userNum, cateNum, title, quantity, price, shipDate, content, imgUrl);
			KockFarmService service = new KockFarmServiceImpl();
			
			Long kockNum = service.insertKockFarm(kockfarm);
			
			//json으로 성공응답
			JsonResponse successResponse = new JsonResponse(true, "콕팜 작성 완료", kockfarm.getKockNum());
			gson.toJson(successResponse, response.getWriter());
			
		} catch (Exception e) {
			e.printStackTrace();
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			JsonResponse successResponse = new JsonResponse(true, "콕팜 작성 실패");
			gson.toJson(successResponse, response.getWriter());
		}
		
	}
	// 내부 클래스: 응답용
	private class JsonResponse {
		boolean success;
		String message;
		Long kockNum;

		public JsonResponse(boolean success, String message) {
			this.success = success;
			this.message = message;
		}

		public JsonResponse(boolean success, String message, Long kockNum) {
			this.success = success;
			this.message = message;
			this.kockNum = kockNum;
		}
	}
}
