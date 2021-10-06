package testProj.api;

import java.util.Optional;
import java.util.UUID;

public interface UserApi {
    Optional<User> getUser(UUID userId);
}
