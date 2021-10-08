package testProj;

import feign.Feign;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import feign.optionals.OptionalDecoder;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.glassfish.hk2.utilities.binding.AbstractBinder;
import testProj.api.User;
import testProj.api.UserApi;
import testProj.configuration.testProjConfiguration;
import testProj.core.UserService;
import testProj.health.TemplateHealthCheck;
import testProj.resources.UserResource;
import zone.dragon.dropwizard.HK2Bundle;

import javax.inject.Singleton;
import java.util.Optional;
import java.util.UUID;

public class testProjApplication extends Application<testProjConfiguration> {

    public static void main(final String[] args) throws Exception {

        new testProjApplication().run(args);

        UserApi userApi = Feign.builder()
                .decoder(new OptionalDecoder(new JacksonDecoder()))
                .target(UserApi.class, "http://localhost:8080");


        UUID uuid = UUID.fromString("0d6948b9-a1d0-4c1c-83a2-d146a3b3848c");
//        userApi.getUser(uuid);

        Optional<User> user = userApi.getUser(uuid);
        user.ifPresent(value -> System.out.println("" + value));
//        System.out.println(userApi);
    }

    @Override
    public String getName() {
        return "Hello-World";
    }

    @Override
    public void initialize(final Bootstrap<testProjConfiguration> bootstrap) {
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
                        }
                    }
            );
        environment.jersey().register(UserResource.class);

        final TemplateHealthCheck healthCheck = new TemplateHealthCheck();
        environment.healthChecks().register("ApiHealthCheck", healthCheck);
    }
}