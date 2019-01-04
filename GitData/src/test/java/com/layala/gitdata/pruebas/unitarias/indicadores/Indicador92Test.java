package com.layala.gitdata.pruebas.unitarias.indicadores;

import com.layala.gitdata.entidades.Comentario;
import com.layala.gitdata.entidades.Incidencia;
import com.layala.gitdata.excepciones.GitDataIncidenciaExcepcion;
import com.layala.gitdata.indicadores.Indicador92;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import static org.hamcrest.CoreMatchers.*;
import org.hamcrest.Matchers;
import org.hamcrest.collection.IsIterableContainingInAnyOrder;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.junit.Assert.assertThat;
import org.junit.Rule;
import org.junit.rules.ExpectedException;

/**
 * Prueba unitaria para el indicador 9.2
 * Se evaluan diversos casos que se pueden presentar.
 * 1.- Si el parámetro es nulo, el método debe lanzar una excepcion de tipo GitDataIncidenciaExcepcion
 * 2.- Si se pasa una lista vacia de incidencias entonces el método debe regresar una lista vacia
 * 3.- Si se pasa una lista de incidencias que no tienen comentarios entonces el método debe 
 *     regresar una lista de incidencias que debe ser igual a la lista que se le pasa.
 * 4.- Si se pasa una lista con incidencias que almenos una de ellas tiene un comentario entonces
 *     debe regresar una lista de incidencias con el campo Indicador92 diferente a cero.
 * 
 * @author Luis Ayala
 * @version 1.0
 * @since 1.0
 */
public class Indicador92Test {
    private static final Logger LOGGER = LogManager.getLogger(Indicador92Test.class);
    
    public Indicador92Test() {
    }
    
    @Rule
    public ExpectedException excepcion = ExpectedException.none();
    
    /**
     * Prueba para el método getTiempoRespuestaInicial, de la clase Indicador92.
     * Esta prueba verifica que se lance la excepcion cuando se le pasa null al
     * parámetro del método.
     * 
     * @throws GitDataIncidenciaExcepcion 
     */
    @Test
    public void getTiempoPrimerComentarioExcepcionTest() throws GitDataIncidenciaExcepcion {
        LOGGER.info("Entrando al método getTiempoPrimerComentarioExcepcionTest.");
        
        final Indicador92 indicador = new Indicador92();
        final List<Incidencia> incidencias = null;
        
        excepcion.expect(GitDataIncidenciaExcepcion.class);
        excepcion.expectMessage("La lista no puedde ser nula.");
        
        indicador.getTiempoRespuestaInicial(incidencias);
        LOGGER.info("Método getTiempoPrimerComentarioExcepcionTest finalizado.");
    }
    
    /**
     * Prueba para el método getTiempoRespuestaInicial, de la clase Indicador92. 
     * Esta prueba verifica que el resultado sea una lista vacia cuando el paramétro que recibe
     * sea una lista vacia.
     * 
     * @throws GitDataIncidenciaExcepcion 
     */
    @Test
    public void getTiempoPrimerComentarioResultadoVacioTest() throws GitDataIncidenciaExcepcion {
        LOGGER.info("Entrando al método getTiempoPrimerComentarioResultadoVacioTest.");
        
        final Indicador92 indicador = new Indicador92();
        final List<Incidencia> incidencias = new ArrayList<>();
        
        final List<Incidencia> resultado = indicador.getTiempoRespuestaInicial(incidencias);
        
        assertTrue(resultado.isEmpty());
        LOGGER.info("Método getTiempoPrimerComentarioResultadoVacioTest finalizado.");
    }
    
    /**
     * Prueba para el método getTiempoRespuestaInicial, de la clase Indicador92. 
     * Esta prueba verifica que el resultado sea una lista indética a la lista
     * que se le pasa como parámetro y que en la lista de resultado tenga el 
     * campo indicador92 en cero.
     * 
     * @throws GitDataIncidenciaExcepcion 
     */
    @Test
    public void getTiempoPrimerComentarioResultadoNoEncontradoTest() throws GitDataIncidenciaExcepcion {
        LOGGER.info("Entrando al método getIndicenciasNoResueltasResultadoNoEncontradoTest.");
        
        final Indicador92 indicador = new Indicador92();
        final List<Incidencia> incidencias = new ArrayList<>();
        final Incidencia incidencia1 = new Incidencia();
        final Incidencia incidencia2 = new Incidencia();
        
        incidencia1.setIncidenciaId(1);
        incidencia1.setCreadaEn(new Date(2018, 12, 30));
        incidencia1.setComentarios(new ArrayList<>());
        incidencia1.setIndicador92(0);
        
        incidencia2.setIncidenciaId(2);
        incidencia2.setCreadaEn(new Date(2018, 12, 30));
        incidencia2.setComentarios(new ArrayList<>());
        incidencia2.setIndicador92(0);
        
        incidencias.add(incidencia1);
        incidencias.add(incidencia2);
        final List<Incidencia> resultado = indicador.getTiempoRespuestaInicial(incidencias);
        
        assertThat(resultado, IsIterableContainingInAnyOrder.containsInAnyOrder(incidencias.toArray()));
        assertThat(resultado, hasItem(Matchers.<Incidencia>hasProperty("indicador92", equalTo(0L))));
        
        LOGGER.info("Método getIndicenciasNoResueltasResultadoNoEncontradoTest finalizado.");
    }
    
    /**
     * Prueba para el método getTiempoRespuestaInicial, de la clase Indicador92. 
     * Esta prueba verifica que la lista de resultado no sea una lista vacia, 
     * que sea del mismo tamaño que la lista que se pasa como parámetro y que
     * la incidencia que contiene el comentario tenga en el campo indicador92
     * el valor de 25 horas-
     * 
     * @throws GitDataIncidenciaExcepcion 
     */
    @Test
    public void getTiempoPrimerComentarioResultadoEncontradoTest() throws GitDataIncidenciaExcepcion {
        LOGGER.info("Entrando al método getIndicenciasNoResueltasResultadoEncontradoTest.");
        
        final Indicador92 indicador = new Indicador92();
        final List<Incidencia> incidencias = new ArrayList<>();
        final Incidencia incidencia1 = new Incidencia();
        final Incidencia incidencia2 = new Incidencia();
        
        Comentario comentario = new Comentario();
        comentario.setCreadoEn(new Date(2018, 12, 30, 10, 20, 32));
        
        incidencia1.setIncidenciaId(1);
        incidencia1.setCreadaEn(new Date(2018, 12, 31, 11, 35, 36));
        incidencia1.setComentarios(new ArrayList<>());
        incidencia1.setIndicador92(0);
        incidencia1.setComentarios(Arrays.asList(comentario));
        
        incidencia2.setIncidenciaId(2);
        incidencia2.setCreadaEn(new Date(2018, 12, 30));
        incidencia2.setComentarios(new ArrayList<>());
        incidencia2.setIndicador92(0);
        
        incidencias.add(incidencia1);
        incidencias.add(incidencia2);
        final List<Incidencia> resultado = indicador.getTiempoRespuestaInicial(incidencias);
        
        assertFalse(resultado.isEmpty());
        assertTrue(resultado.size() == 2);
        assertThat(resultado, hasItem(Matchers.<Incidencia>hasProperty("indicador92", equalTo(25L))));
        LOGGER.info("Método getIndicenciasNoResueltasResultadoEncontradoTest finalizado.");
    }
}
