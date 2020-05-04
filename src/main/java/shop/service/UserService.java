package shop.service;

import java.util.Optional;
import shop.model.User;

public interface UserService extends GenericService<User,Long> {
    Optional<User> findByLogin(String login);
}
