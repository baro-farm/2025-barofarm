package controller.seller;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import service.buyer.PackReviewSerivce;
import service.buyer.PackReviewServiceImpl;
import service.buyer.ProdReviewSerivce;
import service.buyer.ProdReviewServiceImpl;

/**
 * Servlet implementation class InsertReviewComment
 */
@WebServlet("/insertPackReviewComment")
public class InsertPackReviewComment extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertPackReviewComment() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pkReviewNums = request.getParameter("pkReviewNums");  // 예: "1,2,3"
		request.setAttribute("pkReviewNums", pkReviewNums);

		// 화면 띄워주기
		request.getRequestDispatcher("/seller/insertPackReviewComment.jsp").forward(request, response);
	
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("application/json;charset=utf-8");
        
        BufferedReader reader = request.getReader();
        Gson gson = new Gson();
        JsonObject jsonObj = gson.fromJson(reader, JsonObject.class);
        
        JsonArray reviewNumsJson = jsonObj.getAsJsonArray("pkReviewNums");
        String commentContent = jsonObj.get("commentContent").getAsString();
        PackReviewSerivce service = new PackReviewServiceImpl();
        
        List<Long> pkReviewNums = new ArrayList<>();
        for(JsonElement review : reviewNumsJson) {
        	pkReviewNums.add(review.getAsLong());
        }
        
        try {
        	
        	System.out.println(pkReviewNums+""+commentContent);
        	
        	service.insertPackReviewComment(pkReviewNums, commentContent);
            // 응답 작성 (json-simple 사용)
            JSONObject responseJson = new JSONObject();
            responseJson.put("result", "success");
            response.getWriter().write(responseJson.toJSONString());        	
        }catch(Exception e) {
        	e.printStackTrace();
        }
	
	}

}
