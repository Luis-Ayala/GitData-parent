package com.layala.gitdata.pruebas.unitarias.indicadores;

import com.layala.gitdata.entidades.Incidencia;
import com.layala.gitdata.excepciones.GitDataIncidenciaExcepcion;
import com.layala.gitdata.indicadores.Indicador91;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.egit.github.core.service.IssueService;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.junit.Rule;
import org.junit.rules.ExpectedException;

/**
 * Prueba unitaria para el indicador 9.1
 * Se evaluan diversos casos que se pueden presentar.
 * 1.- Si el parámetro es nulo, el método debe lanzar una excepcion de tipo GitDataIncidenciaExcepcion
 * 2.- Si se pasa una lista vacia de incidencias entonces el método debe regresar una lista vacia
 * 3.- Si se pasa una lista de incidencias que no tienen el estado en closed entonces el método debe 
 *     regresar una lista vacía.
 * 4.- Si se pasa una lista con incidencias que almenos una de ellas tiene el estado de closed entonces
 *     debe regresar una lista de tamaño 1
 * 
 * @author Luis Ayala
 * @version 1.0
 * @since 1.0
 */
public class Indicador91Test {
    private static final Logger LOGGER = LogManager.getLogger(Indicador91Test.class);
    
    public Indicador91Test() {
    }
    
    @Rule
    public ExpectedException excepcion = ExpectedException.none();


    /**
     * Prueba para el método getIndicenciasNoResueltas, de la clase Indicador91.
     * Esta prueba verifica que se lance la excepcion cuando se le pasa null al
     * parámetro del método.
     * 
     * @throws com.layala.gitdata.excepciones.GitDataIncidenciaExcepcion
     */
    @Test
    public void getIndicenciasNoResueltasExcepcionTest() throws GitDataIncidenciaExcepcion {
        LOGGER.info("Entrando al método getIndicenciasNoResueltasExcepcionTest.");
        
        final Indicador91 indicador = new Indicador91();
        final List<Incidencia> incidencias = null;
        
        excepcion.expect(GitDataIncidenciaExcepcion.class);
        excepcion.expectMessage("La lista no puedde ser nula.");
        
        indicador.getIndicenciasNoResueltas(incidencias);
        LOGGER.info("Método getIndicenciasNoResueltasExcepcionTest finalizado.");
    }
    
    /**
     * Prueba para el método getIndicenciasNoResueltas, de la clase Indicador91. 
     * Esta prueba verifica que el resultado sea una lista vacia cuando el paramétro que recibe
     * sea una lista vacia.
     * 
     * @throws GitDataIncidenciaExcepcion 
     */
    @Test
    public void getIndicenciasNoResueltasResultadoVacioTest() throws GitDataIncidenciaExcepcion {
        LOGGER.info("Entrando al método getIndicenciasNoResueltasResultadoVacioTest.");
        
        final Indicador91 indicador = new Indicador91();
        final List<Incidencia> incidencias = new ArrayList<>();
        
        final List<Incidencia> resultado = indicador.getIndicenciasNoResueltas(incidencias);
        
        assertTrue(resultado.isEmpty());
        LOGGER.info("Método getIndicenciasNoResueltasResultadoVacioTest finalizado.");
    }
    
    /**
     * Prueba para el método getIndicenciasNoResueltas, de la clase Indicador91. 
     * Esta prueba verifica que el resultado sea una lista vacia cuando el parámetro que recibe
     * sea una lista de incidencias que no tienen el estado de closed.
     * 
     * @throws GitDataIncidenciaExcepcion 
     */
    @Test
    public void getIndicenciasNoResueltasResultadoNoEncontradoTest() throws GitDataIncidenciaExcepcion {
        LOGGER.info("Entrando al método getIndicenciasNoResueltasResultadoNoEncontradoTest.");
        
        final Indicador91 indicador = new Indicador91();
        final List<Incidencia> incidencias = new ArrayList<>();
        final Incidencia incidencia1 = new Incidencia();
        final Incidencia incidencia2 = new Incidencia();
        
        incidencias.add(incidencia1);
        incidencias.add(incidencia2);
        final List<Incidencia> resultado = indicador.getIndicenciasNoResueltas(incidencias);
        
        assertTrue(resultado.isEmpty());
        LOGGER.info("Método getIndicenciasNoResueltasResultadoNoEncontradoTest finalizado.");
    }
    
    /**
     * Prueba para el método getIndicenciasNoResueltas, de la clase Indicador91. 
     * Esta prueba verifica que el resultado sea una lista NO vacia cuando el parámetro que recibe
     * sea una lista de incidencias que al menos una de ellas tenga el estado de closed.
     * 
     * @throws GitDataIncidenciaExcepcion 
     */
    @Test
    public void getIndicenciasNoResueltasResultadoEncontradoTest() throws GitDataIncidenciaExcepcion {
        LOGGER.info("Entrando al método getIndicenciasNoResueltasResultadoEncontradoTest.");
        
        final Indicador91 indicador = new Indicador91();
        final List<Incidencia> incidencias = new ArrayList<>();
        final Incidencia incidencia1 = new Incidencia();
        final Incidencia incidencia2 = new Incidencia();
        
        incidencia1.setEstado(IssueService.STATE_CLOSED);
        incidencia1.setCreadaEn(new Date());
        
        incidencia2.setEstado(IssueService.STATE_OPEN);
        incidencia2.setCreadaEn(new Date());
        
        incidencias.add(incidencia1);
        incidencias.add(incidencia2);
        final List<Incidencia> resultado = indicador.getIndicenciasNoResueltas(incidencias);
        
        assertTrue(resultado.size() == 1);
        LOGGER.info("Método getIndicenciasNoResueltasResultadoEncontradoTest finalizado.");
    }
}
