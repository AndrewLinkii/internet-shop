package shop.service;

import java.util.List;
import shop.model.Order;
import shop.model.Product;
import shop.model.User;

public interface OrderService extends GenericService<Order,Long> {
    Order completeOrder(List<Product> products, User user);

    List<Order> getUserOrders(Long userId);
}
