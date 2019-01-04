
package com.layala.gitdata.indicadores;

import com.layala.gitdata.entidades.Comentario;
import com.layala.gitdata.entidades.Incidencia;
import com.layala.gitdata.excepciones.GitDataIncidenciaExcepcion;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Retorna una lista de las incidencias en donde cada incidencia tiene el tiempo
 * transcurrido entre la fecha en que se creó la incidencia y el primer comentario
 * de la incidencia.
 * 
 * El tiempo se almacena en el campo Indicador92 de la incidencia y se expresa
 * en horas.
 * 
 * @author Luis Ayala
 * @version 1.0
 * @since 1.0
 */
public class Indicador92 {
    
    private static final Logger LOGGER = LogManager.getLogger(Indicador92.class);
    
    /**
     * Regresa las incidencias con el tiempo de respuesta inicial
     * El tiempo se expresa en horas.
     * 
     * @param incidencias Lista de incidencias 
     * @return Lista de incidencias con el tiempo de respuesta inicial
     * @throws GitDataIncidenciaExcepcion 
     */
    public List<Incidencia> getTiempoRespuestaInicial(final List<Incidencia> incidencias) throws GitDataIncidenciaExcepcion {
        LOGGER.info("Entrando al método getTiempoRespuestaInicial.");
        
        if(incidencias == null) {
            throw new GitDataIncidenciaExcepcion("La lista no puedde ser nula.", new IllegalArgumentException());
        }
        
        final List<Incidencia> incidenciasLista = new ArrayList<>(incidencias.size());
        incidenciasLista.addAll(incidencias);
        
        incidenciasLista.stream().forEach(incidencia -> {
            final List<Comentario> comentarios = incidencia.getComentarios();
            comentarios.sort((Comentario c1, Comentario c2) -> c1.getCreadoEn().compareTo(c2.getCreadoEn()));
            final Optional<Comentario> comentario = comentarios.stream().findFirst();
            
            if(comentario.isPresent()) {
                final LocalDateTime ldtIncidencia = incidencia
                        .getCreadaEn()
                        .toInstant()
                        .atZone(ZoneId.systemDefault())
                        .toLocalDateTime();
                
                final LocalDateTime ldtComentario = comentario
                        .get()
                        .getCreadoEn()
                        .toInstant()
                        .atZone(ZoneId.systemDefault())
                        .toLocalDateTime();
                
                Duration tiempo = Duration.between(ldtIncidencia, ldtComentario);
                incidencia.setIndicador92(tiempo.abs().toHours());
            }
        });
        LOGGER.info("Método getTiempoRespuestaInicial finalizado.");
        return incidenciasLista;
    }
}
