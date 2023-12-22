package jax_ws;

import feign.Response;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/notifications")
public class NotificationsResource {

    @GET
    @Path(("/ping"))
    public Response ping() {
        return Response.builder().build();
    }

    @GET
    @Path("/get/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getNotification(@PathParam("id") int id) {
        return Response.builder().status(200).build();
    }
}
