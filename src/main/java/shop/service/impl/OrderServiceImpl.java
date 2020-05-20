package shop.service.impl;

import java.util.List;
import java.util.stream.Collectors;
import shop.dao.OrderDao;
import shop.lib.Inject;
import shop.lib.Service;
import shop.model.Order;
import shop.model.Product;
import shop.model.User;
import shop.service.OrderService;
import shop.service.ShoppingCartService;

@Service
public class OrderServiceImpl implements OrderService {
    @Inject
    private OrderDao orderDao;

    @Inject
    private ShoppingCartService shoppingCartService;

    @Override
    public Order completeOrder(List<Product> products, User user) {
        List<Product> copy = List.copyOf(products);
        Order order = orderDao.create(new Order(copy, user.getId()));
        orderDao.update(order);
        shoppingCartService.clear(shoppingCartService.getByUserId(user.getId()));
        return order;
    }

    @Override
    public List<Order> getUserOrders(Long userId) {
        return orderDao.getAll().stream()
                .filter(x -> x.getUserId().equals(userId))
                .collect(Collectors.toList());
    }

    @Override
    public Order create(Order elem) {
        return orderDao.create(elem);
    }

    @Override
    public Order get(Long id) {
        return orderDao.get(id).get();
    }

    @Override
    public List<Order> getAll() {
        return orderDao.getAll();
    }

    @Override
    public Order update(Order elem) {
        return orderDao.update(elem);
    }

    @Override
    public boolean delete(Long id) {
        return orderDao.delete(id);
    }
}
