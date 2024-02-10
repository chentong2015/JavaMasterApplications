package jax_ws;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/notifications")
public class NotificationsResource {

    @GET
    @Path(("/ping"))
    public String ping() {
        return "response";
    }

    @GET
    @Path("/get/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getNotification(@PathParam("id") int id) {
        return "notification";
    }
}
