package com.layala.gitdata.pruebas.unitarias.servicios;

import com.layala.gitdata.entidades.Hito;
import com.layala.gitdata.entidades.Incidencia;
import com.layala.gitdata.entidades.PullRequest;
import com.layala.gitdata.entidades.Repositorio;
import com.layala.gitdata.entidades.Usuario;
import com.layala.gitdata.excepciones.GitDataConfigExcepcion;
import com.layala.gitdata.excepciones.GitDataIncidenciaExcepcion;
import com.layala.gitdata.servicios.IncidenciaSrv;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import static junit.framework.Assert.assertEquals;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.egit.github.core.service.IssueService;
import static org.junit.Assert.*;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runners.MethodSorters;

/**
 * Prueba unitaria para la clase de servicio IncidenciaSrv.
 * 
 * Se pueban los método de inserción y de actualización, tanto para los métodos
 * que reciben como parámetro una lista como los métodos que reciben solo una
 * incidencia.
 * <p>
 * Las pruebas se ejecutan en orden alfabético mediante la anotación
 * @FixMethodOrder para que no causen conflicto la ejcución de las pruebas unitarias.
 * 
 * @author Luis Ayala
 * @version 1.0
 * @since 1.0
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class IncidenciaSrvTest {
    private static final Logger LOGGER = LogManager.getLogger(IncidenciaSrvTest.class);
    
    @Rule
    public ExpectedException excepcion = ExpectedException.none();
    
    public IncidenciaSrvTest() {
    }
    
    /**
     * Prueba para el método actualizarIncidencia.
     * Se prueba que el método lance la excepción cuando el parámetro es nulo.
     * 
     * @throws GitDataIncidenciaExcepcion
     * @throws GitDataConfigExcepcion 
     */
    @Test
    public void actualizarIncidenciaExcepcionTest() throws GitDataIncidenciaExcepcion, GitDataConfigExcepcion {
        LOGGER.info("Entrando al método actualizarIncidenciaExcepcionTest.");
        
        final IncidenciaSrv servicio = new IncidenciaSrv();
        final Incidencia incidencia = null;
        
        excepcion.expect(GitDataIncidenciaExcepcion.class);

        servicio.actualizarIncidencia(incidencia);
        LOGGER.info("Método actualizarIncidenciaExcepcionTest finalizado.");
    }
    
    /**
     * Prueba para el método actualizarIncidencia.
     * Se prueba que el método lance la excepción cuando el parámetro es nulo.
     * 
     * @throws GitDataIncidenciaExcepcion
     * @throws GitDataConfigExcepcion 
     */
    @Test
    public void actualizarListaIncidenciaExcepcionTest() throws GitDataIncidenciaExcepcion, GitDataConfigExcepcion {
        LOGGER.info("Entrando al método actualizarListaIncidenciaExcepcionTest.");
        
        final IncidenciaSrv servicio = new IncidenciaSrv();
        final List<Incidencia> incidencias = null;
        
        excepcion.expect(GitDataIncidenciaExcepcion.class);
        excepcion.expectMessage("La lista no puede ser nula");
        
        servicio.actualizarIncidencia(incidencias);
        LOGGER.info("Método actualizarListaIncidenciaExcepcionTest finalizado.");
    }
    
    /**
     * Prueba para el método insertarIncidencia.
     * Se prueba que el método lance la excepción cuando el parámetro es nulo.
     * 
     * @throws GitDataIncidenciaExcepcion 
     */
    @Test
    public void insertarIncidenciaExcepciontest() throws GitDataIncidenciaExcepcion {
        LOGGER.info("Entrando al método insertarIncidenciaExcepciontest.");
        
        final IncidenciaSrv servicio = new IncidenciaSrv();
        final Incidencia incidencia = null;
        
        excepcion.expect(GitDataIncidenciaExcepcion.class);
        excepcion.expectMessage("La incidencia no puede ser nula");
        
        servicio.insertarIncidencia(incidencia);
        LOGGER.info("Método insertarIncidenciaExcepciontest finalizado.");
    }
    
    /**
     * Prueba para el método insertarIncidencia.
     * Se prueba que el método lance la excepción cuando el parámetro es nulo.
     * 
     * @throws GitDataIncidenciaExcepcion 
     */
    @Test
    public void insertarListaIncidenciaExcepcionTest() throws GitDataIncidenciaExcepcion {
        LOGGER.info("Entrando al método insertarListaIncidenciaExcepcionTest.");
        
        final IncidenciaSrv servicio = new IncidenciaSrv();
        final List<Incidencia> incidencias = null;
        
        excepcion.expect(GitDataIncidenciaExcepcion.class);
        excepcion.expectMessage("La lista no puede ser nula");
        servicio.insertarIncidencia(incidencias);
        LOGGER.info("Método insertarListaIncidenciaExcepcionTest finalizado.");
    }
    
    /**
     * Prueba para el método insertarIncidencia.
     * Prueba que se inserte una incidencia en la base de datos.
     * 
     * @throws GitDataIncidenciaExcepcion 
     */
    @Test
    public void insertarIncidenciaTest() throws GitDataIncidenciaExcepcion {
        LOGGER.info("Entrando al método insertarIncidenciaTest.");
        
        final IncidenciaSrv servicio = new IncidenciaSrv();
        final Incidencia incidencia = new Incidencia();
        incidencia.setIncidenciaId(1);
        incidencia.setCerradaEn(new Date());
        incidencia.setCreadaEn(new Date());
        incidencia.setModificadaEn(new Date());
        incidencia.setNumComentarios(0);
        incidencia.setEtiquetas(new ArrayList<>());
        incidencia.setHito(new Hito());
        incidencia.setPullRequest(new PullRequest());
        incidencia.setCuerpo("Test");
        incidencia.setHtmlUrl("Test");
        incidencia.setUrl("Test");
        incidencia.setEstado(IssueService.STATE_OPEN);
        incidencia.setTitulo("Test");
        incidencia.setAsignadoA(new Usuario());
        incidencia.setUsuario(new Usuario());
        incidencia.setRepositorio(new Repositorio());
        incidencia.setComentarios(new ArrayList<>());
        incidencia.setIndicador92(0);
        incidencia.setIndicador93(0);
        incidencia.setNumero(0);
        final long resultado = servicio.insertarIncidencia(incidencia);
        
        assertEquals(resultado, 1L);
        LOGGER.info("Método insertarIncidenciaTest finalizado.");
    }
    
    /**
     * Prueba para el método insertarIncidencia.
     * Prueba que se inserte una lista de incidencias en la base de datos.
     * 
     * @throws GitDataIncidenciaExcepcion 
     */
    @Test
    public void insertarListaIncidenciaTest() throws GitDataIncidenciaExcepcion {
        LOGGER.info("Entrando al método insertarListaIncidenciaTest.");
        
        final IncidenciaSrv servicio = new IncidenciaSrv();
        final Incidencia incidencia1 = new Incidencia();
        final Incidencia incidencia2 = new Incidencia();
        final List<Incidencia> incidencias = new ArrayList<>();
        
        incidencia1.setIncidenciaId(2);
        incidencia1.setCerradaEn(new Date());
        incidencia1.setCreadaEn(new Date());
        incidencia1.setModificadaEn(new Date());
        incidencia1.setNumComentarios(0);
        incidencia1.setEtiquetas(new ArrayList<>());
        incidencia1.setHito(new Hito());
        incidencia1.setPullRequest(new PullRequest());
        incidencia1.setCuerpo("Test");
        incidencia1.setHtmlUrl("Test");
        incidencia1.setUrl("Test");
        incidencia1.setEstado(IssueService.STATE_OPEN);
        incidencia1.setTitulo("Test");
        incidencia1.setAsignadoA(new Usuario());
        incidencia1.setUsuario(new Usuario());
        incidencia1.setRepositorio(new Repositorio());
        incidencia1.setComentarios(new ArrayList<>());
        incidencia1.setIndicador92(0);
        incidencia1.setIndicador93(0);
        incidencia1.setNumero(0);
        
        incidencia2.setIncidenciaId(3);
        incidencia2.setCerradaEn(new Date());
        incidencia2.setCreadaEn(new Date());
        incidencia2.setModificadaEn(new Date());
        incidencia2.setNumComentarios(0);
        incidencia2.setEtiquetas(new ArrayList<>());
        incidencia2.setHito(new Hito());
        incidencia2.setPullRequest(new PullRequest());
        incidencia2.setCuerpo("Test");
        incidencia2.setHtmlUrl("Test");
        incidencia2.setUrl("Test");
        incidencia2.setEstado(IssueService.STATE_OPEN);
        incidencia2.setTitulo("Test");
        incidencia2.setAsignadoA(new Usuario());
        incidencia2.setUsuario(new Usuario());
        incidencia2.setRepositorio(new Repositorio());
        incidencia2.setComentarios(new ArrayList<>());
        incidencia2.setIndicador92(0);
        incidencia2.setIndicador93(0);
        incidencia2.setNumero(0);
        
        incidencias.add(incidencia1);
        incidencias.add(incidencia2);
        
        final long resultado = servicio.insertarIncidencia(incidencias);
        assertEquals(resultado, 2L);
        LOGGER.info("Método insertarListaIncidenciaTest finalizado.");
    }
    
    /**
     * Prueba para el método actualizarIncidencia.
     * Prueba que se actualice una incidencia en la base de datos.
     * 
     * @throws GitDataIncidenciaExcepcion 
     */
    @Test
    public void zactualizarIncidenciaTest() throws GitDataIncidenciaExcepcion {
        LOGGER.info("Entrando al método actualizarIncidenciaTest.");
        final IncidenciaSrv servicio = new IncidenciaSrv();
        final Incidencia incidencia1 = new Incidencia();
        
        incidencia1.setIncidenciaId(2);
        incidencia1.setCerradaEn(new Date());
        incidencia1.setCreadaEn(new Date());
        incidencia1.setModificadaEn(new Date());
        incidencia1.setNumComentarios(0);
        incidencia1.setEtiquetas(new ArrayList<>());
        incidencia1.setHito(new Hito());
        incidencia1.setPullRequest(new PullRequest());
        incidencia1.setCuerpo("Test");
        incidencia1.setHtmlUrl("Test");
        incidencia1.setUrl("Test");
        incidencia1.setEstado(IssueService.STATE_CLOSED);
        incidencia1.setTitulo("Test");
        incidencia1.setAsignadoA(new Usuario());
        incidencia1.setUsuario(new Usuario());
        incidencia1.setRepositorio(new Repositorio());
        incidencia1.setComentarios(new ArrayList<>());
        incidencia1.setIndicador92(0);
        incidencia1.setIndicador93(0);
        incidencia1.setNumero(0);
        
        long resultado = servicio.actualizarIncidencia(incidencia1);
        assertEquals(resultado, 1L);
        LOGGER.info("Método actualizarIncidenciaTest finalizado.");
    }
    
    /**
     * Prueba para el método actualizarIncidencia.
     * Prueba que se actualice una lista de incidencias en la base de datos.
     * 
     * @throws GitDataIncidenciaExcepcion 
     */
    @Test
    public void zzactualizarListaIncidenciaTest() throws GitDataIncidenciaExcepcion {
        LOGGER.info("Entrando al método actualizarListaIncidenciaTest.");
        final IncidenciaSrv servicio = new IncidenciaSrv();
        final Incidencia incidencia1 = new Incidencia();
        final Incidencia incidencia2 = new Incidencia();
        final List<Incidencia> incidencias = new ArrayList<>();
        
        incidencia1.setIncidenciaId(2);
        incidencia1.setCerradaEn(new Date());
        incidencia1.setCreadaEn(new Date());
        incidencia1.setModificadaEn(new Date());
        incidencia1.setNumComentarios(0);
        incidencia1.setEtiquetas(new ArrayList<>());
        incidencia1.setHito(new Hito());
        incidencia1.setPullRequest(new PullRequest());
        incidencia1.setCuerpo("Test modificado");
        incidencia1.setHtmlUrl("Test modificado");
        incidencia1.setUrl("Test modificado");
        incidencia1.setEstado(IssueService.STATE_CLOSED);
        incidencia1.setTitulo("Test modificado");
        incidencia1.setAsignadoA(new Usuario());
        incidencia1.setUsuario(new Usuario());
        incidencia1.setRepositorio(new Repositorio());
        incidencia1.setComentarios(new ArrayList<>());
        incidencia1.setIndicador92(0);
        incidencia1.setIndicador93(0);
        incidencia1.setNumero(0);
        
        incidencia2.setIncidenciaId(3);
        incidencia2.setCerradaEn(new Date());
        incidencia2.setCreadaEn(new Date());
        incidencia2.setModificadaEn(new Date());
        incidencia2.setNumComentarios(0);
        incidencia2.setEtiquetas(new ArrayList<>());
        incidencia2.setHito(new Hito());
        incidencia2.setPullRequest(new PullRequest());
        incidencia2.setCuerpo("Test modificado");
        incidencia2.setHtmlUrl("Test modificado");
        incidencia2.setUrl("Test modificado");
        incidencia2.setEstado(IssueService.STATE_CLOSED);
        incidencia2.setTitulo("Test modificado");
        incidencia2.setAsignadoA(new Usuario());
        incidencia2.setUsuario(new Usuario());
        incidencia2.setRepositorio(new Repositorio());
        incidencia2.setComentarios(new ArrayList<>());
        incidencia2.setIndicador92(0);
        incidencia2.setIndicador93(0);
        incidencia2.setNumero(0);
        
        incidencias.add(incidencia1);
        incidencias.add(incidencia2);
        
        long resultado = servicio.actualizarIncidencia(incidencias);
        assertEquals(resultado, 2L);
        LOGGER.info("Método actualizarListaIncidenciaTest finalizado.");
    }
    
    /**
     * Prueba para el método getIncidenciasPorRepositorio.
     * Prueba que se puedan recuperar las incidencias de un repositorio.
     * 
     * @throws GitDataIncidenciaExcepcion
     */
    @Test
    public void getIncidenciasPorRepositorioTest() throws GitDataIncidenciaExcepcion {
        LOGGER.info("Entrando al método getIncidenciasPorRepositorioTest.");
        final IncidenciaSrv servicio = new IncidenciaSrv();
        final Repositorio repositorio = new Repositorio();
        repositorio.setNombre("GitData");
        final List<Incidencia> incidencias = servicio.getIncidenciasPorRepositorio(repositorio);
        
        assertNotNull(incidencias);
        assertEquals(incidencias.isEmpty(), false);
        LOGGER.info("Método getIncidenciasPorRepositorioTest finalizado.");
    }
    
    /**
     * Prueba para el método getIncidenciasPorRepositorio.
     * Prueba que se pueda recuperar las incidencias cerradas de una repositorio.
     * 
     * @throws com.layala.gitdata.excepciones.GitDataIncidenciaExcepcion
     */
    @Test
    public void getIncidenciasCerradasPorRepositorioTest() throws GitDataIncidenciaExcepcion {
        LOGGER.info("Entrando al método getIncidenciasCerradasPorRepositorioTest.");
        final IncidenciaSrv servicio = new IncidenciaSrv();
        final Repositorio repositorio = new Repositorio();
        repositorio.setNombre("GitData");
        final List<Incidencia> incidencias = servicio.getIncidenciasPorRepositorio(repositorio);
        
        assertNotNull(incidencias);
        assertEquals(incidencias.isEmpty(), false);
        LOGGER.info("Método getIncidenciasCerradasPorRepositorioTest finalizado.");
    }
}
