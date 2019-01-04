package com.layala.gitdata.aplicativos;

import com.layala.gitdata.entidades.Incidencia;
import com.layala.gitdata.entidades.Repositorio;
import com.layala.gitdata.excepciones.GitDataIncidenciaExcepcion;
import com.layala.gitdata.servicios.IncidenciaSrv;
import java.io.IOException;
import java.util.List;

import org.apache.logging.log4j.*;
 
/**
 * Aplicación de prueba de concepto
 * 
 * @author Luis Ayala
 */
public class App {
    private static final Logger LOGGER = LogManager.getLogger(App.class);
    
    public static void main(String... args) throws IOException {
        try {
            LOGGER.trace("Entrando en la aplicación");
            final Repositorio repositorio = new Repositorio();
            repositorio.setNombre("plotly.js");
            final IncidenciaSrv incidenciaSrv = new IncidenciaSrv();
            
            List<Incidencia> incidencias = incidenciaSrv.getIncidenciasPorRepositorio(repositorio);
            List<Incidencia> cerradas = incidenciaSrv.getIncidenciasCerradasPorRepositorio(repositorio);
            
            incidencias.addAll(cerradas);
            incidenciaSrv.insertarIncidencia(incidencias);
        } catch (GitDataIncidenciaExcepcion ex) {
            LOGGER.error(ex);
        }
    }
}
