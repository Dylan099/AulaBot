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
@Table(name = "respuesta")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Respuesta.findAll", query = "SELECT r FROM Respuesta r"),
    @NamedQuery(name = "Respuesta.findByIdRespuesta", query = "SELECT r FROM Respuesta r WHERE r.idRespuesta = :idRespuesta"),
    @NamedQuery(name = "Respuesta.findByEnunciadoRe", query = "SELECT r FROM Respuesta r WHERE r.enunciadoRe = :enunciadoRe"),
    @NamedQuery(name = "Respuesta.findByCorrecto", query = "SELECT r FROM Respuesta r WHERE r.correcto = :correcto"),
    @NamedQuery(name = "Respuesta.findByTxuser", query = "SELECT r FROM Respuesta r WHERE r.txuser = :txuser"),
    @NamedQuery(name = "Respuesta.findByTxhost", query = "SELECT r FROM Respuesta r WHERE r.txhost = :txhost"),
    @NamedQuery(name = "Respuesta.findByTxdate", query = "SELECT r FROM Respuesta r WHERE r.txdate = :txdate"),
    @NamedQuery(name = "Respuesta.findByStatus", query = "SELECT r FROM Respuesta r WHERE r.status = :status")})
public class Respuesta implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idRespuesta")
    private Integer idRespuesta;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "enunciadoRe")
    private String enunciadoRe;
    @Basic(optional = false)
    @NotNull
    @Column(name = "correcto")
    private boolean correcto;
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
    @JoinColumn(name = "Pregunta_idPregunta", referencedColumnName = "idPregunta")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Pregunta preguntaidPregunta;

    public Respuesta() {
    }

    public Respuesta(Integer idRespuesta) {
        this.idRespuesta = idRespuesta;
    }

    public Respuesta(Integer idRespuesta, String enunciadoRe, boolean correcto, String txuser, String txhost, Date txdate, int status) {
        this.idRespuesta = idRespuesta;
        this.enunciadoRe = enunciadoRe;
        this.correcto = correcto;
        this.txuser = txuser;
        this.txhost = txhost;
        this.txdate = txdate;
        this.status = status;
    }

    public Integer getIdRespuesta() {
        return idRespuesta;
    }

    public void setIdRespuesta(Integer idRespuesta) {
        this.idRespuesta = idRespuesta;
    }

    public String getEnunciadoRe() {
        return enunciadoRe;
    }

    public void setEnunciadoRe(String enunciadoRe) {
        this.enunciadoRe = enunciadoRe;
    }

    public boolean getCorrecto() {
        return correcto;
    }

    public void setCorrecto(boolean correcto) {
        this.correcto = correcto;
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

    public Pregunta getPreguntaidPregunta() {
        return preguntaidPregunta;
    }

    public void setPreguntaidPregunta(Pregunta preguntaidPregunta) {
        this.preguntaidPregunta = preguntaidPregunta;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idRespuesta != null ? idRespuesta.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Respuesta)) {
            return false;
        }
        Respuesta other = (Respuesta) object;
        if ((this.idRespuesta == null && other.idRespuesta != null) || (this.idRespuesta != null && !this.idRespuesta.equals(other.idRespuesta))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.example.ElAulaBot.domain.Respuesta[ idRespuesta=" + idRespuesta + " ]";
    }
    
}
