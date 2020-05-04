package shop.model;

import java.util.List;

public class Order {
    private Long id;
    private List<Product> products;
    private User user;
    private Double totalPrice;

    public Order(List<Product> products, User user) {
        this.products = products;
        this.user = user;
        totalPrice = getTotalPrice();
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public double getTotalPrice(){
        return products.stream()
                .mapToDouble(x ->  x.getPrice())
                .sum();
    }

    @Override
    public String toString() {
        return "\nOrder{"
                + "id = " + id
                + ", products = " + products
                + ", user = " + user
                + '}';
    }
}
