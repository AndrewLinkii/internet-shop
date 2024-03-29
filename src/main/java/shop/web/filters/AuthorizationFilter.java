package shop.web.filters;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import shop.lib.Injector;
import shop.model.Role;
import shop.model.User;
import shop.service.UserService;

public class AuthorizationFilter implements Filter {
    private static final String USER_ID = "user_id";
    private static final Injector INJECTOR = Injector.getInstance("shop");
    private final UserService userService = (UserService) INJECTOR.getInstance(UserService.class);
    private final Map<String, Set<Role.RoleName>> protectedUrls = new HashMap<>();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        protectedUrls.put("/users/delete", Set.of(Role.RoleName.ADMIN));
        protectedUrls.put("/users/all", Set.of(Role.RoleName.ADMIN));
        protectedUrls.put("/products/edit", Set.of(Role.RoleName.ADMIN));
        protectedUrls.put("/products/add", Set.of(Role.RoleName.ADMIN));
        protectedUrls.put("/orders/add", Set.of(Role.RoleName.ADMIN));
        protectedUrls.put("/orders/delete", Set.of(Role.RoleName.ADMIN));
        protectedUrls.put("/orders/complete", Set.of(Role.RoleName.USER));
        protectedUrls.put("/orders/users/all", Set.of(Role.RoleName.USER));
        protectedUrls.put("/shoppingCart", Set.of(Role.RoleName.USER));
        protectedUrls.put("/products/all", Set.of(Role.RoleName.USER));
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        String requestUrl = req.getServletPath();
        if (protectedUrls.get(requestUrl) == null) {
            chain.doFilter(req, resp);
            return;
        }
        Long userId = (Long) req.getSession().getAttribute(USER_ID);
        if (userId == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }
        User user = userService.get(userId);
        if (isAuthorized(user, protectedUrls.get(requestUrl))) {
            chain.doFilter(req, resp);
            return;
        }
        req.getRequestDispatcher("/WEB-INF/views/accessDenied.jsp").forward(req, resp);
    }

    @Override
    public void destroy() {

    }

    private boolean isAuthorized(User user, Set<Role.RoleName> authorizedRoles) {
        for (Role.RoleName authorizedRole : authorizedRoles) {
            for (Role userRole : user.getRoles()) {
                if (authorizedRole.equals(userRole.getRoleName())) {
                    return true;
                }
            }
        }
        return false;
    }
}
