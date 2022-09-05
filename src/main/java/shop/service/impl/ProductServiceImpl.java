package shop.service.impl;

import java.util.List;
import shop.dao.ProductDao;
import shop.lib.Inject;
import shop.lib.Service;
import shop.model.Product;
import shop.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
    @Inject
    private ProductDao productDao;

    @Override
    public Product create(Product product) {
        return productDao.create(product);
    }

    @Override
    public Product get(Long productId) {
        return productDao.get(productId).get();
    }

    @Override
    public List<Product> getAll() {
        return productDao.getAll();
    }

    @Override
    public Product update(Product product) {
        return productDao.update(product);
    }

    @Override
    public boolean delete(Long productId) {
        return productDao.delete(productId);
    }
}
