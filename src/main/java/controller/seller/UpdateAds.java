package controller.seller;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import dto.User;
import dto.seller.Advertisement;
import dto.seller.Point;
import service.seller.AdsService;
import service.seller.AdsServiceImpl;
import service.seller.PointService;
import service.seller.PointServiceImpl;

/**
 * Servlet implementation class InsertAds
 */
@WebServlet("/updateAdsBySeller")
public class UpdateAds extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UpdateAds() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		User user =  null;
		
		if(session != null) {
			user=(User)session.getAttribute("user");
		}
		if(user == null) {
			request.getRequestDispatcher("/login").forward(request, response);
			return;
		}
		Long adsNum= Long.parseLong(request.getParameter("adsNum"));
		AdsService service = new AdsServiceImpl();
		try {
			Advertisement ads = service.selectAdsByAdsNum(adsNum);
			request.setAttribute("userName", user.getUserName());
			request.setAttribute("ads", ads);
			request.getRequestDispatcher("/seller/updateAds.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("err", "포인트 정보 획득 실패");
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}
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
        
		Long adsNum = Long.parseLong(multi.getParameter("adsNum"));

		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		Long userNum = user.getUserNum();
	
		AdsService service= new AdsServiceImpl();
		try {
			Advertisement ads = service.selectAdsByAdsNum(adsNum);

			// 로그인한 유저가 이 글 주인인지 체크
			if (!ads.getUserNum().equals(userNum)) {
			    response.sendError(403, "수정 권한이 없습니다.");
			    return;
			}
			
			ads.setAdsNum(adsNum);
			ads.setTitle(multi.getParameter("title"));
			ads.setContent(multi.getParameter("content"));
			ads.setProductName(multi.getParameter("productName"));
			ads.setImgUrl(multi.getFilesystemName("imgUrl"));
			ads.setContent(multi.getParameter("content"));
			ads.setProductUrl(multi.getParameter("productUrl"));
			
			
			service.updateAds(ads);
			response.sendRedirect(request.getContextPath()+"/sellerAdsList");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("err", "광고 수정 시 오류가 발생했습니다.");
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}
	}
	
}
