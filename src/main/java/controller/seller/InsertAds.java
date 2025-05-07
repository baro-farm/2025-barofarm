package controller.seller;

import java.io.IOException;

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
import service.seller.SellerService;
import service.seller.SellerServiceImpl;
import vo.SellerVO;

/**
 * Servlet implementation class InsertAds
 */
@WebServlet("/insertAdsBySeller")
public class InsertAds extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public InsertAds() {
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
		PointService service = new PointServiceImpl();
		SellerService sellserService = new SellerServiceImpl();
		try {
			Point point = service.getPoint(user.getUserNum());
			SellerVO sell = sellserService.getSerllerDetail(user.getUserNum());
			if (point==null) {
				Integer zero =0;
				request.setAttribute("point", zero);
			} else {
				request.setAttribute("point", point.getPoint());
			}
			request.setAttribute("userName", user.getUserName());
			request.setAttribute("sellerNum", sell.getSellerNum());
			request.getRequestDispatcher("/seller/insertAds.jsp").forward(request, response);
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

		String title = multi.getParameter("title");
		String content = multi.getParameter("content");
		String productName = multi.getParameter("productName");
		String imgUrl = multi.getFilesystemName("imgUrl");
		String productUrl = multi.getParameter("productUrl");

		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		Long userNum = user.getUserNum();
		
		PointService serviceP = new PointServiceImpl();
		
		Advertisement ads = new Advertisement(title, content, productName, imgUrl, productUrl, userNum); 
		AdsService service= new AdsServiceImpl();
		try {
			//판매자의 보유 포인트 확인
			Point p = serviceP.getPoint(userNum);
			if (p.getPoint()<20000) {
				request.setAttribute("errorMsg", "포인트가 부족하여 광고를 등록할 수 없습니다.");
		        request.setAttribute("point", p); // 다시 렌더링할 때 사용할 수 있도록
		        request.getRequestDispatcher("/seller/insertAds.jsp").forward(request, response);
		        return;		
			}
			
			//2만 포인트 이상 시 광고등록
			service.insertAds(ads);
			response.sendRedirect(request.getContextPath()+"/sellerAdsList");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("err", "광고 작성시오류가 발생했습니다.");
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}
	}
	
}
