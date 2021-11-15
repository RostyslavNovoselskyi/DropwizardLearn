package testProj.db;

import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;

import javax.inject.Inject;
import java.util.UUID;

public class UserEntityDao extends AbstractDAO<UserEntity> implements UserDao<UserEntity>{
    private final SessionFactory sessionFactory;

    @Inject
    public UserEntityDao(SessionFactory sessionFactory) {
        super(sessionFactory);
        this.sessionFactory = sessionFactory;
    }

    @Override
    public UserEntity getUser(UUID uuid) {
        return get(uuid);
    }

    @Override
    public UserEntity createUser(UserEntity userEntity) {
        return persist(userEntity);
    }

}
