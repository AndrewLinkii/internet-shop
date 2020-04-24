package shop;

import shop.lib.Injector;
import shop.model.Product;
import shop.model.ShoppingCart;
import shop.model.User;
import shop.service.OrderService;
import shop.service.ProductService;
import shop.service.ShoppingCartService;
import shop.service.UserService;

import java.util.ArrayList;
import java.util.List;

public class Main {
    private static Injector injector = Injector.getInstance("shop");

    public static void main(String[] args) {

        //что то пошло не так , и я не могу залить изменения , єто комент для пуша , удалю до мерджа
        ProductService productService = (ProductService) injector.getInstance(ProductService.class);
        ShoppingCartService shoppingCartService = (ShoppingCartService) injector.getInstance(ShoppingCartService.class);
        UserService userService = (UserService) injector.getInstance(UserService.class);
        OrderService orderService = (OrderService) injector.getInstance(OrderService.class);

        User andrew =new User("Andrew", "1","1");
        User vita =new User("Vita", "2","2");
        User anna =new User("Anna", "3","3");
        User igor =new User("Igor", "4","4");

        userService.create(andrew);
        userService.create(vita);
        userService.create(anna);
        userService.create(igor);

        Product kiwi = new Product("Kiwi", 10);
        Product banana = new Product("Banana", 15);
        Product apple = new Product("Apple", 20);
        Product orange = new Product("Orange", 25);
        Product cherry = new Product("Cherry", 30);

        productService.create(kiwi);
        productService.create(banana);
        productService.create(apple);
        productService.create(orange);
        productService.create(cherry);

        ShoppingCart bucket1 = new ShoppingCart(new ArrayList<>(), andrew);
        ShoppingCart bucket2 = new ShoppingCart(new ArrayList<>(), vita);
        ShoppingCart bucket3 = new ShoppingCart(new ArrayList<>(), anna);
        ShoppingCart bucket4 = new ShoppingCart(new ArrayList<>(), igor);

        shoppingCartService.create(bucket1);
        shoppingCartService.create(bucket2);
        shoppingCartService.create(bucket3);
        shoppingCartService.create(bucket4);

        shoppingCartService.addProduct(bucket1,kiwi);
        shoppingCartService.addProduct(bucket1,kiwi);
        shoppingCartService.addProduct(bucket1,banana);
        shoppingCartService.addProduct(bucket1, banana);

        shoppingCartService.addProduct(bucket2,apple);
        shoppingCartService.addProduct(bucket2,apple);
        shoppingCartService.addProduct(bucket2,banana);
        shoppingCartService.addProduct(bucket2, banana);


        List<Product> products =shoppingCartService.getByUserId(andrew.getId()).getProducts();
        orderService.completeOrder(products, andrew);
        shoppingCartService.clear(bucket1);

       shoppingCartService.addProduct(bucket1, orange);
       shoppingCartService.addProduct(bucket1, apple);
       shoppingCartService.addProduct(bucket1, cherry);

        orderService.completeOrder(shoppingCartService.getByUserId(andrew.getId()).getProducts(), andrew);
        System.out.println(orderService.getUserOrders(andrew));

    }
}
