package shop.dao.impl;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import shop.dao.OrderDao;
import shop.db.Storage;
import shop.lib.Dao;
import shop.model.Order;

@Dao
public class OrderDaoImpl implements OrderDao {
    @Override
    public Order create(Order order) {
        if (order != null) {
            Storage.add(order);
        } else {
            throw new NoSuchElementException("Cant create because Order is null");
        }
        return order;
    }

    @Override
    public Optional<Order> get(Long id) {
        return Storage.orders.stream()
                .filter(o -> o.getId().equals(id))
                .findFirst();
    }

    @Override
    public List<Order> getAll() {
        return Storage.orders;
    }

    @Override
    public Order update(Order order) {
        int index = Storage.orders.indexOf(order);
        Storage.orders.set(index, order);
        return order;
    }

    @Override
    public boolean delete(Long id) {
        return Storage.orders.removeIf(o -> o.getId().equals(id));
    }
}
