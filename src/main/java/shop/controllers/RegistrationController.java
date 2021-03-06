package shop.controllers;

import java.io.IOException;
import java.util.Set;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import shop.lib.Injector;
import shop.model.Role;
import shop.model.ShoppingCart;
import shop.model.User;
import shop.service.ShoppingCartService;
import shop.service.UserService;

public class RegistrationController extends HttpServlet {
    private static final Injector INJECTOR = Injector.getInstance("shop");
    private final UserService userService =
            (UserService) INJECTOR.getInstance(UserService.class);
    private final ShoppingCartService shoppingCartService =
            (ShoppingCartService) INJECTOR.getInstance(ShoppingCartService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("WEB-INF/views/registration.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String login = req.getParameter("login");
        String name = req.getParameter("user_name");
        String password = req.getParameter("psw");
        String pswRe = req.getParameter("psw-repeat");

        if (password.equals(pswRe)) {
            User user = new User(name, login, password);
            user.setRoles(Set.of(Role.of("ADMIN"),Role.of("USER")));
            userService.create(user);
            shoppingCartService.create(new ShoppingCart(user.getId()));
            HttpSession session = req.getSession();
            session.setAttribute("user_id", user.getId());
            resp.sendRedirect(req.getContextPath() + "/main");
        } else {
            req.setAttribute("msg", "Please repeat same password");
            req.getRequestDispatcher("WEB-INF/views/registration.jsp").forward(req, resp);
        }
    }
}
