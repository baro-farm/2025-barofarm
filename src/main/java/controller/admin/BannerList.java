package controller.admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import dto.admin.Banner;
import service.admin.BannerService;
import service.admin.BannerServiceImpl;

/**
 * Servlet implementation class BannerList
 */
@WebServlet("/getBannerList")
public class BannerList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BannerList() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    response.setContentType("application/json; charset=UTF-8");
		try {
			BannerService service= new BannerServiceImpl();
			List<Banner> bannerList = service.adminBannerList();
			
			Gson gson = new Gson();
			String json = gson.toJson(bannerList);
			
			PrintWriter out = response.getWriter();
			out.print(json);
			out.flush();
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("err", "배너 목록 조회 실패");
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}
	}

}
