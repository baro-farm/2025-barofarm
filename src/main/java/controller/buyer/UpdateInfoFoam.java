package controller.buyer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.buyer.Address;
import service.UserServiceImpl;
import service.buyer.UserService;
import vo.UserVO;

/**
 * Servlet implementation class UpdateInfoFoam
 */
@WebServlet("/updateInfo")
public class UpdateInfoFoam extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateInfoFoam() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
			request.setCharacterEncoding("utf-8");
			response.setCharacterEncoding("utf-8");
			
			String userId= request.getParameter("userId");
			String userName= request.getParameter("userName");
			String phone= request.getParameter("phone");
			String email= request.getParameter("email");
			
			String postCode = request.getParameter("postCode");
			String addr1 = request.getParameter("addr1");
			String addr2 = request.getParameter("addr2");
			
			UserVO userVo=new UserVO();
			userVo.setUserId(userId);
			userVo.setUserName(userName);
			userVo.setPhone(phone);
			userVo.setEmail(email);
			
			Address address =new Address();
			address.setPostCode(postCode);
			address.setAddr1(addr1);
			address.setAddr2(addr2);
			
			List<Address> addrList=new ArrayList<>();
			addrList.add(address);
			userVo.setAddresses(addrList);
			
			UserService service = new service.buyer.UserServiceImpl();
			try {
				service.updateUserInfo(userVo);
				response.sendRedirect("infoFoam");
			}catch(Exception e) {
				e.printStackTrace();
			}

	}

}
