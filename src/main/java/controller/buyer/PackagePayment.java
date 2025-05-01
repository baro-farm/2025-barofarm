package controller.buyer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import dto.User;
import dto.buyer.Address;
import dto.seller.PackageProduct;
import dto.seller.SellerDetail;
import service.buyer.UserService;
import service.buyer.UserServiceImpl;
import service.seller.PackageService;
import service.seller.PackageServiceImpl;
import service.seller.SellerService;
import service.seller.SellerServiceImpl;

/**
 * Servlet implementation class PackagePayment
 */
@WebServlet("/packagePayment")
public class PackagePayment extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PackagePayment() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = (User) request.getSession().getAttribute("user");
		if (user == null) {
			response.sendRedirect("/barofarm/login");
			return;
		}
		
		Long userNum = user.getUserNum();
		
		try {
			// 1. 패키지 번호 받아오기
			String packageNumStr = request.getParameter("packageNum");
			if (packageNumStr == null) {
				response.sendRedirect("/barofarm"); // 또는 오류 페이지
				return;
			}
			Long packageNum = Long.parseLong(packageNumStr);
			
			Properties props = new Properties();
			InputStream input = getServletContext().getResourceAsStream("/WEB-INF/config/config.properties");
			
			if (input == null) {
			    System.out.println("❌ properties 파일 경로 잘못됨!");
			} else {
			    props.load(input);
			    System.out.println("✅ properties 파일 로드 성공!");
			}
			
			String impKey = props.getProperty("imp.key");
			request.setAttribute("impKey", impKey);
			
			// 3. 패키지 정보 가져오기
			PackageService service = new PackageServiceImpl();
			PackageProduct pack = service.selectPackageProduct(packageNum); // 이 메서드는 이미 있다고 가정
			
			SellerService sellerService = new SellerServiceImpl();
			SellerDetail seller = sellerService.selectSellerDetail(pack.getSellerNum());
			
			request.setAttribute("pack", pack);
			request.setAttribute("storeName", seller.getStoreName());
	//		String cartNumsStr = request.getParameter("cartNums");
	//		System.out.println(cartNumsStr);
			
			
			
			request.getRequestDispatcher("/buyer/packagePayment.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = (User) request.getSession().getAttribute("user");
		if (user == null) {
			response.sendRedirect("/barofarm/login");
			return;
		}
		
		request.setAttribute("userName", user.getUserName());
		request.setAttribute("userPhone", user.getPhone());

		try {
			BufferedReader reader = request.getReader();
			JsonObject json = JsonParser.parseReader(reader).getAsJsonObject();

			Long packageNum = json.get("packageNum").getAsLong();

			// 주소 정보 조회
			UserService addrService = new UserServiceImpl();
			Long userNum = user.getUserNum();
			Address defaultAddress = addrService.selectDefaultAddress(userNum);
			List<Address> addressList = addrService.selectUserAddressList(user.getUserId());

			request.getSession().setAttribute("defaultAddress", defaultAddress);
			request.getSession().setAttribute("addressList", addressList);

			// 리다이렉트 (GET으로 넘기기)
			response.sendRedirect("/barofarm/packagePayment?packageNum=" + packageNum);

		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect("/barofarm/error.jsp");
		}
	}

}
