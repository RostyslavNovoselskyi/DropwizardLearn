package testProj.api;

import lombok.Builder;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;

import java.util.UUID;

@Jacksonized
@Builder(toBuilder = true)
@Getter
public class User {
    private final UUID id;

    private final String name;
}
