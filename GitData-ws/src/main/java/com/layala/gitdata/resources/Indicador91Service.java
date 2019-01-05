package com.layala.gitdata.resources;

import com.google.gson.Gson;
import com.layala.gitdata.entidades.Incidencia;
import com.layala.gitdata.entidades.Repositorio;
import com.layala.gitdata.excepciones.GitDataIncidenciaExcepcion;
import com.layala.gitdata.excepciones.GitDataRepositorioExcepcion;
import com.layala.gitdata.indicadores.Indicador91;
import com.layala.gitdata.servicios.IncidenciaSrv;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import com.layala.gitdata.servicios.RepositorioSrv;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

/**
 * Clase que maneja el servicio <code>/indicador91/{repositorio}</code>
 * 
 * @author Luis Ayala
 * @version 1.0
 * @since 1.0
 */
@Path("/indicador91/{repositorio}")
public class Indicador91Service {

    /**
     * Método que gestiona una petición GET. El objeto de retorno es un objeto 
     * tipo Json Array que contiene las incidencias cerradas del repositorio.
     *
     * @param repositorio
     * @return Json Array de incidencias cerradas.
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getIndicador91(@PathParam("repositorio") String repositorio) {
        final RepositorioSrv servicio = new RepositorioSrv();
        final IncidenciaSrv incidenciaSrv = new IncidenciaSrv();
        List<Incidencia> indicador = new ArrayList<>();
        
        try {
            final List<Repositorio> repositorios = servicio.getRepositorios();
            Repositorio encontrado = repositorios
                    .stream()
                    .filter(r -> r.getNombre().equals(repositorio))
                    .findAny()
                    .orElseThrow(() -> new GitDataRepositorioExcepcion("Repositorio no encontrado."));

            final List<Incidencia> incidencias1 = incidenciaSrv.getIncidenciasCerradasPorRepositorio(encontrado);
            final List<Incidencia> incidencias2 = incidenciaSrv.getIncidenciasPorRepositorio(encontrado);
            
            incidencias1.addAll(incidencias2);
            Indicador91 indicador91 = new Indicador91();
            indicador = indicador91.getIndicenciasNoResueltas(incidencias1);
        } catch (GitDataRepositorioExcepcion | GitDataIncidenciaExcepcion e) {
            throw new WebApplicationException(Response.status(Status.BAD_REQUEST)
                    .entity(e.getMessage())
                    .build());
        }
        
        String json = new Gson().toJson(indicador);
        return Response.ok(json).build();
    }
}
