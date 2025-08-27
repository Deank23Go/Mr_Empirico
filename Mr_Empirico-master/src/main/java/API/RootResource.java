package API;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/")
public class RootResource {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public Response getRoot() {
        return Response.ok("Sistema de Gestión de Mantenimiento de Equipos - API REST activa")
                .build();
    }
}
