package testProj;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import javax.validation.constraints.NotEmpty;
import java.util.UUID;

public class testProjConfiguration extends Configuration {
    private UUID userId;

    private String defaultName;

    @JsonProperty
    public UUID getUserId() {
        return userId;
    }

    @JsonProperty
    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    @JsonProperty
    public String getDefaultName() {
        return defaultName;
    }

    @JsonProperty
    public void setDefaultName(String name) {
        this.defaultName = name;
    }
}
