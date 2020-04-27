package shop.dao.impl;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import shop.dao.ShoppingCartDao;
import shop.db.Storage;
import shop.lib.Dao;
import shop.model.ShoppingCart;

@Dao
public class ShoppingCartDaoImpl implements ShoppingCartDao {
    @Override
    public ShoppingCart create(ShoppingCart shoppingCart) {
        if (shoppingCart != null) {
            Storage.add(shoppingCart);
        } else {
            throw new NoSuchElementException("Cant create because ShoppingCart is null");
        }
        return shoppingCart;
    }

    @Override
    public Optional<ShoppingCart> get(Long id) {
        return Storage.shoppingCarts.stream()
                .filter(s -> s.getId().equals(id))
                .findFirst();
    }

    @Override
    public List<ShoppingCart> getAll() {
        return Storage.shoppingCarts;
    }

    @Override
    public ShoppingCart update(ShoppingCart shoppingCart) {
        int index = Storage.shoppingCarts.indexOf(shoppingCart);
        Storage.shoppingCarts.set(index, shoppingCart);
        return shoppingCart;
    }

    @Override
    public boolean delete(Long id) {
        return Storage.shoppingCarts.removeIf(s -> s.getId().equals(id));
    }
}
