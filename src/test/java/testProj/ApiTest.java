package testProj;

import feign.Feign;
import feign.jackson.JacksonDecoder;
import feign.optionals.OptionalDecoder;
import io.dropwizard.testing.ResourceHelpers;
import io.dropwizard.testing.junit.DropwizardAppRule;
import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Test;
import testProj.api.User;
import testProj.api.UserApi;

import java.util.Optional;
import java.util.UUID;

public class ApiTest {

    @ClassRule
    public static final DropwizardAppRule<testProjConfiguration> RULE =
            new DropwizardAppRule<>(
                    testProjApplication.class, ResourceHelpers.resourceFilePath("it-config.yml")
            );

    @Test
    public void loginHandlerRedirectsAfterPost() {
        UserApi userApi = Feign.builder()
                .decoder(new OptionalDecoder(new JacksonDecoder()))
                .target(UserApi.class, "http://localhost:8080");

        UUID uuid = UUID.fromString("0d6948b9-a1d0-4c1c-83a2-d146a3b3848b");
//        Optional<User> response = userApi.getUser(uuid);
//
//        response.ifPresent(user -> Assert.assertEquals("John Doe", user.getName()));
    }

}
