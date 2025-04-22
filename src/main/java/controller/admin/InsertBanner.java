package controller.admin;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import dto.User;
import dto.admin.Banner;
import service.admin.BannerService;
import service.admin.BannerServiceImpl;

/**
 * Servlet implementation class InsertBanner
 */
@WebServlet("/insertBanner")
public class InsertBanner extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertBanner() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");

		String path = request.getServletContext().getRealPath("upload");
		int size = 10*1024*1024;

		MultipartRequest multi = new MultipartRequest(request, path,size,"utf-8",
				new DefaultFileRenamePolicy());

		String title = multi.getParameter("title");
		String imgUrl = multi.getFilesystemName("imgUrl");

		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		Long userNum = user.getUserNum();
		
		Banner banner = new Banner(null, null, title, imgUrl, "http://localhost:8080/barofarm/main.jsp", null, null, true, userNum);

		response.setContentType("application/json; charset=UTF-8");
		PrintWriter out = response.getWriter();

		try {
			BannerService service = new BannerServiceImpl();
		    service.insertBannerByAdmin(banner);
		    out.print("{\"success\": true}");
		    out.flush(); // ✅ 꼭 flush 하자
		    out.close(); // 또는 자동으로 닫히게 하던가!
		} catch (Exception e) {
		    e.printStackTrace();
		    response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		    out.print("{\"success\": false, \"message\": \"배너 삽입 실패\"}");
		    out.flush(); // ✅ 꼭 flush 하자
		}
	}
}
