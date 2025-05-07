package controller.common;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.json.simple.JSONObject;

/**
 * Servlet implementation class ImageUpload
 */
@WebServlet("/imageUpload")
@MultipartConfig(
	    fileSizeThreshold = 1024 * 1024, // 1MB
	    maxFileSize = 10 * 1024 * 1024,  // 10MB
	    maxRequestSize = 50 * 1024 * 1024 // 50MB
	)
public class ImageUpload extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ImageUpload() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

//        Part filePart = request.getPart("uploadFile");
//        String fileName = UUID.randomUUID() + "_" + filePart.getSubmittedFileName();
//        String uploadPath = getServletContext().getRealPath("/") + "uploads";

    	request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        Part filePart = request.getPart("uploadFile");
        String fileName = UUID.randomUUID() + "_" + filePart.getSubmittedFileName();

        // src/main/webapp/upload 폴더 경로
        String uploadPath = getServletContext().getRealPath("/upload");
        
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) uploadDir.mkdirs();

        filePart.write(uploadPath + File.separator + fileName);
        String imageUrl = request.getContextPath() + "/upload/" + fileName;

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        JSONObject json = new JSONObject();
        json.put("url", imageUrl);  // 이 URL이 에디터에 삽입됨

//        response.getWriter().write(json.toString());
        response.getWriter().write(json.toJSONString());
        
        System.out.println("파일 저장 위치: " + uploadPath + File.separator + fileName);
        System.out.println("이미지 URL: " + imageUrl);

    }

}
