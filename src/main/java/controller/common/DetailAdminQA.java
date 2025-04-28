package controller.common;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dto.User;
import service.UserService;
import service.UserServiceImpl;
import vo.AdminQuestionVO;

/**
 * Servlet implementation class DetailAdminQA
 */
@WebServlet("/detailAdminQA")
public class DetailAdminQA extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DetailAdminQA() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
            return;
        }
        User loginUser = (User) session.getAttribute("user");
        String loginUserId = loginUser.getUserId();
        System.out.println("loginUserId :"+loginUserId);

        Long qNum = Long.parseLong(request.getParameter("questionNum"));
        
        UserService service = new UserServiceImpl();
        try {
            AdminQuestionVO adminq = service.detailAdminQA(qNum);
            System.out.println("adminqUserId :"+ adminq.getUserId());
            
            if (!loginUserId.equals(adminq.getUserId())) {
            	response.sendRedirect(request.getContextPath() + "/adminQAList?error=notAuthor");
                return;
            }
            
            request.setAttribute("adminq", adminq);
            request.getRequestDispatcher("/common/detailAdminQA.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/adminQAList?error=fail");
        }
	}

}
