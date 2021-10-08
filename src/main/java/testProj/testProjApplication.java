package testProj;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.glassfish.hk2.utilities.binding.AbstractBinder;
import testProj.api.UserApi;
import testProj.core.UserService;
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