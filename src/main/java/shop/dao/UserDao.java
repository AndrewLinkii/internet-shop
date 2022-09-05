package shop.dao;

import java.util.Optional;
import shop.model.User;

public interface UserDao extends GenericDao<User, Long> {
    Optional<User> findByLogin(String login);
}
