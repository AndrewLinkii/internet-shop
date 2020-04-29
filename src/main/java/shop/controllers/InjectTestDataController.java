package shop.controllers;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import shop.lib.Injector;
import shop.model.Product;
import shop.model.ShoppingCart;
import shop.model.User;
import shop.service.ProductService;
import shop.service.ShoppingCartService;
import shop.service.UserService;

public class InjectTestDataController extends HttpServlet {
    private static final Injector INJECTOR = Injector.getInstance("shop");
    private final UserService userService =
            (UserService) INJECTOR.getInstance(UserService.class);
    private final ProductService productService =
            (ProductService) INJECTOR.getInstance(ProductService.class);
    private final ShoppingCartService shoppingCartService =
            (ShoppingCartService) INJECTOR.getInstance(ShoppingCartService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        User user1 = new User("Andrew", "KOT", "111");
        User user2 = new User("Ira", "POP", "222");
        User user3 = new User("Tolya", "DJ", "333");
        userService.create(user1);
        userService.create(user2);
        userService.create(user3);

        Product product1 = new Product("Pen", 35.5);
        Product product2 = new Product("Book", 49.7);
        Product product3 = new Product("Glass", 212.5);
        productService.create(product1);
        productService.create(product2);
        productService.create(product3);

        ShoppingCart bucketUser1 = new ShoppingCart(new ArrayList<>(), user1);
        ShoppingCart bucketUser2 = new ShoppingCart(new ArrayList<>(), user2);
        ShoppingCart bucketUser3 = new ShoppingCart(new ArrayList<>(), user3);
        shoppingCartService.create(bucketUser1);
        shoppingCartService.create(bucketUser2);
        shoppingCartService.create(bucketUser3);
        req.getRequestDispatcher("WEB-INF/views/mainPage.jsp").forward(req, resp);
    }
}
