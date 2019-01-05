package com.layala.gitdata.indicadores;

import com.layala.gitdata.entidades.Incidencia;
import com.layala.gitdata.excepciones.GitDataIncidenciaExcepcion;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.egit.github.core.service.IssueService;

/**
 * Retorna una lista de incidencias en donde cada incidencia tiene el tiempo
 * que se tomó en resolverla. En este indicador solo se toman en cuenta las
 * incidencias cerradas, es decir, las incidencias que tienen el estado de
 * "closed"
 *
 * El tiempo se almacena en el campo Indicador93 de la incidencia y se expresa
 * en horas.
 *
 * @author Luis Ayala
 * @version 1.0
 * @since 1.0
 */
public class Indicador93 {

    private static final Logger LOGGER = LogManager.getLogger(Indicador93.class);
    
    /**
     * Regresa las incidencias con el tiempo entre la fecha de creación y la
     * fecha de cierre de la incidencia. 
     * El tiempo se expresa en horas.
     * 
     * @param incidencias Lista de incidencias 
     * @return Lista de incidencias con el tiempo transcurrido entre la creación y la fecha de
     * cierre de la incidencia.
     * @throws com.layala.gitdata.excepciones.GitDataIncidenciaExcepcion
     */
    public List<Incidencia> getTiempoResolucion(final List<Incidencia> incidencias) throws GitDataIncidenciaExcepcion {
        LOGGER.info("Entrando al método getTiempoResolucion.");

        if (incidencias == null) {
            throw new GitDataIncidenciaExcepcion("La lista no puedde ser nula.", new IllegalArgumentException());
        }

        final List<Incidencia> cerradas = incidencias
                .stream()
                .filter(i -> IssueService.STATE_CLOSED.equals(i.getEstado()))
                .collect(Collectors.toList());

        cerradas.forEach(i -> {
            final LocalDateTime ldtCreadaEn = i
                    .getCreadaEn()
                    .toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDateTime();

            final LocalDateTime ldtCerradaEn = i
                    .getCerradaEn()
                    .toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDateTime();
            
            Duration tiempo = Duration.between(ldtCreadaEn, ldtCerradaEn);
            i.setIndicador93(tiempo.abs().toHours());
        });
        LOGGER.info("Método getTiempoResolucion finalizado.");
        return cerradas;
    }
}
