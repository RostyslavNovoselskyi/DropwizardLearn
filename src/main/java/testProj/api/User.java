package testProj.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import java.util.UUID;

@Builder
public class User {
    private UUID userId;

    private String name;

    public User() {
    }

    public User(UUID userId, String name) {
        this.userId = userId;
        this.name = name;
    }

    public User(User user) {
        this.userId = user.getUserId();
        this.name = user.getName();
    }

    @JsonProperty
    public UUID getUserId(){
        return userId;
    }

    @JsonProperty
    public String getName() {
        return name;
    }
}
