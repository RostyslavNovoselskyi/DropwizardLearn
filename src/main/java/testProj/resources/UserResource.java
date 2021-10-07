package testProj.resources;

import testProj.api.User;
import testProj.api.UserApi;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.Optional;
import java.util.UUID;

@Path("/user")
@Produces(MediaType.APPLICATION_JSON)
public class UserResource {
    private final UserApi userApi;

    @Inject
    public UserResource(UserApi userApi) {
        this.userApi = userApi;
    }

    @GET
    public Optional<User> getUser(@QueryParam("id") UUID id){
        return userApi.getUser(id);
    }
}
