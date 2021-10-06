package testProj.resources;

//import com.example.helloworld.api.Saying;
import testProj.api.User;
import testProj.core.UserService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Optional;

@Path("/hello-user")
@Produces(MediaType.APPLICATION_JSON)
public class HelloUserResource {
    @Inject
    UserService userService;

//    private final User user;

    public HelloUserResource(UserService user) {
        this.userService = user;
    }

//    private final UUID id;
//    private final String defaultName;

//    public HelloUserResource(UUID id, String defaultName) {
//        this.id = id;
//        this.defaultName = defaultName;
//    }

    @GET
    public Optional<User> getUser(){
        return userService.getUser();
//        return new User(
//                User.builder()
//                .userId(UUID.randomUUID())
//                .name("John Doe").build()
//        );
    }
}
