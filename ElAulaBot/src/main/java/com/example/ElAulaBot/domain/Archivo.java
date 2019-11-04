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
import javax.persistence.Lob;
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
@Table(name = "archivo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Archivo.findAll", query = "SELECT a FROM Archivo a"),
    @NamedQuery(name = "Archivo.findByIdArchivo", query = "SELECT a FROM Archivo a WHERE a.idArchivo = :idArchivo"),
    @NamedQuery(name = "Archivo.findByFechaPublicacionAr", query = "SELECT a FROM Archivo a WHERE a.fechaPublicacionAr = :fechaPublicacionAr"),
    @NamedQuery(name = "Archivo.findByTxuser", query = "SELECT a FROM Archivo a WHERE a.txuser = :txuser"),
    @NamedQuery(name = "Archivo.findByTxhost", query = "SELECT a FROM Archivo a WHERE a.txhost = :txhost"),
    @NamedQuery(name = "Archivo.findByTxdate", query = "SELECT a FROM Archivo a WHERE a.txdate = :txdate"),
    @NamedQuery(name = "Archivo.findByStatus", query = "SELECT a FROM Archivo a WHERE a.status = :status")})
public class Archivo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idArchivo")
    private Integer idArchivo;
    @Lob
    @Column(name = "contenidoAr")
    private byte[] contenidoAr;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fechaPublicacionAr")
    @Temporal(TemporalType.DATE)
    private Date fechaPublicacionAr;
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
    @JoinColumn(name = "Curso_idCurso", referencedColumnName = "idCurso")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Curso cursoidCurso;

    public Archivo() {
    }

    public Archivo(Integer idArchivo) {
        this.idArchivo = idArchivo;
    }

    public Archivo(Integer idArchivo, Date fechaPublicacionAr, String txuser, String txhost, Date txdate, int status) {
        this.idArchivo = idArchivo;
        this.fechaPublicacionAr = fechaPublicacionAr;
        this.txuser = txuser;
        this.txhost = txhost;
        this.txdate = txdate;
        this.status = status;
    }

    public Integer getIdArchivo() {
        return idArchivo;
    }

    public void setIdArchivo(Integer idArchivo) {
        this.idArchivo = idArchivo;
    }

    public byte[] getContenidoAr() {
        return contenidoAr;
    }

    public void setContenidoAr(byte[] contenidoAr) {
        this.contenidoAr = contenidoAr;
    }

    public Date getFechaPublicacionAr() {
        return fechaPublicacionAr;
    }

    public void setFechaPublicacionAr(Date fechaPublicacionAr) {
        this.fechaPublicacionAr = fechaPublicacionAr;
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

    public Curso getCursoidCurso() {
        return cursoidCurso;
    }

    public void setCursoidCurso(Curso cursoidCurso) {
        this.cursoidCurso = cursoidCurso;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idArchivo != null ? idArchivo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Archivo)) {
            return false;
        }
        Archivo other = (Archivo) object;
        if ((this.idArchivo == null && other.idArchivo != null) || (this.idArchivo != null && !this.idArchivo.equals(other.idArchivo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.example.ElAulaBot.domain.Archivo[ idArchivo=" + idArchivo + " ]";
    }
    
}
