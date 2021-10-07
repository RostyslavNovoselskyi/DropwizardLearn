package testProj.resources;

import lombok.AllArgsConstructor;
import testProj.api.User;
import testProj.core.UserService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.Optional;
import java.util.UUID;

@AllArgsConstructor
@Path("/user")
@Produces(MediaType.APPLICATION_JSON)
public class UserResource {
    @Inject
     UserService userService;

    @GET
    public Optional<User> getUser(@QueryParam("id") UUID id){
        return userService.getUser(id);
    }
}
