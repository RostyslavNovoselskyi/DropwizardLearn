package testProj.db;

import testProj.api.User;

import java.util.UUID;

public interface UserDao<T> {

    public T getUser(UUID uuid);

    public T createUser(T t);
}
