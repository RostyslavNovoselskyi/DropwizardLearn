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
        UserEntity user = userDao.getUser(id);

        return Optional.of(User.builder()
                .id(user.getId())
                .name(user.getUserName())
                .build());
    }

    @Override
    public User createUser(User user) {
        UserEntity userEntity = new UserEntity();
        userEntity.setUserName(user.getName());
        UserEntity userEntity1 = userDao.createUser(userEntity);

        return User.builder().id(userEntity1.getId()).name(userEntity1.getUserName()).build();
    }
}
