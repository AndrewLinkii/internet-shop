package shop.dao.jdbcimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import shop.dao.OrderDao;
import shop.exeption.DataProcessingException;
import shop.lib.Dao;
import shop.model.Order;
import shop.model.Product;
import shop.util.ConnectionUtil;

@Dao
public class OrderDaoJdbcImpl implements OrderDao {
    @Override
    public Order create(Order order) {
        try (Connection connection = ConnectionUtil.getConnection()) {
            String query = "INSERT INTO orders (user_id) VALUES (?)";
            PreparedStatement preparedStatement =
                    connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setLong(1, order.getUserId());
            preparedStatement.executeUpdate();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                order.setId(generatedKeys.getLong(1));
            }
            return order;
        } catch (SQLException e) {
            throw new DataProcessingException("cant create order");
        }
    }

    @Override
    public Optional<Order> get(Long id) {
        try (Connection connection = ConnectionUtil.getConnection()) {
            String query = "SELECT * FROM orders WHERE order_id = ? ";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Order order = getOrderFromResultSet(resultSet);
                order.setProducts(getProducts(order));
                return Optional.of(order);
            }
            return Optional.empty();
        } catch (SQLException e) {
            throw new DataProcessingException("Cant get order");
        }
    }

    @Override
    public Order update(Order order) {
        try (Connection connection = ConnectionUtil.getConnection()) {
            String deleteQuery = "DELETE FROM order_products WHERE order_id = ?";
            String addQuery = "INSERT INTO order_products (order_id, product_id) VALUES (?, ?)";
            PreparedStatement clearStatement = connection.prepareStatement(deleteQuery);
            PreparedStatement updateStatement = connection.prepareStatement(addQuery);
            clearStatement.setLong(1, order.getId());
            clearStatement.executeUpdate();

            for (Product product : order.getProducts()) {
                updateStatement.setLong(1, order.getId());
                updateStatement.setLong(2, product.getId());
                updateStatement.executeUpdate();
            }
            return order;
        } catch (SQLException e) {
            throw new DataProcessingException("Cant Update order");
        }
    }

    @Override
    public boolean delete(Long id) {
        try (Connection connection = ConnectionUtil.getConnection()) {
            String query = "DELETE FROM orders WHERE order_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, id);
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DataProcessingException("Cant delete order");
        }
    }

    @Override
    public List<Order> getAll() {
        try (Connection connection = ConnectionUtil.getConnection()) {
            String query = "SELECT * FROM orders";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Order> allOrders = new ArrayList<>();

            while (resultSet.next()) {
                Order order = getOrderFromResultSet(resultSet);
                order.setProducts(getProducts(order));
                allOrders.add(order);
            }
            return allOrders;
        } catch (SQLException e) {
            throw new DataProcessingException("Cant get all orders");
        }
    }

    private Order getOrderFromResultSet(ResultSet resultSet) throws SQLException {
        Long orderId = resultSet.getLong("order_id");
        Long userId = resultSet.getLong("user_id");
        Order order = new Order(userId);
        order.setId(orderId);
        return order;
    }

    private List<Product> getProducts(Order order) {
        try (Connection connection = ConnectionUtil.getConnection()) {
            String query = "SELECT * FROM products INNER JOIN order_products "
                    + "ON products.product_id = order_products.product_id "
                    + "WHERE order_products.order_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, order.getId());
            List<Product> products = new ArrayList<>();
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Long productId = resultSet.getLong("product_id");
                String name = resultSet.getString("name");
                Double price = resultSet.getDouble("price");
                Product product = new Product(name, price);
                product.setId(productId);
                products.add(product);
            }
            return products;
        } catch (SQLException e) {
            throw new DataProcessingException("Cant get products from cart");
        }
    }
}
