package shop.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import shop.lib.Injector;
import shop.model.Product;
import shop.model.ShoppingCart;
import shop.service.ProductService;
import shop.service.ShoppingCartService;

public class AddProductToShoppingCart extends HttpServlet {
    private static final Long USER_ID = 1L;
    private static final Injector INJECTOR = Injector.getInstance("shop");
    private final ProductService productService =
            (ProductService) INJECTOR.getInstance(ProductService.class);
    private final ShoppingCartService shoppingCartService =
            (ShoppingCartService) INJECTOR.getInstance(ShoppingCartService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Long productId = Long.valueOf(req.getParameter("id"));
        Product product = productService.get(productId);
        ShoppingCart shoppingCart = shoppingCartService.getByUserId(USER_ID);
        shoppingCartService.addProduct(shoppingCart, product);
        resp.sendRedirect(req.getContextPath() + "/allProducts");
    }
}
