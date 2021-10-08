package testProj.core;

import testProj.api.User;
import testProj.api.UserApi;

import java.util.Optional;
import java.util.UUID;

public class UserService implements UserApi {

    @Override
    public Optional<User> getUser(UUID uuid) {
        return Optional.of(
                User.builder()
                        .id(uuid)
                        .name("John Doe")
                        .build()
        );
    }
}
