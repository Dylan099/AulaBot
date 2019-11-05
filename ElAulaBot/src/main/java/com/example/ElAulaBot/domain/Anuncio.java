/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.ElAulaBot.domain;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author mirke
 */
@Entity
@Table(name = "anuncio")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Anuncio.findAll", query = "SELECT a FROM Anuncio a"),
    @NamedQuery(name = "Anuncio.findByIdAnuncio", query = "SELECT a FROM Anuncio a WHERE a.idAnuncio = :idAnuncio"),
    @NamedQuery(name = "Anuncio.findByFechaPublicacionAn", query = "SELECT a FROM Anuncio a WHERE a.fechaPublicacionAn = :fechaPublicacionAn"),
    @NamedQuery(name = "Anuncio.findByContenidoAn", query = "SELECT a FROM Anuncio a WHERE a.contenidoAn = :contenidoAn"),
    @NamedQuery(name = "Anuncio.findByTxuser", query = "SELECT a FROM Anuncio a WHERE a.txuser = :txuser"),
    @NamedQuery(name = "Anuncio.findByTxhost", query = "SELECT a FROM Anuncio a WHERE a.txhost = :txhost"),
    @NamedQuery(name = "Anuncio.findByTxdate", query = "SELECT a FROM Anuncio a WHERE a.txdate = :txdate"),
    @NamedQuery(name = "Anuncio.findByStatus", query = "SELECT a FROM Anuncio a WHERE a.status = :status")})
public class Anuncio implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_anuncio")
    private Integer idAnuncio;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_publicacion_an")
    @Temporal(TemporalType.DATE)
    private Date fechaPublicacionAn;
    @Size(max = 200)
    @Column(name = "contenido_an")
    private String contenidoAn;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "txuser")
    private String txuser;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "txhost")
    private String txhost;
    @Basic(optional = false)
    @NotNull
    @Column(name = "txdate")
    @Temporal(TemporalType.DATE)
    private Date txdate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "status")
    private int status;
    @JoinColumn(name = "curso_id_curso", referencedColumnName = "id_curso")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Curso cursoIdCurso;

    public Anuncio() {
    }

    public Anuncio(Integer idAnuncio) {
        this.idAnuncio = idAnuncio;
    }

    public Anuncio(Integer idAnuncio, Date fechaPublicacionAn, String txuser, String txhost, Date txdate, int status) {
        this.idAnuncio = idAnuncio;
        this.fechaPublicacionAn = fechaPublicacionAn;
        this.txuser = txuser;
        this.txhost = txhost;
        this.txdate = txdate;
        this.status = status;
    }

    public Integer getIdAnuncio() {
        return idAnuncio;
    }

    public void setIdAnuncio(Integer idAnuncio) {
        this.idAnuncio = idAnuncio;
    }

    public Date getFechaPublicacionAn() {
        return fechaPublicacionAn;
    }

    public void setFechaPublicacionAn(Date fechaPublicacionAn) {
        this.fechaPublicacionAn = fechaPublicacionAn;
    }

    public String getContenidoAn() {
        return contenidoAn;
    }

    public void setContenidoAn(String contenidoAn) {
        this.contenidoAn = contenidoAn;
    }

    public String getTxuser() {
        return txuser;
    }

    public void setTxuser(String txuser) {
        this.txuser = txuser;
    }

    public String getTxhost() {
        return txhost;
    }

    public void setTxhost(String txhost) {
        this.txhost = txhost;
    }

    public Date getTxdate() {
        return txdate;
    }

    public void setTxdate(Date txdate) {
        this.txdate = txdate;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Curso getCursoIdCurso() {
        return cursoIdCurso;
    }

    public void setCursoIdCurso(Curso cursoIdCurso) {
        this.cursoIdCurso = cursoIdCurso;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAnuncio != null ? idAnuncio.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Anuncio)) {
            return false;
        }
        Anuncio other = (Anuncio) object;
        if ((this.idAnuncio == null && other.idAnuncio != null) || (this.idAnuncio != null && !this.idAnuncio.equals(other.idAnuncio))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.example.ElAulaBot.domain.Anuncio[ idAnuncio=" + idAnuncio + " ]";
    }
    
}
