package com.layala.gitdata.aplicativos;

import com.layala.gitdata.entidades.Incidencia;
import com.layala.gitdata.entidades.Repositorio;
import com.layala.gitdata.excepciones.GitDataIncidenciaExcepcion;
import com.layala.gitdata.excepciones.GitDataRepositorioExcepcion;
import com.layala.gitdata.servicios.IncidenciaSrv;
import com.layala.gitdata.servicios.RepositorioSrv;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.*;
import org.eclipse.egit.github.core.Issue;
import org.eclipse.egit.github.core.Repository;
import org.eclipse.egit.github.core.service.IssueService;
import org.eclipse.egit.github.core.service.RepositoryService;
 
/**
 * Aplicación de prueba de concepto
 * 
 * @author Luis Ayala
 */
public class App {
    private static final Logger LOGGER = LogManager.getLogger(App.class);
    
    public static void main(String... args) throws IOException {
        /*try {
            LOGGER.trace("Entrando en la aplicación");
            
            RepositorioSrv repoSrv = new RepositorioSrv();
            List<Repositorio> repositorios = repoSrv.getRepositorios();
            
            IncidenciaSrv incidenciaSrv = new IncidenciaSrv();
            List<Incidencia> incidencias = new ArrayList<>();
            
            List<Incidencia> lista = null;
            List<Incidencia> lista2 = null;
            for (Repositorio repositorio : repositorios) {
                lista = incidenciaSrv.getIncidenciasPorRepositorio(repositorio);
                lista2 = incidenciaSrv.getIncidenciasCerradasPorRepositorio(repositorio);
                
                incidencias.addAll(lista);
                incidencias.addAll(lista2);
            }
            incidenciaSrv.insertarIncidencia(incidencias);
        } catch (GitDataIncidenciaExcepcion | GitDataRepositorioExcepcion ex) {
            LOGGER.error(ex);
        }*/
        
        //final RepositoryService repoSrv = new RepositoryService();
        //Repository repo = repoSrv.getRepository("JuliaLang", "julia");
        
        final IssueService issueSrv = new IssueService();
        List<Incidencia> lista = new ArrayList<>();
        final List<Issue> incidencias = issueSrv.getIssues("JuliaLang", "julia", null);
        incidencias.stream().map((issue) -> {
            final Incidencia incidencia = new Incidencia();
            incidencia.setIncidenciaId(issue.getId());
            incidencia.setCerradaEn(issue.getClosedAt());
            incidencia.setCreadaEn(issue.getCreatedAt());
            incidencia.setModificadaEn(issue.getUpdatedAt());
            incidencia.setNumComentarios(issue.getComments());
            incidencia.setCuerpo(issue.getBody());
            incidencia.setHtmlUrl(issue.getHtmlUrl());
            incidencia.setUrl(issue.getUrl());
            incidencia.setEstado(issue.getState());
            incidencia.setTitulo(issue.getTitle());
            return incidencia;
        }).forEachOrdered((incidencia) -> {
            lista.add(incidencia);
        });
        
        System.out.println(lista.size());
        System.out.println(incidencias.size());
        
        
        
        
    }
}
