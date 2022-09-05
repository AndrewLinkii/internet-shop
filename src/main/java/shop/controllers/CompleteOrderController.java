package shop.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import shop.lib.Injector;
import shop.model.ShoppingCart;
import shop.service.OrderService;
import shop.service.ShoppingCartService;
import shop.service.UserService;

public class CompleteOrderController extends HttpServlet {
    private static final String USER_ID = "user_id";
    private static final Injector INJECTOR = Injector.getInstance("shop");
    private final UserService userService =
            (UserService) INJECTOR.getInstance(UserService.class);
    private final ShoppingCartService shoppingCartService =
            (ShoppingCartService) INJECTOR.getInstance(ShoppingCartService.class);
    private final OrderService orderService =
            (OrderService) INJECTOR.getInstance(OrderService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Long userId = (Long) req.getSession().getAttribute(USER_ID);
        ShoppingCart shoppingCart = shoppingCartService.getByUserId(userId);
        if (shoppingCart.getProducts().isEmpty()) {
            String msg = "Sorry but you shopping cart is empty";
            req.setAttribute("msg", msg);
            req.getRequestDispatcher("/WEB-INF/views/shoppingCart.jsp").forward(req, resp);
            return;
        }
        orderService.completeOrder(shoppingCart.getProducts(), userService.get(userId));
        resp.sendRedirect(req.getContextPath() + "/orders/user/all");
    }
}
