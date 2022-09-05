package shop.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Order order = (Order) o;

        if (!Objects.equals(id, order.id)) {
            return false;
        }
        if (!Objects.equals(products, order.products)) {
            return false;
        }
        if (!Objects.equals(userId, order.userId)) {
            return false;
        }
        return Objects.equals(totalPrice, order.totalPrice);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (products != null ? products.hashCode() : 0);
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        result = 31 * result + (totalPrice != null ? totalPrice.hashCode() : 0);
        return result;
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
