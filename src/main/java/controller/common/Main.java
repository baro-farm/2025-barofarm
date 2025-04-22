package controller.common;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.admin.AdminQuestion;
import dto.admin.Banner;
import dto.admin.Notice;
import service.UserProductService;
import service.UserProductServiceImpl;
import service.UserService;
import service.UserServiceImpl;
import service.admin.BannerService;
import service.admin.BannerServiceImpl;
import service.admin.NoticeService;
import service.admin.NoticeServiceImpl;
import vo.ProductVO;

/**
 * Servlet implementation class Main
 */
@WebServlet("/main")
public class Main extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Main() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		NoticeService nservice = new NoticeServiceImpl();
		UserService uservice = new UserServiceImpl();
		UserProductService pservice = new UserProductServiceImpl();
		BannerService bservice = new BannerServiceImpl();

		try {
			// 베스트 TOP 5
			List<ProductVO> bestProducts = pservice.getBest5();
			request.setAttribute("bestProducts", bestProducts);
			// 신상품 TOP 5
			List<ProductVO> newProducts = pservice.getNew5();
			request.setAttribute("newProducts", newProducts);
			// 공지사항
			List<Notice> noticeList = nservice.recentNotices();
			request.setAttribute("noticeList", noticeList);
			// 문의하기
			List<AdminQuestion> adminQA = uservice.recentAdminQA();
			request.setAttribute("adminQA", adminQA);
			//메인배너 상태 변경
			bservice.updateExpiredBannerIsPosted();
			List<Banner> bannerList = bservice.selectBannerByIsPosted();
			request.setAttribute("bannerList", bannerList);
			

			request.getRequestDispatcher("main.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
