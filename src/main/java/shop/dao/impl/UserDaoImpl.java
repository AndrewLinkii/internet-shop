package shop.dao.impl;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import shop.dao.UserDao;
import shop.db.Storage;
import shop.lib.Dao;
import shop.model.User;

@Dao
public class UserDaoImpl implements UserDao {
    @Override
    public User create(User user) {
        if (user != null) {
            Storage.add(user);
        } else {
            throw new NoSuchElementException("Cant create because User is null");
        }
        return user;
    }

    @Override
    public Optional<User> get(Long id) {
        return Storage.users.stream()
                .filter(u -> u.getId().equals(id))
                .findFirst();
    }

    @Override
    public List<User> getAll() {
        return Storage.users;
    }

    @Override
    public User update(User user) {
        int index = Storage.users.indexOf(user);
        Storage.users.set(index, user);
        return user;
    }

    @Override
    public boolean delete(Long id) {
        return Storage.users.removeIf(user -> user.getId().equals(id));
    }
}
