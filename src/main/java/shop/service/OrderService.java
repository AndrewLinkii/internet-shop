package shop.service;

import java.util.List;
import shop.model.Order;
import shop.model.Product;
import shop.model.User;


public interface OrderService {
    Order completeOrder(List<Product> products, User user);

    List<Order> getUserOrders(User user);

    Order get(Long id);

    List<Order> getAll();

    boolean delete(Long id);
}
