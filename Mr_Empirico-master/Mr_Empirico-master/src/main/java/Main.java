import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import java.net.URI;

public class Main {
    public static final String BASE_URI = "http://localhost:8080/api/";

    public static HttpServer startServer() {
        // Registrar explícitamente todas las clases de recursos
        final ResourceConfig config = new ResourceConfig();
        config.register(API.RootResource.class);
        config.register(API.EquipoResource.class);
        config.register(API.MantenimientoResource.class);
        config.register(org.glassfish.jersey.jackson.JacksonFeature.class);

        return GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI), config);
    }

    public static void main(String[] args)
        try {
            final HttpServer server = startServer();
            System.out.println("==================================================");
            System.out.println("SISTEMA DE GESTIÓN DE MANTENIMIENTO DE EQUIPOS");
            System.out.println("==================================================");
            System.out.println("Servidor API REST iniciado en: " + BASE_URI);
            System.out.println("Endpoints disponibles:");
            System.out.println("• GET  " + BASE_URI + " - Página de inicio");
            System.out.println("• GET  " + BASE_URI + "equipos - Listar todos los equipos");
            System.out.println("• POST " + BASE_URI + "equipos - Crear nuevo equipo");
            System.out.println("• GET  " + BASE_URI + "equipos/{id} - Obtener equipo por ID");
            System.out.println("• PUT  " + BASE_URI + "equipos/{id} - Actualizar equipo");
            System.out.println("• DELETE " + BASE_URI + "equipos/{id} - Eliminar equipo");
            System.out.println("• GET  " + BASE_URI + "mantenimientos - Listar todos los mantenimientos");
            System.out.println("• POST " + BASE_URI + "mantenimientos - Crear nuevo mantenimiento");
            System.out.println("• GET  " + BASE_URI + "mantenimientos/equipo/{id} - Mantenimientos por equipo");
            System.out.println("• GET  " + BASE_URI + "mantenimientos/estado/{estado} - Mantenimientos por estado");
            System.out.println("==================================================");
            System.out.println("Presiona Enter para detener el servidor...");

            // Esperar a que el usuario presione Enter para detener el servidor
            System.in.read();

            System.out.println("Deteniendo el servidor...");
            server.shutdownNow();
            System.out.println("Servidor detenido.");

        } catch (Exception e) {
            System.err.println("Error al iniciar el servidor: " + e.getMessage());
            e.printStackTrace();
        }
    }
}