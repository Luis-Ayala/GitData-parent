
package com.layala.gitdata.indicadores;

import com.layala.gitdata.entidades.Incidencia;
import com.layala.gitdata.excepciones.GitDataIncidenciaExcepcion;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.egit.github.core.service.IssueService;

/**
 * Retorna una lista de las incidencias no resueltas, 
 * las incidencias no resueltas son todas aquellas que no tienen el estado
 * de close.
 * 
 * @author Luis Ayala
 * @version 1.0
 * @since 1.0
 */
public class Indicador91 {
    
    private static final Logger LOGGER = LogManager.getLogger(Indicador91.class);

    /**
     * Regresa las incidencias que tienen el estado como cerradas "closed"
     * 
     * @param incidencias Lista de inciencias de las cuales se quiere saber las
     *        que tienen el estado de cerradas.
     * @return Lista de incidencias cerradas.
     * @throws com.layala.gitdata.excepciones.GitDataIncidenciaExcepcion
     */
    public List<Incidencia> getIndicenciasNoResueltas(final List<Incidencia> incidencias) throws GitDataIncidenciaExcepcion{
        LOGGER.info("Entrando al método getIndicenciasNoResueltas.");
        
        if(incidencias == null) {
            throw new GitDataIncidenciaExcepcion("La lista no puedde ser nula.", new IllegalArgumentException());
        }
        
        final List<Incidencia> cerradas = incidencias
                .stream()
                .filter(i -> IssueService.STATE_CLOSED.equals(i.getEstado()))
                .collect(Collectors.toList());
        
        cerradas.sort((Incidencia i1, Incidencia i2) ->
            i1.getCreadaEn().compareTo(i2.getCreadaEn())
        );
        LOGGER.info("Método getIndicenciasNoResueltas finalizado.");
        return cerradas;
    }
}
