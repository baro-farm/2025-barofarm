package controller.common;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.User;
import service.seller.AlarmService;
import service.seller.AlarmServiceImpl;

/**
 * Servlet implementation class GetAlarmCount
 */
@WebServlet("/getAlarmCount")
public class GetAlarmCount extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetAlarmCount() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json; charset=UTF-8");
        Long userNum = ((User) request.getSession().getAttribute("user")).getUserNum();

        AlarmService service = new AlarmServiceImpl();
        int count = 0;

        try {
            count = service.getUnreadAlarmCount(userNum);
        } catch (Exception e) {
            e.printStackTrace();
        }

        response.getWriter().write("{\"count\":" + count + "}");
    }



}
