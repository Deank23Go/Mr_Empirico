package Config;

import jakarta.ws.rs.ApplicationPath;
import org.glassfish.jersey.server.ResourceConfig;

@ApplicationPath("/api")
public class MantenimientoApplication extends ResourceConfig {
    public MantenimientoApplication() {
        packages("api"); // Escanea el paquete "api" en busca de recursos REST
        register(org.glassfish.jersey.jackson.JacksonFeature.class); // Habilita soporte JSON
    }
}