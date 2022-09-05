package shop.service.impl;

import java.util.List;
import shop.dao.ShoppingCartDao;
import shop.lib.Inject;
import shop.lib.Service;
import shop.model.Product;
import shop.model.ShoppingCart;
import shop.service.ShoppingCartService;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {
    @Inject
    private ShoppingCartDao shoppingCartDao;

    @Override
    public ShoppingCart create(ShoppingCart shoppingCart) {
        return shoppingCartDao.create(shoppingCart);
    }

    @Override
    public ShoppingCart addProduct(ShoppingCart shoppingCart, Product product) {
        shoppingCart.getProducts().add(product);
        shoppingCartDao.update(shoppingCart);
        return shoppingCart;
    }

    @Override
    public boolean deleteProduct(ShoppingCart shoppingCart, Product product) {
        for (int i = 0; i < shoppingCart.getProducts().size(); i++) {
            if (product.equals(shoppingCart.getProducts().get(i))) {
                shoppingCart.getProducts().remove(i);
                shoppingCartDao.update(shoppingCart);
                return true;
            }
        }
        return false;
    }

    @Override
    public void clear(ShoppingCart shoppingCart) {
        shoppingCart.getProducts().clear();
        shoppingCartDao.update(shoppingCart);
    }

    @Override
    public ShoppingCart getByUserId(Long userId) {
        return shoppingCartDao.getAll().stream()
                .filter(x -> x.getId().equals(userId))
                .findFirst().orElseThrow();
    }

    @Override
    public List<Product> getAllProducts(ShoppingCart shoppingCart) {
        return shoppingCart.getProducts();
    }

    @Override
    public List<ShoppingCart> getAll() {
        return shoppingCartDao.getAll();
    }
}
