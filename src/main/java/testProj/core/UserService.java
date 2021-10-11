package testProj.core;

import testProj.api.User;
import testProj.api.UserApi;
import testProj.db.UserDao;
import testProj.db.UserEntity;

import javax.inject.Inject;
import java.util.Optional;
import java.util.UUID;

public class UserService  implements UserApi {

    private final UserDao userDao;

    @Inject
    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public Optional<UserEntity> getUser(UUID id){
        return userDao.getUser(id);
    }

    @Override
    public void createUser(UUID id) {
        userDao.createUser(
                User.builder()
                        .id(id)
                        .name("John Doe")
                        .build()
        );
    }
}
