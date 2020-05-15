package shop.model;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private Long id;
    private List<Product> products;
    private Long userId;
    private Double totalPrice;

    public Order(List<Product> products, Long userId) {
        this.products = products;
        this.userId = userId;
        totalPrice = getTotalPrice();
    }

    public Order(Long userId) {
        this.userId = userId;
        this.products = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public double getTotalPrice() {
        return products.stream()
                .mapToDouble(Product::getPrice)
                .sum();
    }

    @Override
    public String toString() {
        return "\nOrder{"
                + "id = " + id
                + ", products = " + products
                + ", user = " + userId
                + '}';
    }
}
