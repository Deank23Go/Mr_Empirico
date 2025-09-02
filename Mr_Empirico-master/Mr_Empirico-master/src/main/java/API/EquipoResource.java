package API;

import Model.Equipo;
import repository.EquipoRepository;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

@Path("/equipos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EquipoResource {
    private EquipoRepository equipoRepository = new EquipoRepository();

    @GET
    public List<Equipo> getAllEquipos() {
        return equipoRepository.listarEquipos();
    }

    @GET
    @Path("/{id}")
    public Response getEquipo(@PathParam("id") int id) {
        return equipoRepository.buscarPorId(id)
                .map(equipo -> Response.ok(equipo).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    @POST
    public Response createEquipo(Equipo equipo) {
        try {
            equipoRepository.agregarEquipo(equipo);
            return Response.status(Response.Status.CREATED).entity(equipo).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error al crear el equipo: " + e.getMessage()).build();
        }
    }

    @PUT
    @Path("/{id}")
    public Response updateEquipo(@PathParam("id") int id, Equipo equipo) {
        try {
            equipo.setId(id);
            equipoRepository.actualizarEquipo(equipo);
            return Response.ok(equipo).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error al actualizar el equipo: " + e.getMessage()).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deleteEquipo(@PathParam("id") int id) {
        try {
            equipoRepository.eliminarEquipo(id);
            return Response.noContent().build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error al eliminar el equipo: " + e.getMessage()).build();
        }
    }
}