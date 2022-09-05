package shop.dao;

import java.util.List;
import java.util.Optional;

public interface GenericDao<T, K> {
    T create(T obj);

    Optional<T> get(K obj);

    T update(T obj);

    boolean delete(K obj);

    List<T> getAll();
}

