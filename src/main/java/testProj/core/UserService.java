package testProj.core;

import testProj.api.User;
import testProj.api.UserApi;

import java.util.Optional;
import java.util.UUID;

public class UserService implements UserApi {

    @Override
    public Optional<User> getUser() {
        return Optional.of(
                User.builder()
                        .userId(UUID.randomUUID())
                        .name("John Doe")
                        .build()
        );
    }
}
