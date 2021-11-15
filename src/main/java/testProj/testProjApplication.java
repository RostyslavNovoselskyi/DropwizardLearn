package testProj;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import io.dropwizard.Application;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.hibernate.SessionFactory;
import org.mapstruct.factory.Mappers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import testProj.api.UserApi;
import testProj.core.KafkaProducerService;
import testProj.core.UserMapper;
import testProj.core.UserService;
import testProj.db.UserDao;
import testProj.db.UserDynamoDao;
import testProj.db.UserEntity;
import testProj.db.UserEntityDao;
import testProj.health.TemplateHealthCheck;
import testProj.resources.KafkaProducerResource;
import testProj.resources.UserResource;
import zone.dragon.dropwizard.HK2Bundle;

import javax.inject.Singleton;

public class testProjApplication extends Application<testProjConfiguration> {

    private static final Logger logger = LoggerFactory.getLogger(testProjApplication.class);

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

        final AmazonDynamoDB amazonDynamoDB = AmazonDynamoDBClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(
                        new BasicAWSCredentials(
                                configuration.getAwsConfig().getKey(),
                                configuration.getAwsConfig().getSecret())
                ))
                .withRegion(configuration.getAwsConfig().getRegion())
                .build();

        DynamoDBMapper mapper = new DynamoDBMapper(amazonDynamoDB);

        environment
                .jersey()
                .register(
                        new AbstractBinder() {
                            @Override
                            protected void configure() {
                                bind(UserService.class).to(UserApi.class).in(Singleton.class);
                                bind(mapper).to(DynamoDBMapper.class);
                                bindAsContract(UserDynamoDao.class).in(Singleton.class);
                                bindAsContract(UserEntityDao.class).in(Singleton.class);
                                bind(Mappers.getMapper(UserMapper.class)).to(UserMapper.class);
                                bind(hibernate.getSessionFactory()).to(SessionFactory.class);
                            }
                        }
                );
        environment.jersey().register(UserResource.class);

        logger.info("Registering RESTful API resources");
        environment.jersey().register(new KafkaProducerResource());
        environment.healthChecks().register("DropwizardKafkaProducerHealthCheck",
                new TemplateHealthCheck());

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