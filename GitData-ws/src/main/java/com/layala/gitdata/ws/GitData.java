package com.layala.gitdata.ws;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import java.io.IOException;
import java.net.URI;

/**
 * Clase de inicio para los servicios web, se especifica la ruta base de los
 * servicios y el paquete donde se alojan, posteriormente se inicia el servidor
 * embebido.
 * 
 * @author Luis Ayala
 * @version 1.0
 * @since 1.0
 */
public class GitData {
    public static final String BASE_URI = "http://localhost:8080/gitdata/";

    /**
     * Inicia el servidor embebido Grizzly HTTP y expone los servicios web.
     * 
     * @return Grizzly HTTP server.
     */
    public static HttpServer inicializarServidor() {
        final ResourceConfig rc = new ResourceConfig().packages("com.layala.gitdata.resources");
        return GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI), rc);
    }

    /**
     * Inicio de la aplicación inicializa el servidor Grizzly HTTP.
     * 
     * @param args
     * @throws IOException 
     */
    public static void main(String[] args) throws IOException {
        final HttpServer server = inicializarServidor();
        final String mensaje = "GitData app inicializada, WADL dispnible en "
                                + "%sapplication.wadl\nEnter para detener la aplicación...";
        final String info = String.format(mensaje, BASE_URI);
        System.out.println(info);
        System.in.read();
        server.stop();
    }
}

