package testProj.health;

import com.codahale.metrics.health.HealthCheck;

import java.util.UUID;

public class TemplateHealthCheck extends HealthCheck {

    public TemplateHealthCheck(UUID userId, String defaultName) {

    }


    @Override
    protected Result check() throws Exception {
        return null;
    }
}
