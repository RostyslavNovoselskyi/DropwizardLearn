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
    public Optional<User> getUser(UUID id){
        UserEntity userEntity = userDao.getUser(id);
        User user = UserMapper.INSTANCE.entityToUser(userEntity);
        return Optional.of(user);
//        return Optional.of(User.builder()
//                .id(user.getId())
//                .name(user.getUserName())
//                .build());
    }

    @Override
    public User createUser(User user) {
        UserEntity userEntity = new UserEntity();
        userEntity.setUserName(user.getName());
        UserEntity userEntityNew = userDao.createUser(userEntity);

        user = UserMapper.INSTANCE.entityToUser(userEntityNew);
        return user;
//        return User.builder().id(userEntityNew.getId()).name(userEntityNew.getUserName()).build();
    }
}
