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
    private final UserMapper userMapper;

    @Inject
    public UserService(UserDao userDao, UserMapper userMapper) {
        this.userDao = userDao;
        this.userMapper = userMapper;
    }

    @Override
    public Optional<User> getUser(UUID id){
        UserEntity userEntity = userDao.getUser(id);
        User user = userMapper.INSTANCE.entityToUser(userEntity);
        return Optional.of(user);
    }

    @Override
    public User createUser(User user) {
        UserEntity userEntity = new UserEntity();
        userEntity.setUserName(user.getName());
        UserEntity userEntityNew = userDao.createUser(userEntity);

        user = userMapper.INSTANCE.entityToUser(userEntityNew);
        return user;
    }
}
