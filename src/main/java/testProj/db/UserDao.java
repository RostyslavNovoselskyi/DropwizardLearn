package testProj.db;

import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import testProj.api.User;

import javax.inject.Inject;
import java.util.Optional;
import java.util.UUID;

public class UserDao extends AbstractDAO<User> {
    private final SessionFactory sessionFactory;

    @Inject
    public UserDao(SessionFactory sessionFactory) {
        super(sessionFactory);
        this.sessionFactory = sessionFactory;
    }

    public Optional<UserEntity> getUser(UUID id) {
        Session session = sessionFactory.getCurrentSession();
        UserEntity user = (UserEntity) session.createQuery("FROM UserEntity u WHERE u.id=:id")
                .setParameter("id", id);

        return Optional.of(user);
    }

    public void createUser(User user) {
        Session session = sessionFactory.getCurrentSession();
        session.save(user);
    }

}
