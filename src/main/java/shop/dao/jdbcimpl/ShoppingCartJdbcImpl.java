package shop.dao.jdbcimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import shop.dao.ShoppingCartDao;
import shop.exeption.DataProcessingException;
import shop.lib.Dao;
import shop.model.Product;
import shop.model.ShoppingCart;
import shop.util.ConnectionUtil;

@Dao
public class ShoppingCartJdbcImpl implements ShoppingCartDao {
    @Override
    public ShoppingCart create(ShoppingCart cart) {
        try (Connection connection = ConnectionUtil.getConnection()) {
            String query = "INSERT INTO carts (user_id) VALUES (?)";
            PreparedStatement preparedStatement =
                    connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setLong(1, cart.getUserId());
            preparedStatement.executeUpdate();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                cart.setId(generatedKeys.getLong(1));
            }
            return cart;
        } catch (SQLException e) {
            throw new DataProcessingException("cant create cart");
        }
    }

    @Override
    public Optional<ShoppingCart> get(Long id) {
        try (Connection connection = ConnectionUtil.getConnection()) {
            String query = "SELECT * FROM carts WHERE cart_id = ? ";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                ShoppingCart shoppingCart = getCartFromResultSet(resultSet);
                shoppingCart.setProducts(getProducts(shoppingCart));
                return Optional.of(shoppingCart);
            }
            return Optional.empty();
        } catch (SQLException e) {
            throw new DataProcessingException("Cant get cart");
        }
    }

    @Override
    public ShoppingCart update(ShoppingCart cart) {
        try (Connection connection = ConnectionUtil.getConnection()) {
            String deleteQuery = "DELETE FROM carts_products WHERE cart_id = ?";
            String addQuery = "INSERT INTO carts_products (cart_id, product_id) VALUES (?, ?)";
            PreparedStatement clearStatement = connection.prepareStatement(deleteQuery);
            PreparedStatement updateStatement = connection.prepareStatement(addQuery);
            clearStatement.setLong(1, cart.getId());
            clearStatement.executeUpdate();

            for (Product product : cart.getProducts()) {
                updateStatement.setLong(1, cart.getId());
                updateStatement.setLong(2, product.getId());
                updateStatement.executeUpdate();
            }
            return cart;
        } catch (SQLException e) {
            throw new DataProcessingException("Cant Update cart");
        }
    }

    @Override
    public boolean delete(Long id) {
        try (Connection connection = ConnectionUtil.getConnection()) {
            String query = "DELETE FROM carts WHERE cart_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, id);
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DataProcessingException("Cant delete cart");
        }
    }

    @Override
    public List<ShoppingCart> getAll() {
        try (Connection connection = ConnectionUtil.getConnection()) {
            String query = "SELECT * FROM carts";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<ShoppingCart> allCarts = new ArrayList<>();

            while (resultSet.next()) {
                ShoppingCart shoppingCart = getCartFromResultSet(resultSet);
                shoppingCart.setProducts(getProducts(shoppingCart));
                allCarts.add(shoppingCart);
            }
            return allCarts;
        } catch (SQLException e) {
            throw new DataProcessingException("Cant get all carts");
        }
    }

    private ShoppingCart getCartFromResultSet(ResultSet resultSet) throws SQLException {
        Long cartId = resultSet.getLong("cart_id");
        Long userId = resultSet.getLong("user_id");
        ShoppingCart shoppingCart = new ShoppingCart(userId);
        shoppingCart.setId(cartId);
        return shoppingCart;
    }

    private List<Product> getProducts(ShoppingCart shoppingCart) {
        try (Connection connection = ConnectionUtil.getConnection()) {
            String query = "SELECT * FROM products INNER JOIN carts_products "
                    + "ON products.product_id = carts_products.product_id "
                    + "WHERE carts_products.cart_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, shoppingCart.getId());
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
