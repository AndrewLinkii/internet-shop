package shop.dao.jdbcimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import shop.dao.ProductDao;
import shop.exeption.DataProcessingException;
import shop.lib.Dao;
import shop.model.Product;
import shop.util.ConnectionUtil;

@Dao
public class ProductJdbcImpl implements ProductDao {

    @Override
    public Product create(Product product) {
        try (Connection connection = ConnectionUtil.getConnection()) {
            String query = "INSERT INTO products(name, price) VALUES (?, ?);";
            PreparedStatement statement = connection.prepareStatement(query,
                    Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, product.getName());
            statement.setDouble(2, product.getPrice());
            statement.execute();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                product.setId(resultSet.getLong(1));
            }
            return product;
        } catch (SQLException e) {
            throw new DataProcessingException("Can`t add product to DB", e);
        }
    }

    @Override
    public Optional<Product> get(Long id) {
        try (Connection connection = ConnectionUtil.getConnection()) {
            String query = "SELECT * FROM products WHERE product_id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return Optional.of(getProduct(resultSet));
            }
            return Optional.empty();
        } catch (SQLException e) {
            throw new DataProcessingException("Can`t get product whit id " + id, e);
        }
    }

    @Override
    public Product update(Product product) {
        try (Connection connection = ConnectionUtil.getConnection()) {
            String query = "UPDATE products SET name = ?, price = ? WHERE product_id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, product.getName());
            statement.setDouble(2, product.getPrice());
            statement.setLong(3, product.getId());
            return get(product.getId()).get();
        } catch (SQLException e) {
            throw new DataProcessingException("Can`t update product in DB", e);
        }
    }

    @Override
    public boolean delete(Long id) {
        String query = "DELETE FROM products WHERE product_id = ?";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, id);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DataProcessingException("Can`t delete product in DB", e);
        }
    }

    @Override
    public List<Product> getAll() {
        List<Product> products = new ArrayList<>();
        String query = "SELECT * FROM products;";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            Product product;
            while (resultSet.next()) {
                product = getProduct(resultSet);
                products.add(product);
            }
            return products;
        } catch (SQLException e) {
            throw new DataProcessingException("Can`t receive all products from DB", e);
        }
    }

    private Product getProduct(ResultSet resultSet) throws SQLException {
        Long productId = resultSet.getLong("product_id");
        String name = resultSet.getString("name");
        double price = resultSet.getDouble("price");
        Product product = new Product(name, price);
        product.setId(productId);
        return product;
    }
}
