package com.layala.gitdata.pruebas.unitarias.configuraciones;

import com.layala.gitdata.configuraciones.Configuracion;
import com.layala.gitdata.excepciones.GitDataConfigExcepcion;
import com.mongodb.client.MongoClient;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Pruebas unitarias para los métodos de la clase Configuracion
 * 
 * @author Luis Ayala
 */
public class ConfiguracionTest {
    private static final Logger LOGGER = LogManager.getLogger(ConfiguracionTest.class);

    public ConfiguracionTest() {
    }
    
    /**
     * Prueba para el método getProperty, de la clase Configuracion.
     * 
     * @author Luis Ayala
     * @version 1.0
     * @since 1.0
     * @throws com.layala.gitdata.excepciones.GitDataConfigExcepcion
     */
    @Test
    public void testGetProperty() throws GitDataConfigExcepcion {
        String llave        = "puerto";
        String expResultado = "27017";
        String resultado    = Configuracion.getProperty(llave);
        
        assertNotNull(resultado);
        assertEquals(expResultado, resultado);
    }

    /**
     * Prueba unitaria para el método crearConexion de la clase Configuracion
     *
     * @author Luis Ayala
     * @version 1.0
     * @since 1.0
     */
    @Test
    public void testCrearConexion() {
        try (MongoClient resultado = Configuracion.crearConexion()) {
            assertNotNull(resultado);
        } catch(GitDataConfigExcepcion e) {
            LOGGER.error(e);
        }
    }
}
