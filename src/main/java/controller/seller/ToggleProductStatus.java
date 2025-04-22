package controller.seller;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import dto.User;
import dto.seller.Product;
import service.seller.ProductService;
import service.seller.ProductServiceImpl;
import service.seller.SellerService;
import service.seller.SellerServiceImpl;

@WebServlet("/toggleProductStatus")
public class ToggleProductStatus extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ToggleProductStatus() {
        super();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("application/json; charset=utf-8");

        JsonObject resJson = new JsonObject();

        try {
            // 1. JSON 파싱
            BufferedReader reader = request.getReader();
            JsonObject json = JsonParser.parseReader(reader).getAsJsonObject();
            Long productNum = json.get("productNum").getAsLong();

            // 2. 로그인 유저 확인
            User user = (User) request.getSession().getAttribute("user");
            if (user == null) {
                resJson.addProperty("success", false);
                resJson.addProperty("error", "unauthorized");
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().write(resJson.toString());
                return;
            }

            Long userNum = user.getUserNum();

            // 3. 상품 및 판매자 번호 확인
            ProductService productService = new ProductServiceImpl();
            Product product = productService.selectProduct(productNum);

            SellerService sellerService = new SellerServiceImpl();
            Long sellerNum = sellerService.selectSellerNum(userNum);

            if (!product.getSellerNum().equals(sellerNum)) {
                resJson.addProperty("success", false);
                resJson.addProperty("error", "forbidden");
                resJson.addProperty("message", "해당 상품의 판매자만 상태를 변경할 수 있습니다.");
                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                response.getWriter().write(resJson.toString());
                return;
            }

            // 4. 상태 토글 및 반영
            product.setStatus(!product.isStatus());
            productService.updateProductStatus(product);

            resJson.addProperty("success", true);
            resJson.addProperty("message", product.isStatus() ? "판매가 재개되었습니다." : "판매가 중단되었습니다.");

        } catch (Exception e) {
            e.printStackTrace();
            resJson.addProperty("success", false);
            resJson.addProperty("error", "server_error");
            resJson.addProperty("message", "서버 오류가 발생했습니다.");
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }

        response.getWriter().write(resJson.toString());
    }
}
