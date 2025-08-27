package API;

import Model.Mantenimiento;
import service.MantenimientoService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

@Path("/mantenimientos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MantenimientoResource {
    private MantenimientoService mantenimientoService = new MantenimientoService();

    @GET
    public List<Mantenimiento> getAllMantenimientos() {
        return mantenimientoService.listarMantenimientos();
    }

    @GET
    @Path("/{id}")
    public Response getMantenimiento(@PathParam("id") int id) {
        Mantenimiento mantenimiento = mantenimientoService.buscarPorId(id);
        if (mantenimiento != null) {
            return Response.ok(mantenimiento).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @POST
    public Response createMantenimiento(Mantenimiento mantenimiento) {
        try {
            mantenimientoService.agregarMantenimiento(mantenimiento);
            return Response.status(Response.Status.CREATED).entity(mantenimiento).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error al crear el mantenimiento: " + e.getMessage()).build();
        }
    }

    @PUT
    @Path("/{id}")
    public Response updateMantenimiento(@PathParam("id") int id, Mantenimiento mantenimiento) {
        try {
            mantenimiento.setId(id);
            mantenimientoService.actualizarMantenimiento(mantenimiento);
            return Response.ok(mantenimiento).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error al actualizar el mantenimiento: " + e.getMessage()).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deleteMantenimiento(@PathParam("id") int id) {
        try {
            mantenimientoService.eliminarMantenimiento(id);
            return Response.noContent().build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error al eliminar el mantenimiento: " + e.getMessage()).build();
        }
    }

    @GET
    @Path("/equipo/{equipoId}")
    public List<Mantenimiento> getMantenimientosByEquipo(@PathParam("equipoId") int equipoId) {
        return mantenimientoService.buscarPorEquipoId(equipoId);
    }

    @GET
    @Path("/estado/{estado}")
    public List<Mantenimiento> getMantenimientosByEstado(@PathParam("estado") String estado) {
        return mantenimientoService.buscarPorEstado(estado);
    }
}