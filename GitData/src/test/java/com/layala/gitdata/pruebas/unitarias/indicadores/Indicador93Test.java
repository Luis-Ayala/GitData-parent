package com.layala.gitdata.pruebas.unitarias.indicadores;

import com.layala.gitdata.entidades.Incidencia;
import com.layala.gitdata.excepciones.GitDataIncidenciaExcepcion;
import com.layala.gitdata.indicadores.Indicador93;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.egit.github.core.service.IssueService;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItem;
import org.hamcrest.Matchers;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.rules.ExpectedException;

/**
 * Prueba unitaria para el indicador 9.3
 * Se evaluan diversos casos que se pueden presentar.
 * 1.- Si el parámetro es nulo, el método debe lanzar una excepcion de tipo GitDataIncidenciaExcepcion
 * 2.- Si se pasa una lista vacia de incidencias entonces el método debe regresar una lista vacia
 * 3.- Si se pasa una lista de incidencias que no tiene incidencias cerradas entonces el método debe 
 *     regresar una lista de incidencias vacia.
 * 4.- Si se pasa una lista con incidencias que al menos una de ellas cerradas entonces
 *     debe regresar una lista de incidencias con el campo Indicador93 diferente a cero.
 * 
 * @author Luis Ayala
 * @version 1.0
 * @since 1.0
 */
public class Indicador93Test {
    private static final Logger LOGGER = LogManager.getLogger(Indicador93Test.class);
    
    @Rule
    public ExpectedException excepcion = ExpectedException.none();
    
    public Indicador93Test() {
    }
    
    /**
     * Prueba para el método getTiempoResolucion, de la clase Indicador93.
     * Esta prueba verifica que se lance la excepcion cuando se le pasa null al
     * parámetro del método.
     * 
     * @throws GitDataIncidenciaExcepcion 
     */
    @Test
    public void getTiempoResolucionExcepcionTest() throws GitDataIncidenciaExcepcion {
        LOGGER.info("Entrando al método getTiempoResolucionExcepcionTest.");
        
        final Indicador93 indicador = new Indicador93();
        final List<Incidencia> incidencias = null;
        
        excepcion.expect(GitDataIncidenciaExcepcion.class);
        excepcion.expectMessage("La lista no puedde ser nula.");
        
        indicador.getTiempoResolucion(incidencias);
        LOGGER.info("Método getTiempoResolucionExcepcionTest finalizado.");
    }
    
    /**
     * Prueba para el método getTiempoResolucion de la clase Indicador93. 
     * Esta prueba verifica que el resultado sea una lista vacia cuando el paramétro que recibe
     * sea una lista vacia.
     * 
     * @throws GitDataIncidenciaExcepcion 
     */
    @Test
    public void getTiempoResolucionResultadoVacioTest() throws GitDataIncidenciaExcepcion {
        LOGGER.info("Entrando al método getTiempoResolucionResultadoVacioTest.");
        
        final Indicador93 indicador = new Indicador93();
        final List<Incidencia> incidencias = new ArrayList<>();
        
        final List<Incidencia> resultado = indicador.getTiempoResolucion(incidencias);
        
        assertTrue(resultado.isEmpty());
        LOGGER.info("Método getTiempoResolucionResultadoVacioTest finalizado.");
    }
    
    /**
     * Prueba para el método getTiempoResolucion de la clase Indicador93. 
     * Esta prueba verifica que el resultado sea una lista vacia, cuando
     * se le pasa una lista de incidencias y ninguna de ellas tenga el estado de closed.
     * 
     * @throws GitDataIncidenciaExcepcion 
     */
    @Test
    public void getTiempoResolucionResultadoNoEncontradoTest() throws GitDataIncidenciaExcepcion {
        LOGGER.info("Entrando al método getTiempoResolucionResultadoNoEncontradoTest.");
        
        final Indicador93 indicador = new Indicador93();
        final List<Incidencia> incidencias = new ArrayList<>();
        final Incidencia incidencia1 = new Incidencia();
        final Incidencia incidencia2 = new Incidencia();
        
        incidencia1.setIncidenciaId(1);
        incidencia1.setCreadaEn(new Date(2018, 12, 30));
        incidencia1.setEstado(IssueService.STATE_OPEN);
        
        incidencia2.setIncidenciaId(2);
        incidencia2.setCreadaEn(new Date(2018, 12, 30));
        incidencia1.setEstado(IssueService.STATE_OPEN);
        
        incidencias.add(incidencia1);
        incidencias.add(incidencia2);
        final List<Incidencia> resultado = indicador.getTiempoResolucion(incidencias);
        
        assertTrue(resultado.isEmpty());
        LOGGER.info("Método getTiempoResolucionResultadoNoEncontradoTest finalizado.");
    }
    
    /**
     * Prueba para el método getTiempoResolucion de la clase Indicador93. 
     * Esta prueba verifica que la lista de resultado no sea una lista vacia, 
     * que la incidencia que contiene el estado de closed tenga en el campo indicador93
     * el valor de 720 horas y que el tamaño de la lista sea uno.
     * 
     * @throws GitDataIncidenciaExcepcion 
     */
    @Test
    public void getTiempoResolucionResultadoEncontradoTest() throws GitDataIncidenciaExcepcion {
        LOGGER.info("Entrando al método getTiempoResolucionResultadoEncontradoTest.");
        
        final Indicador93 indicador = new Indicador93();
        final List<Incidencia> incidencias = new ArrayList<>();
        final Incidencia incidencia1 = new Incidencia();
        
        incidencia1.setIncidenciaId(1);
        incidencia1.setCreadaEn(new Date(2018, 12, 31));
        incidencia1.setCerradaEn(new Date(2019, 1, 30));
        incidencia1.setEstado(IssueService.STATE_CLOSED);
        
        incidencias.add(incidencia1);
        final List<Incidencia> resultado = indicador.getTiempoResolucion(incidencias);
        
        assertFalse(resultado.isEmpty());
        assertTrue(resultado.size() == 1);
        assertThat(resultado, hasItem(Matchers.<Incidencia>hasProperty("indicador93", equalTo(720L))));
        LOGGER.info("Método getTiempoResolucionResultadoEncontradoTest finalizado.");
    }
}
