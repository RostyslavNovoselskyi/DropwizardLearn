package testProj.api;

import feign.Param;
import feign.RequestLine;

import java.util.Optional;
import java.util.UUID;

public interface UserApi {
    @RequestLine("GET /user?id={id}")
    Optional<User> getUser(@Param("id") UUID id);
}
