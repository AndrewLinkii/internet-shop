package shop;

import shop.lib.Injector;
import shop.model.Product;
import shop.model.User;
import shop.service.ProductService;
import shop.service.UserService;

public class Main {
    private static Injector injector = Injector.getInstance("shop");

    public static void main(String[] args) {
        ProductService productService = (ProductService) injector.getInstance(ProductService.class);
        UserService userService = (UserService) injector.getInstance(UserService.class);

        Product product1 = new Product("gun", 100.0);
        Product product2 = new Product("paper", 111.0);

        User user1 = new User("Vita", "1", "1");
        User user2 = new User("Gosha", "2", "2");
        User user3 = new User("Dima", "3", "3");


        userService.create(user1);
        userService.create(user2);
        userService.create(user3);




        for (User user : userService.getAll()) {
            System.out.println(user);
        }
        user1.setName("LILGosha");


    }
}
