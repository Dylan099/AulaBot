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
@Table(name = "archivo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Archivo.findAll", query = "SELECT a FROM Archivo a"),
    @NamedQuery(name = "Archivo.findByIdArchivo", query = "SELECT a FROM Archivo a WHERE a.idArchivo = :idArchivo"),
    @NamedQuery(name = "Archivo.findByNombreAr", query = "SELECT a FROM Archivo a WHERE a.nombreAr = :nombreAr"),
    @NamedQuery(name = "Archivo.findByTipoMime", query = "SELECT a FROM Archivo a WHERE a.tipoMime = :tipoMime"),
    @NamedQuery(name = "Archivo.findByTamanioAr", query = "SELECT a FROM Archivo a WHERE a.tamanioAr = :tamanioAr"),
    @NamedQuery(name = "Archivo.findByUrlAr", query = "SELECT a FROM Archivo a WHERE a.urlAr = :urlAr"),
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
    @Column(name = "id_archivo")
    private Integer idArchivo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "nombre_ar")
    private String nombreAr;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "tipo_mime")
    private String tipoMime;
    @Basic(optional = false)
    @NotNull
    @Column(name = "tamanio_ar")
    private int tamanioAr;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "url_ar")
    private String urlAr;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_publicacion_ar")
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
    @JoinColumn(name = "id_curso", referencedColumnName = "id_curso")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Curso idCurso;

    public Archivo() {
    }

    public Archivo(Integer idArchivo) {
        this.idArchivo = idArchivo;
    }

    public Archivo(Integer idArchivo, String nombreAr, String tipoMime, int tamanioAr, String urlAr, Date fechaPublicacionAr, String txuser, String txhost, Date txdate, int status) {
        this.idArchivo = idArchivo;
        this.nombreAr = nombreAr;
        this.tipoMime = tipoMime;
        this.tamanioAr = tamanioAr;
        this.urlAr = urlAr;
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

    public String getNombreAr() {
        return nombreAr;
    }

    public void setNombreAr(String nombreAr) {
        this.nombreAr = nombreAr;
    }

    public String getTipoMime() {
        return tipoMime;
    }

    public void setTipoMime(String tipoMime) {
        this.tipoMime = tipoMime;
    }

    public int getTamanioAr() {
        return tamanioAr;
    }

    public void setTamanioAr(int tamanioAr) {
        this.tamanioAr = tamanioAr;
    }

    public String getUrlAr() {
        return urlAr;
    }

    public void setUrlAr(String urlAr) {
        this.urlAr = urlAr;
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

    public Curso getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(Curso idCurso) {
        this.idCurso = idCurso;
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
