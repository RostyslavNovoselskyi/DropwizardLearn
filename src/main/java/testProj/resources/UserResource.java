package testProj.resources;

import feign.Param;
import testProj.api.User;
import testProj.api.UserApi;

import javax.inject.Inject;
import javax.ws.rs.*;
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
