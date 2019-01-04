package com.layala.gitdata.entidades;

import java.util.Date;
import java.util.List;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Clase que representa un objeto Issue de la API v3 de GitHub
 *
 * @author Luis Ayala
 * @version 1.0
 * @since 1.0
 */
public class Incidencia {

    private long incidenciaId;
    private Date cerradaEn;
    private Date creadaEn;
    private Date modificadaEn;
    private int numComentarios;
    private List<Etiqueta> etiquetas;
    private Hito hito;
    private PullRequest pullRequest;
    private String cuerpo;
    private String htmlUrl;
    private String url;
    private String estado;
    private String titulo;
    private Usuario asignadoA;
    private Usuario usuario;
    private Repositorio repositorio;
    private List<Comentario> comentarios;
    private long indicador92;
    private long indicador93;
    private int numero;

    public long getIncidenciaId() {
        return incidenciaId;
    }

    public void setIncidenciaId(long incidenciaId) {
        this.incidenciaId = incidenciaId;
    }

    public Date getCerradaEn() {
        return cerradaEn;
    }

    public void setCerradaEn(Date cerradaEn) {
        this.cerradaEn = cerradaEn;
    }

    public Date getCreadaEn() {
        return creadaEn;
    }

    public void setCreadaEn(Date creadaEn) {
        this.creadaEn = creadaEn;
    }

    public Date getModificadaEn() {
        return modificadaEn;
    }

    public void setModificadaEn(Date modificadaEn) {
        this.modificadaEn = modificadaEn;
    }

    public List<Etiqueta> getEtiquetas() {
        return etiquetas;
    }

    public void setEtiquetas(List<Etiqueta> etiquetas) {
        this.etiquetas = etiquetas;
    }

    public Hito getHito() {
        return hito;
    }

    public void setHito(Hito hito) {
        this.hito = hito;
    }

    public PullRequest getPullRequest() {
        return pullRequest;
    }

    public void setPullRequest(PullRequest pullRequest) {
        this.pullRequest = pullRequest;
    }

    public String getCuerpo() {
        return cuerpo;
    }

    public void setCuerpo(String cuerpo) {
        this.cuerpo = cuerpo;
    }

    public String getHtmlUrl() {
        return htmlUrl;
    }

    public void setHtmlUrl(String htmlUrl) {
        this.htmlUrl = htmlUrl;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Usuario getAsignadoA() {
        return asignadoA;
    }

    public void setAsignadoA(Usuario asignadoA) {
        this.asignadoA = asignadoA;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Repositorio getRepositorio() {
        return repositorio;
    }

    public void setRepositorio(Repositorio repositorio) {
        this.repositorio = repositorio;
    }

    public int getNumComentarios() {
        return numComentarios;
    }

    public void setNumComentarios(int numComentarios) {
        this.numComentarios = numComentarios;
    }

    public List<Comentario> getComentarios() {
        return comentarios;
    }

    public void setComentarios(List<Comentario> comentarios) {
        this.comentarios = comentarios;
    }

    public long getIndicador92() {
        return indicador92;
    }

    public void setIndicador92(long indicador92) {
        this.indicador92 = indicador92;
    }

    public long getIndicador93() {
        return indicador93;
    }

    public void setIndicador93(long indicador93) {
        this.indicador93 = indicador93;
    }

    public long getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }
    
    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("incidenciaId", incidenciaId)
                .append("titulo",       titulo)
                .append("creadaEn",     creadaEn)
                .append("estado",       estado)
                .toString();
    }

    @Override
    public boolean equals(final Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (obj.getClass() != getClass()) {
            return false;
        }

        if (!(obj instanceof Incidencia)) {
            return false;
        }

        Incidencia incidencia = (Incidencia) obj;
        return new EqualsBuilder()
                .append(incidenciaId, incidencia.getIncidenciaId())
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(21, 15)
                .append(incidenciaId)
                .toHashCode();
    }
}
