package shop.dao.impl;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import shop.dao.ProductDao;
import shop.db.Storage;
import shop.lib.Dao;
import shop.model.Product;

@Dao
public class ProductDaoImpl implements ProductDao {
    @Override
    public Product create(Product product) {
        if (product != null) {
            Storage.add(product);
        } else {
            throw new NoSuchElementException("Cant create because Product is null");
        }
        return product;
    }

    @Override
    public Optional<Product> get(Long id) {
        return Storage.products
                .stream()
                .filter(p -> p.getId().equals(id))
                .findFirst();
    }

    @Override
    public List<Product> getAll() {
        return Storage.products;
    }

    @Override
    public Product update(Product product) {
        int index = Storage.products.indexOf(product);
        Storage.products.set(index, product);
        return product;
    }

    @Override
    public boolean delete(Long id) {
        return Storage.products.removeIf(p -> p.getId().equals(id));
    }
}
