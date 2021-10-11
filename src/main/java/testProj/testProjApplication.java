package testProj;

import io.dropwizard.Application;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.hibernate.SessionFactory;
import testProj.api.User;
import testProj.api.UserApi;
import testProj.core.UserService;
import testProj.db.UserDao;
import testProj.db.UserEntity;
import testProj.health.TemplateHealthCheck;
import testProj.resources.UserResource;
import zone.dragon.dropwizard.HK2Bundle;

import javax.inject.Singleton;

public class testProjApplication extends Application<testProjConfiguration> {

    public static void main(final String[] args) throws Exception {
        new testProjApplication().run(args);
    }

    @Override
    public String getName() {
        return "Hello-World";
    }

    @Override
    public void initialize(final Bootstrap<testProjConfiguration> bootstrap) {
        bootstrap.addBundle(hibernate);
        HK2Bundle.addTo(bootstrap);
    }

    @Override
    public void run(final testProjConfiguration configuration,
                    final Environment environment) {
        environment
            .jersey()
            .register(
                    new AbstractBinder() {
                        @Override
                        protected void configure() {
                            bind(UserService.class).to(UserApi.class).in(Singleton.class);
                            bind(UserDao.class).to(UserApi.class).in(Singleton.class);
                            bind(hibernate.getSessionFactory()).to(SessionFactory.class);
                        }
                    }
            );
        environment.jersey().register(UserResource.class);

//        final UserDao dao = new UserDao(hibernate.getSessionFactory());
//        environment
//                .jersey()
//                .register(
//                        new AbstractBinder() {
//                            @Override
//                            protected void configure() {
//                                bind(UserDao.class).to(UserApi.class).in(Singleton.class);
//                            }
//                        }
//                );
//        environment.jersey().register(UserResource.class);

        final TemplateHealthCheck healthCheck = new TemplateHealthCheck();
        environment.healthChecks().register("ApiHealthCheck", healthCheck);
    }

    private final HibernateBundle<testProjConfiguration> hibernate = new HibernateBundle<testProjConfiguration>(UserEntity.class) {
        @Override
        public DataSourceFactory getDataSourceFactory(testProjConfiguration configuration) {
            return configuration.getDatabase();
        }
    };
}