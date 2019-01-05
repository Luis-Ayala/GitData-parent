package com.layala.gitdata.aplicativos;

import com.layala.gitdata.entidades.Incidencia;
import com.layala.gitdata.entidades.Repositorio;
import com.layala.gitdata.excepciones.GitDataIncidenciaExcepcion;
import com.layala.gitdata.excepciones.GitDataRepositorioExcepcion;
import com.layala.gitdata.servicios.IncidenciaSrv;
import com.layala.gitdata.servicios.RepositorioSrv;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.*;
 
/**
 * Aplicación para la prueba de concepto
 * 
 * @author Luis Ayala
 * @version 1.0
 * @since 1.0
 */
public class App {
    private static final Logger LOGGER = LogManager.getLogger(App.class);
    
    /**
     * Método de entrada de la aplicación
     * 
     * @param args Argumentos para el método
     * @throws IOException
     * @throws GitDataRepositorioExcepcion
     * @throws GitDataIncidenciaExcepcion 
     */
    public static void main(String... args) throws IOException, GitDataRepositorioExcepcion, GitDataIncidenciaExcepcion {
        try {
            LOGGER.trace("Entrando en la aplicación");
            final RepositorioSrv repoSrv = new RepositorioSrv();
            final List<Repositorio> repositorios = repoSrv.getRepositorios();
            final IncidenciaSrv incidenciaSrv = new IncidenciaSrv();
            final List<Incidencia> total = new ArrayList<>();
            
            repositorios.forEach(r -> {
                try {
                    List<Incidencia> incidencias = incidenciaSrv.getIncidenciasPorRepositorio(r);
                    total.addAll(incidencias);
                } catch (GitDataIncidenciaExcepcion ex) {
                }
                
            });
            incidenciaSrv.insertarIncidencia(total);
            repoSrv.insertarRepositorio(repositorios);
        } catch (GitDataIncidenciaExcepcion ex) {
            LOGGER.error(ex);
        }
    }
}
