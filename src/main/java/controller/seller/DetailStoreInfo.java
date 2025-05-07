package controller.seller;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import dto.User;
import service.buyer.UserService;
import service.buyer.UserServiceImpl;
import service.seller.SellerService;
import service.seller.SellerServiceImpl;
import vo.SellerVO;
import vo.UserVO;

/**
 * Servlet implementation class DetailStoreInfo
 */
@WebServlet("/detailStoreInfo")
public class DetailStoreInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DetailStoreInfo() {
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
		SellerService sellerService = new SellerServiceImpl();
		UserService userService = new UserServiceImpl();
		
		
		try {
			SellerVO seller = sellerService.getSerllerDetail(user.getUserNum());
			UserVO userVo = userService.selectUserInfo(user.getUserId());
			request.setAttribute("seller", seller);
			System.out.println(userVo);
			request.setAttribute("user", userVo);
			request.getRequestDispatcher("/seller/detailStoreInfo.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("err", "판매자의 스토어정보 획득 실패");
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=UTF-8");

        BufferedReader reader = request.getReader();
        Gson gson = new Gson();
        JsonObject json = gson.fromJson(reader, JsonObject.class);

        // JSON에서 값 추출
        String userId = json.get("userId").getAsString();
        String pwd = json.get("pwd").getAsString();
        String phone = json.get("phone").getAsString();
        String email = json.get("email").getAsString();
        String postCode = json.get("postCode").getAsString();
        String addr1 = json.get("addr1").getAsString();
        String addr2 = json.get("addr2").getAsString();
        String storeName = json.get("storeName").getAsString();


        HttpSession session = request.getSession(false);
        User user  = (User) session.getAttribute("user");
        Long userNum = user.getUserNum();
        
        service.UserService uService = new service.UserServiceImpl();
        boolean success = false;
        try {

            if (userNum != null) {
                // 서비스에 전달할 map 또는 DTO 구성
                success = uService.updateSellerAccountInfo(userNum, pwd, phone, email, storeName, postCode, addr1, addr2);
            }
		} catch (Exception e) {
	        e.printStackTrace();
        }
        
        JsonObject result = new JsonObject();
        result.addProperty("msg", success ? "정보가 수정되었습니다." : "수정에 실패했습니다.");

        response.getWriter().write(result.toString());
	}

}
