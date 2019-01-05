package com.layala.gitdata.resources;

import com.google.gson.Gson;
import com.layala.gitdata.ws.GitData;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import org.glassfish.grizzly.http.server.HttpServer;
import org.junit.After;
import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;
import java.lang.reflect.Type;
import com.google.gson.reflect.TypeToken;
import com.layala.gitdata.entidades.Incidencia;
import java.util.ArrayList;
import java.util.List;
import static org.hamcrest.CoreMatchers.equalTo;
import org.hamcrest.Matchers;
import static org.hamcrest.Matchers.hasItem;

/**
 * Prueba unitaria para el servicio web Incidencia 91
 *  
 * @author Luis Ayala
 * @version 1.0
 * @since 1.0
 */
public class Indicador91ServiceTest {
    private HttpServer servidor;
    private WebTarget uri;
    
    public Indicador91ServiceTest() {
    }
    
    /**
     * inicializa el cliente para ejecutar el servicio web.
     * 
     * @throws Exception 
     */
    @Before
    public void setUp() throws Exception {
        servidor = GitData.inicializarServidor();
        Client c = ClientBuilder.newClient();
        uri = c.target(GitData.BASE_URI);
    }
    
    /**
     * Detiene el servidor luego de ejecutar las pruebas.
     * 
     * @throws Exception 
     */
    @After
    public void detenerServidor() throws Exception {
        servidor.stop();
    }
    
    /**
     * Prueba unitaria para el indicador 91
     */
    @Test
    public void testGetIndicador91() {
        final String repositorio = "GitData";
        String response = uri.path("/indicador91/"+repositorio).request().get(String.class);
        
        assertNotNull(response);
        
        Type tipoLista = new TypeToken<ArrayList<Incidencia>>(){}.getType();
        List<Incidencia> incidencias = new Gson().fromJson(response, tipoLista);
        
        assertNotNull(incidencias);
        assertFalse(incidencias.isEmpty());
        assertThat(incidencias, hasItem(Matchers.<Incidencia>hasProperty("estado", equalTo("closed"))));
    }    
}
