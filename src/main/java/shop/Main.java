package shop;

import shop.lib.Injector;
import shop.model.Product;
import shop.service.ProductService;

public class Main {
    private static Injector injector = Injector.getInstance("shop");

    public static void main(String[] args) {
        ProductService productService = (ProductService) injector.getInstance(ProductService.class);

        Product product1 = new Product("gun", 100.0);
        Product product2 = new Product("paper", 111.0);

        productService.create(product1);
        productService.create(product2);


        for (Product product : productService.getAll()) {
            System.out.println(product);
        }
        product1.setPrice(999.0);
        productService.update(product1);

        for (Product product : productService.getAll()) {
            System.out.println(product);
        }
        productService.delete(2L);
        productService.create(new Product("siga", 9.0));



    }
}
