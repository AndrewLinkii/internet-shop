package shop.service.impl;

import java.util.List;
import shop.dao.UserDao;
import shop.lib.Inject;
import shop.lib.Service;
import shop.model.User;
import shop.service.UserService;

@Service
public class UserServiceImpl implements UserService {
    @Inject
    UserDao userDao;

    @Override
    public User create(User user) {
       return userDao.create(user);
    }

    @Override
    public User get(Long id) {
        return userDao.get(id).get();
    }

    @Override
    public List<User> getAll() {
        return userDao.getAll();
    }

    @Override
    public User update(User user) {
        return userDao.update(user);
    }

    @Override
    public boolean delete(Long id) {
        return userDao.delete(id);
    }
}
