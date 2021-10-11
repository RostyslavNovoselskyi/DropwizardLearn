package testProj.api;

import feign.Param;
import feign.RequestLine;
import testProj.db.UserEntity;

import java.util.Optional;
import java.util.UUID;

public interface UserApi {
    @RequestLine("GET /user?id={id}")
    Optional<UserEntity> getUser(@Param("id") UUID id);

    void createUser(UUID id);
}
