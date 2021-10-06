package testProj.resources;

import testProj.api.User;
import testProj.core.UserService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Optional;

@Path("/get-user")
@Produces(MediaType.APPLICATION_JSON)
public class UserResource {
    @Inject
    UserService userService;

    public UserResource(UserService user) {
        this.userService = user;
    }

    @GET
    public Optional<User> getUser(){
        return userService.getUser();
    }
}
