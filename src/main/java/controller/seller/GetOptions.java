package controller.seller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import dto.seller.ProductOption;
import service.seller.ProductService;
import service.seller.ProductServiceImpl;

/**
 * Servlet implementation class GetOptions
 */
@WebServlet("/getOptions")
public class GetOptions extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetOptions() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Long productNum = Long.parseLong(request.getParameter("productNum"));
        ProductService productService = new ProductServiceImpl();

        try {
            List<ProductOption> options = productService.selectProductOption(productNum);
            response.setContentType("application/json;charset=UTF-8");
            new Gson().toJson(options, response.getWriter());
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(500);
        }
	}

}
