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
@Table(name = "curso_has_estudiante")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CursoHasEstudiante.findAll", query = "SELECT c FROM CursoHasEstudiante c"),
    @NamedQuery(name = "CursoHasEstudiante.findByIdChe", query = "SELECT c FROM CursoHasEstudiante c WHERE c.idChe = :idChe"),
    @NamedQuery(name = "CursoHasEstudiante.findByTxuser", query = "SELECT c FROM CursoHasEstudiante c WHERE c.txuser = :txuser"),
    @NamedQuery(name = "CursoHasEstudiante.findByTxhost", query = "SELECT c FROM CursoHasEstudiante c WHERE c.txhost = :txhost"),
    @NamedQuery(name = "CursoHasEstudiante.findByTxdate", query = "SELECT c FROM CursoHasEstudiante c WHERE c.txdate = :txdate"),
    @NamedQuery(name = "CursoHasEstudiante.findByStatus", query = "SELECT c FROM CursoHasEstudiante c WHERE c.status = :status")})
public class CursoHasEstudiante implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_che")
    private Integer idChe;
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
    @JoinColumn(name = "estudiante_id_estudiante", referencedColumnName = "id_estudiante")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Estudiante estudianteIdEstudiante;

    public CursoHasEstudiante() {
    }

    public CursoHasEstudiante(Integer idChe) {
        this.idChe = idChe;
    }

    public CursoHasEstudiante(Integer idChe, String txuser, String txhost, Date txdate, int status) {
        this.idChe = idChe;
        this.txuser = txuser;
        this.txhost = txhost;
        this.txdate = txdate;
        this.status = status;
    }

    public Integer getIdChe() {
        return idChe;
    }

    public void setIdChe(Integer idChe) {
        this.idChe = idChe;
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

    public Estudiante getEstudianteIdEstudiante() {
        return estudianteIdEstudiante;
    }

    public void setEstudianteIdEstudiante(Estudiante estudianteIdEstudiante) {
        this.estudianteIdEstudiante = estudianteIdEstudiante;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idChe != null ? idChe.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CursoHasEstudiante)) {
            return false;
        }
        CursoHasEstudiante other = (CursoHasEstudiante) object;
        if ((this.idChe == null && other.idChe != null) || (this.idChe != null && !this.idChe.equals(other.idChe))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.example.ElAulaBot.domain.CursoHasEstudiante[ idChe=" + idChe + " ]";
    }
    
}
