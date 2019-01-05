package com.layala.gitdata.pruebas.unitarias.servicios;

import com.layala.gitdata.entidades.Repositorio;
import com.layala.gitdata.excepciones.GitDataConfigExcepcion;
import com.layala.gitdata.excepciones.GitDataRepositorioExcepcion;
import com.layala.gitdata.servicios.RepositorioSrv;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import static junit.framework.Assert.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.Rule;
import org.junit.rules.ExpectedException;
import org.junit.runners.MethodSorters;

/**
 * Pruebas unitarias para la clase de servicio RepositorioSrv.
 * 
 * Se pueban los método de inserción y de actualización, tanto para los métodos
 * que reciben por parámetro una lista como los métodos que reciben solo un 
 * repositorio.
 * <p>
 * Las pruebas se ejecutan en orden alfabético mediante la anotación
 * @FixMethodOrder para que no causen conflicto la ejcución de las pruebas unitarias.
 * 
 * @author Luis Ayala
 * @version 1.0
 * @since 1.0
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RepositorioSrvTest {
    private static final Logger LOGGER = LogManager.getLogger(RepositorioSrvTest.class);
    
    @Rule
    public ExpectedException excepcion = ExpectedException.none();
    
    public RepositorioSrvTest() {
    }
    
    /**
     * Prueba para el método actualizarRepositorio.
     * Se prueba que el método lance la excepción cuando el parámetro es nulo.
     * 
     * @throws GitDataRepositorioExcepcion
     * @throws GitDataConfigExcepcion 
     */
    @Test
    public void actualizarRepositorioExcepcionTest() throws GitDataRepositorioExcepcion, GitDataConfigExcepcion {
        LOGGER.info("Entrando al método actualizarRepositorioExcepcionTest.");
        
        final RepositorioSrv servicio = new RepositorioSrv();
        final Repositorio repositorio = null;
        
        excepcion.expect(GitDataRepositorioExcepcion.class);
        excepcion.expectMessage("El repositorio no puede ser nulo");
        
        servicio.actualizarRepositorio(repositorio);
        LOGGER.info("Método actualizarRepositorioExcepcionTest finalizado.");
    }
    
    /**
     * Prueba para el método actualizarRepositorio cuando el parámetro es una lista.
     * Se prueba que el método lance la excepción cuando el parámetro es nulo.
     * 
     * @throws GitDataRepositorioExcepcion
     * @throws GitDataConfigExcepcion 
     */
    @Test
    public void actualizarListaRepositorioExcepcionTest() throws GitDataRepositorioExcepcion, GitDataConfigExcepcion {
        LOGGER.info("Entrando al método actualizarListaRepositorioExcepcionTest.");
        
        final RepositorioSrv servicio = new RepositorioSrv();
        final List<Repositorio> repositorios = null;
        
        excepcion.expect(GitDataRepositorioExcepcion.class);
        excepcion.expectMessage("La lista no puede ser nula");
        
        servicio.actualizarRepositorio(repositorios);
        LOGGER.info("Método actualizarListaRepositorioExcepcionTest finalizado.");
    }
    
    /**
     * Prueba para el método insertarRepositorio.
     * Se prueba que el método lance la excepción cuando el parámetro es nulo.
     * 
     * @throws GitDataRepositorioExcepcion
     * @throws GitDataConfigExcepcion 
     */
    @Test
    public void insertarRepositorioExcepcionTest() throws GitDataRepositorioExcepcion, GitDataConfigExcepcion {
        LOGGER.info("Entrando al método insertarRepositorioExcepcionTest.");
        
        final RepositorioSrv servicio = new RepositorioSrv();
        final Repositorio repositorio = null;
        
        excepcion.expect(GitDataRepositorioExcepcion.class);
        excepcion.expectMessage("El repositorio no puede ser nulo");
        
        servicio.insertarRepositorio(repositorio);
        LOGGER.info("Método insertarRepositorioExcepcionTest finalizado.");
    }
    
    /**
     * Prueba para el método insertarRepositorio cuando el parámetro es una lista.
     * Se prueba que el método lance la excepción cuando el parámetro es nulo.
     * 
     * @throws GitDataRepositorioExcepcion
     * @throws GitDataConfigExcepcion 
     */
    @Test
    public void insertarListaRepositorioExcepcionTest() throws GitDataRepositorioExcepcion, GitDataConfigExcepcion {
        LOGGER.info("Entrando al método insertarListaRepositorioExcepcionTest.");
        
        final RepositorioSrv servicio = new RepositorioSrv();
        final List<Repositorio> repositorios = null;
        
        excepcion.expect(GitDataRepositorioExcepcion.class);
        excepcion.expectMessage("La lista no puede ser nula");
        
        servicio.insertarRepositorio(repositorios);
        LOGGER.info("Método insertarListaRepositorioExcepcionTest finalizado.");
    }
    
    /**
     * Prueba para el método insertarRepositorio.
     * Prueba que se realice exitosamente la inserción de un repositorio.
     * 
     * @throws GitDataRepositorioExcepcion
     * @throws GitDataConfigExcepcion 
     */
    @Test
    public void insertarRepositorioTest() throws GitDataRepositorioExcepcion, GitDataConfigExcepcion {
        LOGGER.info("Entrando al método insertarRepositorioTest.");
        
        final RepositorioSrv servicio = new RepositorioSrv();
        final Repositorio repositorio1 = new Repositorio();
        
        repositorio1.setTieneIncidencias(false);
        repositorio1.setCreadoEn(new Date());
        repositorio1.setModificadoEn(new Date());
        repositorio1.setRepositorioId(1L);
        repositorio1.setIncidenciasAbiertas(0);
        repositorio1.setDescripcion("Descripcion test");
        repositorio1.setHomepage("Home test");
        repositorio1.setLenguaje("Lenguaje test");
        repositorio1.setNombre("Nombre test");
        repositorio1.setUrl("Url test");
        repositorio1.setGitUrl("GitUrl test");
        repositorio1.setHtmlUrl("HtmlUrl test");
        
        long resultado = servicio.insertarRepositorio(repositorio1);
        assertEquals(resultado, 1L);
        LOGGER.info("Método insertarRepositorioTest finalizado.");
    }
    
    /**
     * Prueba para el método insertarRepositorio cuando el parámetro es una lista de repositorios.
     * Prueba que se realice de forma exitosa la inserción de varios repositorios. 
     * @throws GitDataRepositorioExcepcion
     * @throws GitDataConfigExcepcion 
     */
    @Test
    public void insertarListaRepositorioTest() throws GitDataRepositorioExcepcion, GitDataConfigExcepcion {
        LOGGER.info("Entrando al método insertarListaRepositorioTest.");
        
        final RepositorioSrv servicio = new RepositorioSrv();
        final Repositorio repositorio1 = new Repositorio();
        final Repositorio repositorio2 = new Repositorio();
        final List<Repositorio> repositorios = new ArrayList<>();
        
        repositorio1.setTieneIncidencias(false);
        repositorio1.setCreadoEn(new Date());
        repositorio1.setModificadoEn(new Date());
        repositorio1.setRepositorioId(2L);
        repositorio1.setIncidenciasAbiertas(0);
        repositorio1.setDescripcion("Descripcion test");
        repositorio1.setHomepage("Home test");
        repositorio1.setLenguaje("Lenguaje test");
        repositorio1.setNombre("Nombre test");
        repositorio1.setUrl("Url test");
        repositorio1.setGitUrl("GitUrl test");
        repositorio1.setHtmlUrl("HtmlUrl test");
        
        repositorio2.setTieneIncidencias(false);
        repositorio2.setCreadoEn(new Date());
        repositorio2.setModificadoEn(new Date());
        repositorio2.setRepositorioId(3L);
        repositorio2.setIncidenciasAbiertas(0);
        repositorio2.setDescripcion("Descripcion test");
        repositorio2.setHomepage("Home test");
        repositorio2.setLenguaje("Lenguaje test");
        repositorio2.setNombre("Nombre test");
        repositorio2.setUrl("Url test");
        repositorio2.setGitUrl("GitUrl test");
        repositorio2.setHtmlUrl("HtmlUrl test");
        
        repositorios.add(repositorio1);
        repositorios.add(repositorio2);
        
        long resultado = servicio.insertarRepositorio(repositorios);
        assertEquals(resultado, 2L);
        LOGGER.info("Método insertarListaRepositorioTest finalizado.");
    }
    
    /**
     * Prueba para el método getRepositorios.
     * Se prueba que se regresen todos los repositorio.
     * 
     * @throws GitDataRepositorioExcepcion 
     */
    @Test
    public void getRepositoriosTest() throws GitDataRepositorioExcepcion {
        LOGGER.info("Entrando al método getRepositoriosTest.");
        
        RepositorioSrv repoSrv = new RepositorioSrv();
        List<Repositorio> repositorios = repoSrv.getRepositorios();
        
        assertNotNull(repositorios);
        assertEquals(repositorios.size(), 4);
        assertFalse(repositorios.isEmpty());
        
        LOGGER.info("Método getRepositoriosTest finalizado.");
    }
    
    /**
     * Pruaba para el método actualizarRepositorio.
     * Prueba que se actualice de forma exitosa un repositorio.
     * 
     * @throws GitDataRepositorioExcepcion
     * @throws GitDataConfigExcepcion 
     */
    @Test
    public void zactualizarRepositorioTest() throws GitDataRepositorioExcepcion, GitDataConfigExcepcion {
        LOGGER.info("Entrando al método actualizarRepositorioTest.");
        
        final RepositorioSrv servicio = new RepositorioSrv();
        final Repositorio repositorio1 = new Repositorio();
        
        repositorio1.setTieneIncidencias(true);
        repositorio1.setCreadoEn(new Date());
        repositorio1.setModificadoEn(new Date());
        repositorio1.setRepositorioId(1L);
        repositorio1.setIncidenciasAbiertas(1);
        repositorio1.setDescripcion("Descripcion test actualizado1");
        repositorio1.setHomepage("Home test actualizado1");
        repositorio1.setLenguaje("Lenguaje test actualizado1");
        repositorio1.setNombre("Nombre test actualizado1");
        repositorio1.setUrl("Url test actualizado1");
        repositorio1.setGitUrl("GitUrl test actualizado1");
        repositorio1.setHtmlUrl("HtmlUrl test actualizado1");
        
        long resultado = servicio.actualizarRepositorio(repositorio1);
        assertEquals(resultado, 1L);
        LOGGER.info("Método actualizarRepositorioTest finalizado.");
    }
    
    /**
     * Prueba para el método actualizarRepositorio cuando el parámetro es una lista de repositorios.
     * Pruaba que se actualice de forma exitosa los repositorios de la lista.
     * Se agregan dos repositorios y solo uno de ellos existen en Mongo.
     * <p>
     * @throws GitDataRepositorioExcepcion
     * @throws GitDataConfigExcepcion 
     */
    @Test
    public void zzactualizarListaRepositorioTest() throws GitDataRepositorioExcepcion, GitDataConfigExcepcion {
        LOGGER.info("Entrando al método actualizarListaRepositorioTest.");
        
        final RepositorioSrv servicio = new RepositorioSrv();
        final Repositorio repositorio2 = new Repositorio();
        final Repositorio repositorio3 = new Repositorio();
        final List<Repositorio> repositorios = new ArrayList<>();
        
        repositorio2.setTieneIncidencias(true);
        repositorio2.setCreadoEn(new Date());
        repositorio2.setModificadoEn(new Date());
        repositorio2.setRepositorioId(2L);
        repositorio2.setIncidenciasAbiertas(1);
        repositorio2.setDescripcion("Descripcion test actualizado2");
        repositorio2.setHomepage("Home test actualizado2");
        repositorio2.setLenguaje("Lenguaje test actualizado2");
        repositorio2.setNombre("Nombre test actualizado2");
        repositorio2.setUrl("Url test actualizado2");
        repositorio2.setGitUrl("GitUrl test actualizado2");
        repositorio2.setHtmlUrl("HtmlUrl test actualizado2");
        
       repositorio3.setTieneIncidencias(true);
        repositorio3.setCreadoEn(new Date());
        repositorio3.setModificadoEn(new Date());
        repositorio3.setRepositorioId(4L);
        repositorio3.setIncidenciasAbiertas(1);
        repositorio3.setDescripcion("Descripcion test actualizado4");
        repositorio3.setHomepage("Home test actualizado4");
        repositorio3.setLenguaje("Lenguaje test actualizado4");
        repositorio3.setNombre("Nombre test actualizado4");
        repositorio3.setUrl("Url test actualizado4");
        repositorio3.setGitUrl("GitUrl test actualizado4");
        repositorio3.setHtmlUrl("HtmlUrl test actualizado4");
        
        repositorios.add(repositorio2);
        repositorios.add(repositorio3);
        
        long resultado = servicio.actualizarRepositorio(repositorios);
        assertEquals(resultado, 1L);
        LOGGER.info("Método actualizarListaRepositorioTest finalizado.");
    }
}
