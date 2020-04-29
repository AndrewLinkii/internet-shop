package shop.controllers;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import shop.lib.Injector;
import shop.model.Product;
import shop.model.ShoppingCart;
import shop.service.ShoppingCartService;

public class GetShopCartController extends HttpServlet {
    private static final Long USER_ID = 1L;

    private static final Injector INJECTOR = Injector.getInstance("shop");
    private final ShoppingCartService shoppingCartService =
            (ShoppingCartService) INJECTOR.getInstance(ShoppingCartService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        ShoppingCart shoppingCart = shoppingCartService.getByUserId(USER_ID);
        List<Product> products = shoppingCartService.getAllProducts(shoppingCart);
        req.setAttribute("products", products);
        req.getRequestDispatcher("WEB-INF/views/shoppingCart.jsp").forward(req, resp);
    }
}
