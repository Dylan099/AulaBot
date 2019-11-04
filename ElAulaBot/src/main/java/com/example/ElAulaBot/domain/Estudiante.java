/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.ElAulaBot.domain;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author mirke
 */
@Entity
@Table(name = "estudiante")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Estudiante.findAll", query = "SELECT e FROM Estudiante e"),
    @NamedQuery(name = "Estudiante.findByIdEstudiante", query = "SELECT e FROM Estudiante e WHERE e.idEstudiante = :idEstudiante"),
    @NamedQuery(name = "Estudiante.findByChatId", query = "SELECT e FROM Estudiante e WHERE e.chatId = :chatId"),
    @NamedQuery(name = "Estudiante.findByCelularEs", query = "SELECT e FROM Estudiante e WHERE e.celularEs = :celularEs"),
    @NamedQuery(name = "Estudiante.findByPrimerNombreEs", query = "SELECT e FROM Estudiante e WHERE e.primerNombreEs = :primerNombreEs"),
    @NamedQuery(name = "Estudiante.findByPrimerApellidoEs", query = "SELECT e FROM Estudiante e WHERE e.primerApellidoEs = :primerApellidoEs"),
    @NamedQuery(name = "Estudiante.findBySegundoNombreEs", query = "SELECT e FROM Estudiante e WHERE e.segundoNombreEs = :segundoNombreEs"),
    @NamedQuery(name = "Estudiante.findBySegundoApellidoEs", query = "SELECT e FROM Estudiante e WHERE e.segundoApellidoEs = :segundoApellidoEs"),
    @NamedQuery(name = "Estudiante.findByTxuser", query = "SELECT e FROM Estudiante e WHERE e.txuser = :txuser"),
    @NamedQuery(name = "Estudiante.findByTxhost", query = "SELECT e FROM Estudiante e WHERE e.txhost = :txhost"),
    @NamedQuery(name = "Estudiante.findByTxdate", query = "SELECT e FROM Estudiante e WHERE e.txdate = :txdate"),
    @NamedQuery(name = "Estudiante.findByStatus", query = "SELECT e FROM Estudiante e WHERE e.status = :status")})
public class Estudiante implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idEstudiante")
    private Integer idEstudiante;
    @Basic(optional = false)
    @NotNull
    @Column(name = "chat_id")
    private int chatId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "celularEs")
    private String celularEs;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "primerNombreEs")
    private String primerNombreEs;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "primerApellidoEs")
    private String primerApellidoEs;
    @Size(max = 50)
    @Column(name = "segundoNombreEs")
    private String segundoNombreEs;
    @Size(max = 50)
    @Column(name = "segundoApellidoEs")
    private String segundoApellidoEs;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "estudianteidEstudiante", fetch = FetchType.LAZY)
    private Collection<CursoHasEstudiante> cursoHasEstudianteCollection;

    public Estudiante() {
    }

    public Estudiante(Integer idEstudiante) {
        this.idEstudiante = idEstudiante;
    }

    public Estudiante(Integer idEstudiante, int chatId, String celularEs, String primerNombreEs, String primerApellidoEs, String txuser, String txhost, Date txdate, int status) {
        this.idEstudiante = idEstudiante;
        this.chatId = chatId;
        this.celularEs = celularEs;
        this.primerNombreEs = primerNombreEs;
        this.primerApellidoEs = primerApellidoEs;
        this.txuser = txuser;
        this.txhost = txhost;
        this.txdate = txdate;
        this.status = status;
    }

    public Integer getIdEstudiante() {
        return idEstudiante;
    }

    public void setIdEstudiante(Integer idEstudiante) {
        this.idEstudiante = idEstudiante;
    }

    public int getChatId() {
        return chatId;
    }

    public void setChatId(int chatId) {
        this.chatId = chatId;
    }

    public String getCelularEs() {
        return celularEs;
    }

    public void setCelularEs(String celularEs) {
        this.celularEs = celularEs;
    }

    public String getPrimerNombreEs() {
        return primerNombreEs;
    }

    public void setPrimerNombreEs(String primerNombreEs) {
        this.primerNombreEs = primerNombreEs;
    }

    public String getPrimerApellidoEs() {
        return primerApellidoEs;
    }

    public void setPrimerApellidoEs(String primerApellidoEs) {
        this.primerApellidoEs = primerApellidoEs;
    }

    public String getSegundoNombreEs() {
        return segundoNombreEs;
    }

    public void setSegundoNombreEs(String segundoNombreEs) {
        this.segundoNombreEs = segundoNombreEs;
    }

    public String getSegundoApellidoEs() {
        return segundoApellidoEs;
    }

    public void setSegundoApellidoEs(String segundoApellidoEs) {
        this.segundoApellidoEs = segundoApellidoEs;
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

    @XmlTransient
    public Collection<CursoHasEstudiante> getCursoHasEstudianteCollection() {
        return cursoHasEstudianteCollection;
    }

    public void setCursoHasEstudianteCollection(Collection<CursoHasEstudiante> cursoHasEstudianteCollection) {
        this.cursoHasEstudianteCollection = cursoHasEstudianteCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEstudiante != null ? idEstudiante.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Estudiante)) {
            return false;
        }
        Estudiante other = (Estudiante) object;
        if ((this.idEstudiante == null && other.idEstudiante != null) || (this.idEstudiante != null && !this.idEstudiante.equals(other.idEstudiante))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.example.ElAulaBot.domain.Estudiante[ idEstudiante=" + idEstudiante + " ]";
    }
    
}
