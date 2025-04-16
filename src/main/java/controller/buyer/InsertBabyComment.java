package controller.buyer;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.buyer.BabyComment;
import dto.buyer.KockComment;
import service.buyer.KockCommentService;
import service.buyer.KockCommentServiceImpl;

/**
 * Servlet implementation class InsertKockComment
 */
@WebServlet("/insertBabyComment")
public class InsertBabyComment extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertBabyComment() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		Long userNum=Long.parseLong(request.getParameter("userNum"));
		Long kcNum=Long.parseLong(request.getParameter("kcNum"));
		String content = request.getParameter("content");
		
		BabyComment bComment = new BabyComment(kcNum, userNum, content);
		KockCommentService service = new KockCommentServiceImpl();
		
		response.setContentType("application/json;charset=UTF-8");
		PrintWriter out = response.getWriter();

		try {
			boolean success =service.insertBabyComment(bComment);// DB 삽입 로직
			if(success) {
	            // 댓글 등록 성공 시 사용자 이름도 함께 응답
	            BabyComment rBC = service.selectBCommentByReNum(bComment.getReNum());
	            String storeName= rBC.getStoreName();
	            String userName = rBC.getUserName();
				
				// JSON으로 응답
				String json = String.format(
					    "{" +
					        "\"success\": true," +
					        "\"comment\": {" +
					            "\"reNum\": %d," +
					            "\"userName\": \"%s\"," +
					            "\"content\": \"%s\"," +
					            "\"storeName\": \"%s\"," +
					            "\"kcNum\": %d" +
					        "}" +
					    "}",
					    bComment.getReNum(),
					    userName,
					    content.replace("\"", "\\\""),
					    storeName,
					    bComment.getKcNum()
					);
	            out.print(json);	
            } else {
                out.print("{\"success\": false, \"message\": \"DB 오류로 실패\"}");
			}
		} catch (Exception e) {
			e.printStackTrace();
	        out.print("{\"success\": false, \"message\": \"서버 예외 발생\"}");
		} finally {
	        out.flush();
	        out.close();
	    }
	}

}
