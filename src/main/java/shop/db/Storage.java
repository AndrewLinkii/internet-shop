package shop.db;

import java.util.ArrayList;
import java.util.List;
import shop.model.Order;
import shop.model.Product;
import shop.model.ShoppingCart;
import shop.model.User;

public class Storage {
    public static final List<Product> products = new ArrayList<>();
    public static final List<ShoppingCart> shoppingCarts = new ArrayList<>();
    public static final List<User> users = new ArrayList<>();
    public static final List<Order> orders = new ArrayList<>();

    public static Long userId = 0L;
    public static Long shoppingCartId = 0L;
    public static Long orderId = 0L;
    public static Long productId = 0L;

    public static void add(Product product) {
        productId++;
        product.setId(productId);
        products.add(product);
    }

    public static void add(ShoppingCart shoppingCart) {
        shoppingCartId++;
        shoppingCart.setId(shoppingCartId);
        shoppingCarts.add(shoppingCart);
    }

    public static void add(User user) {
        userId++;
        user.setId(userId);
        users.add(user);
    }

    public static void add(Order order) {
        orderId++;
        order.setId(orderId);
        orders.add(order);
    }
}
