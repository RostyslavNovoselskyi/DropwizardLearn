package testProj;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class testProjApplication extends Application<testProjConfiguration> {

    public static void main(final String[] args) throws Exception {
        new testProjApplication().run(args);
    }

    @Override
    public String getName() {
        return "testProj";
    }

    @Override
    public void initialize(final Bootstrap<testProjConfiguration> bootstrap) {
        // TODO: application initialization
    }

    @Override
    public void run(final testProjConfiguration configuration,
                    final Environment environment) {
        // TODO: implement application
    }

}
