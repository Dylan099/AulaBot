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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "estudiante_has_examen")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EstudianteHasExamen.findAll", query = "SELECT e FROM EstudianteHasExamen e"),
    @NamedQuery(name = "EstudianteHasExamen.findByIdEhe", query = "SELECT e FROM EstudianteHasExamen e WHERE e.idEhe = :idEhe"),
    @NamedQuery(name = "EstudianteHasExamen.findByNotaExamen", query = "SELECT e FROM EstudianteHasExamen e WHERE e.notaExamen = :notaExamen"),
    @NamedQuery(name = "EstudianteHasExamen.findByTxuser", query = "SELECT e FROM EstudianteHasExamen e WHERE e.txuser = :txuser"),
    @NamedQuery(name = "EstudianteHasExamen.findByTxhost", query = "SELECT e FROM EstudianteHasExamen e WHERE e.txhost = :txhost"),
    @NamedQuery(name = "EstudianteHasExamen.findByTxdate", query = "SELECT e FROM EstudianteHasExamen e WHERE e.txdate = :txdate"),
    @NamedQuery(name = "EstudianteHasExamen.findByStatus", query = "SELECT e FROM EstudianteHasExamen e WHERE e.status = :status")})
public class EstudianteHasExamen implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_Ehe")
    private Integer idEhe;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "notaExamen")
    private Float notaExamen;
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

    public EstudianteHasExamen() {
    }

    public EstudianteHasExamen(Integer idEhe) {
        this.idEhe = idEhe;
    }

    public EstudianteHasExamen(Integer idEhe, String txuser, String txhost, Date txdate, int status) {
        this.idEhe = idEhe;
        this.txuser = txuser;
        this.txhost = txhost;
        this.txdate = txdate;
        this.status = status;
    }

    public Integer getIdEhe() {
        return idEhe;
    }

    public void setIdEhe(Integer idEhe) {
        this.idEhe = idEhe;
    }

    public Float getNotaExamen() {
        return notaExamen;
    }

    public void setNotaExamen(Float notaExamen) {
        this.notaExamen = notaExamen;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEhe != null ? idEhe.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EstudianteHasExamen)) {
            return false;
        }
        EstudianteHasExamen other = (EstudianteHasExamen) object;
        if ((this.idEhe == null && other.idEhe != null) || (this.idEhe != null && !this.idEhe.equals(other.idEhe))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.example.ElAulaBot.domain.EstudianteHasExamen[ idEhe=" + idEhe + " ]";
    }
    
}
