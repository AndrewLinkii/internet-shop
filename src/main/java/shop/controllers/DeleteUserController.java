package shop.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import shop.lib.Injector;
import shop.service.UserService;

public class DeleteUserController extends HttpServlet {
    private static final String USER_ID = "user_id";
    private static final Injector INJECTOR = Injector.getInstance("shop");
    private final UserService userService =
            (UserService) INJECTOR.getInstance(UserService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String id = req.getParameter("id");
        Long userId = (Long) req.getSession().getAttribute(USER_ID);
        if (Long.valueOf(id).equals(userId)) {
            req.getSession().removeAttribute(USER_ID);
        }
        userService.delete(Long.valueOf(id));
        resp.sendRedirect(req.getContextPath() + "/users");
    }
}
