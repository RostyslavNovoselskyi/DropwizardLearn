package testProj.core;

import testProj.api.User;
import testProj.api.UserApi;
import testProj.db.UserDynamoEntity;
import testProj.db.UserDynamoDao;

import javax.inject.Inject;
import java.util.Optional;
import java.util.UUID;

public class UserService implements UserApi {

    private final UserDynamoDao userDao;
    private final UserMapper userMapper;

    @Inject
    public UserService(UserDynamoDao userDao, UserMapper userMapper) {
        this.userDao = userDao;
        this.userMapper = userMapper;
    }

    @Override
    public Optional<User> getUser(UUID id) {
        UserDynamoEntity userEntity = userDao.getUser(id);
        User user = userMapper.dynamoToUser(userEntity);
        return Optional.of(user);
    }

    @Override
    public User createUser(User user) {
        UserDynamoEntity userDynamoEntity = new UserDynamoEntity();
        userDynamoEntity.setUserId(user.getId().toString());
        userDynamoEntity.setUserName(user.getName());
        userDao.createUser(userDynamoEntity);

        return userMapper.dynamoToUser(userDynamoEntity);
    }
}
