package testProj.resources;

//import com.example.helloworld.api.Saying;
import testProj.api.User;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

@Path("/hello-user")
@Produces(MediaType.APPLICATION_JSON)
public class HelloUserResource {
    private final UUID id;
    private final String defaultName;
    private final AtomicLong counter;

    public HelloUserResource(UUID id, String defaultName) {
        this.id = id;
        this.defaultName = defaultName;
        this.counter = new AtomicLong();
    }

    @GET
    public User getUser(){
        return new User(
                User.builder()
                .userId(UUID.randomUUID())
                .name("John Doe").build()
        );
    }
}
