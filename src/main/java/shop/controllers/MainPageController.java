package shop.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import shop.lib.Injector;
import shop.model.User;
import shop.service.UserService;

public class MainPageController extends HttpServlet {
    private static final String USER_ID = "user_id";
    private static final Injector INJECTOR = Injector.getInstance("shop");
    private final UserService userService =
            (UserService) INJECTOR.getInstance(UserService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Long userId = (Long) req.getSession().getAttribute(USER_ID);
        if (userId != null) {
            User user = userService.get(userId);
            String login = user.getLogin();
            if ((long) user.getRoles().size() >= 2) {
                req.setAttribute("userRole", "ADMIN");
            }
            req.setAttribute("name", login);
        }
        req.setAttribute("userId", userId);
        req.getRequestDispatcher("/WEB-INF/views/users/main.jsp").forward(req, resp);

    }
}
