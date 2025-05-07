package controller.buyer;

import java.io.IOException;
import java.util.Arrays;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.User;
import service.buyer.ShoppingCartService;
import service.buyer.ShoppingCartServiceImpl;

@WebServlet("/updateCartOptions")
public class UpdateCartOptions extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public UpdateCartOptions() { super(); }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    request.setCharacterEncoding("utf-8");

    ShoppingCartService service = new ShoppingCartServiceImpl();

    try {
        // 수정
        String paramCartNums = request.getParameter("cartNums");
        String paramQuantities = request.getParameter("hiddenQuantities");

        System.out.println(paramCartNums);
        System.out.println(paramQuantities);
        Enumeration<String> paramNames = request.getParameterNames();
        while (paramNames.hasMoreElements()) {
            String paramName = paramNames.nextElement();
            String[] paramValues = request.getParameterValues(paramName);
            System.out.println(paramName + ": " + Arrays.toString(paramValues));
        }

        if (paramCartNums != null && !paramCartNums.isBlank()) {
            String[] cartNums = paramCartNums.split(",");
            String[] quantities = paramQuantities.split(",");

            System.out.println("Received cartNums: " + Arrays.toString(cartNums));
            System.out.println("Received quantities: " + Arrays.toString(quantities));

            for (int i = 0; i < cartNums.length; i++) {
                if (!cartNums[i].isBlank() && !quantities[i].isBlank()) {
                    Long cartNum = Long.parseLong(cartNums[i].trim());
                    Integer quantity = Integer.parseInt(quantities[i].trim());
                    service.updateCartQuantity(cartNum, quantity);
                }
            }
        }

        // 추가
        String paramNewOptionNums = request.getParameter("newOptionNums");
        String paramNewQuantities = request.getParameter("newQuantities");

        if (paramNewOptionNums != null && !paramNewOptionNums.isBlank()) {
            String[] newOptionNums = paramNewOptionNums.split(",");
            String[] newQuantities = paramNewQuantities.split(",");

            User user = (User) request.getSession().getAttribute("user");
            Long userNum = user.getUserNum();

            for (int i = 0; i < newOptionNums.length; i++) {
                Long optionNum = Long.parseLong(newOptionNums[i].trim());
                Integer quantity = Integer.parseInt(newQuantities[i].trim());
                service.addCartOption(userNum, optionNum, quantity);
            }
        }

        // 삭제
        String paramDeleteCartNums = request.getParameter("deleteCartNums");

        if (paramDeleteCartNums != null && !paramDeleteCartNums.isBlank()) {
            String[] deleteCartNums = paramDeleteCartNums.split(",");
            for (String del : deleteCartNums) {
                if (!del.isBlank()) {
                    service.deleteCartOption(Long.parseLong(del.trim()));
                }
            }
        }

        response.sendRedirect("/barofarm/shoppingCart");

    } catch (Exception e) {
        e.printStackTrace();
        response.sendRedirect("/barofarm/shoppingCart?error=true");
    }
}

}