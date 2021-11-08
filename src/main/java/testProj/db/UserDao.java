package testProj.db;

import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;

import javax.inject.Inject;
import java.util.UUID;

public class UserDao extends AbstractDAO<UserEntity> {
    private final SessionFactory sessionFactory;

    @Inject
    public UserDao(SessionFactory sessionFactory) {
        super(sessionFactory);
        this.sessionFactory = sessionFactory;
    }

    public UserEntity getUser(UUID id) {
        UserEntity userEntity = get(id);

        return userEntity;
    }

    public UserEntity createUser(UserEntity user) {
        return persist(user);
    }

}
