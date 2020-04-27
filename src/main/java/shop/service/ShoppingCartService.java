package shop.service;

import java.util.List;
import shop.model.Product;
import shop.model.ShoppingCart;

public interface ShoppingCartService {

    ShoppingCart addProduct(ShoppingCart shoppingCart, Product product);

    boolean deleteProduct(ShoppingCart shoppingCart, Product product);

    void clear(ShoppingCart shoppingCart);

    ShoppingCart getByUserId(Long userId);

    List<Product> getAllProducts(ShoppingCart shoppingCart);

    ShoppingCart create(ShoppingCart shoppingCart);

    List<ShoppingCart> getAll();

}
