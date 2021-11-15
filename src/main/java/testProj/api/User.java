package testProj.api;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.jackson.Jacksonized;

import java.util.UUID;

@Jacksonized
@Builder(toBuilder = true)
@Getter
@Setter
public class User {
    private final UUID id;

    private final String name;
}
