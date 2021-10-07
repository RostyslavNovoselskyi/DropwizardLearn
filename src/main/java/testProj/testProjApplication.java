package testProj;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import testProj.core.UserService;
import testProj.health.TemplateHealthCheck;
import testProj.resources.UserResource;

public class testProjApplication extends Application<testProjConfiguration> {
    private final UserService userService = new UserService();

    public static void main(final String[] args) throws Exception {
        new testProjApplication().run(args);
    }

    @Override
    public String getName() {
        return "Hello-World";
    }

    @Override
    public void initialize(final Bootstrap<testProjConfiguration> bootstrap) {
        // TODO: application initialization
    }

    @Override
    public void run(final testProjConfiguration configuration,
                    final Environment environment) {
        final UserResource resource = new UserResource(
                userService
        );
        final TemplateHealthCheck healthCheck = new TemplateHealthCheck();

        environment.healthChecks().register("template", healthCheck);
        environment.jersey().register(resource);
    }

}
