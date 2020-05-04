package shop.security;

import shop.exeption.AuthenticationException;
import shop.lib.Inject;
import shop.lib.Service;
import shop.model.User;
import shop.service.UserService;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    @Inject
    UserService userService;

    @Override
    public User login(String login, String password) throws AuthenticationException {
        User user = userService.findByLogin(login)
                .orElseThrow(() -> new AuthenticationException("Incorrect login or password"));

        if (user.getPassword().equals(password)) {
            return user;
        }

        throw new AuthenticationException("Incorrect login or password");
    }
}
