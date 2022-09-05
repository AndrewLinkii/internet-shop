package shop.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import shop.exeption.AuthenticationException;
import shop.lib.Injector;
import shop.model.User;
import shop.security.AuthenticationService;

public class LoginController extends HttpServlet {
    private static final Injector INJECTOR = Injector.getInstance("shop");
    private static final Logger LOGGER = Logger.getLogger(LoginController.class);
    private final AuthenticationService authenticationService =
            (AuthenticationService) INJECTOR.getInstance(AuthenticationService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("WEB-INF/views/users/login.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String login = req.getParameter("login");
        String psw = req.getParameter("password");
        try {
            User user = authenticationService.login(login, psw);
            HttpSession session = req.getSession();
            session.setAttribute("user_id", user.getId());
            LOGGER.info("User with ID " + user.getId() + " login in ");
        } catch (AuthenticationException e) {
            req.setAttribute("msg", e.getMessage());
            req.getRequestDispatcher("/WEB-INF/views/users/login.jsp").forward(req, resp);
            return;
        }
        resp.sendRedirect(req.getContextPath() + "/main");
    }
}
