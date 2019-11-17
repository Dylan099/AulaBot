/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.ElAulaBot.domain;

import java.io.Serializable;
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
import javax.persistence.OneToOne;
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
@Table(name = "profesor")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Profesor.findAll", query = "SELECT p FROM Profesor p"),
    @NamedQuery(name = "Profesor.findByIdProfesor", query = "SELECT p FROM Profesor p WHERE p.idProfesor = :idProfesor"),
    @NamedQuery(name = "Profesor.findByChatId", query = "SELECT p FROM Profesor p WHERE p.chatId = :chatId"),
    @NamedQuery(name = "Profesor.findByPrimerNombrePr", query = "SELECT p FROM Profesor p WHERE p.primerNombrePr = :primerNombrePr"),
    @NamedQuery(name = "Profesor.findByPrimerApellidoPr", query = "SELECT p FROM Profesor p WHERE p.primerApellidoPr = :primerApellidoPr"),
    @NamedQuery(name = "Profesor.findBySegundoNombrePr", query = "SELECT p FROM Profesor p WHERE p.segundoNombrePr = :segundoNombrePr"),
    @NamedQuery(name = "Profesor.findBySegundoApellidoPr", query = "SELECT p FROM Profesor p WHERE p.segundoApellidoPr = :segundoApellidoPr"),
    @NamedQuery(name = "Profesor.findByCelularPr", query = "SELECT p FROM Profesor p WHERE p.celularPr = :celularPr"),
    @NamedQuery(name = "Profesor.findByTxuser", query = "SELECT p FROM Profesor p WHERE p.txuser = :txuser"),
    @NamedQuery(name = "Profesor.findByTxhost", query = "SELECT p FROM Profesor p WHERE p.txhost = :txhost"),
    @NamedQuery(name = "Profesor.findByTxdate", query = "SELECT p FROM Profesor p WHERE p.txdate = :txdate"),
    @NamedQuery(name = "Profesor.findByStatus", query = "SELECT p FROM Profesor p WHERE p.status = :status")})
public class Profesor implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_profesor")
    private Integer idProfesor;
    @Basic(optional = false)
    @NotNull
    @Column(name = "chat_id")
    private int chatId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "primer_nombre_pr")
    private String primerNombrePr;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "primer_apellido_pr")
    private String primerApellidoPr;
    @Size(max = 50)
    @Column(name = "segundo_nombre_pr")
    private String segundoNombrePr;
    @Size(max = 50)
    @Column(name = "segundo_apellido_pr")
    private String segundoApellidoPr;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "celular_pr")
    private String celularPr;
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
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "profesorIdProfesor", fetch = FetchType.LAZY)
    private Curso curso;

    public Profesor() {
    }

    public Profesor(Integer idProfesor) {
        this.idProfesor = idProfesor;
    }

    public Profesor(Integer idProfesor, int chatId, String primerNombrePr, String primerApellidoPr, String celularPr, String txuser, String txhost, Date txdate, int status) {
        this.idProfesor = idProfesor;
        this.chatId = chatId;
        this.primerNombrePr = primerNombrePr;
        this.primerApellidoPr = primerApellidoPr;
        this.celularPr = celularPr;
        this.txuser = txuser;
        this.txhost = txhost;
        this.txdate = txdate;
        this.status = status;
    }

    public Integer getIdProfesor() {
        return idProfesor;
    }

    public void setIdProfesor(Integer idProfesor) {
        this.idProfesor = idProfesor;
    }

    public int getChatId() {
        return chatId;
    }

    public void setChatId(int chatId) {
        this.chatId = chatId;
    }

    public String getPrimerNombrePr() {
        return primerNombrePr;
    }

    public void setPrimerNombrePr(String primerNombrePr) {
        this.primerNombrePr = primerNombrePr;
    }

    public String getPrimerApellidoPr() {
        return primerApellidoPr;
    }

    public void setPrimerApellidoPr(String primerApellidoPr) {
        this.primerApellidoPr = primerApellidoPr;
    }

    public String getSegundoNombrePr() {
        return segundoNombrePr;
    }

    public void setSegundoNombrePr(String segundoNombrePr) {
        this.segundoNombrePr = segundoNombrePr;
    }

    public String getSegundoApellidoPr() {
        return segundoApellidoPr;
    }

    public void setSegundoApellidoPr(String segundoApellidoPr) {
        this.segundoApellidoPr = segundoApellidoPr;
    }

    public String getCelularPr() {
        return celularPr;
    }

    public void setCelularPr(String celularPr) {
        this.celularPr = celularPr;
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

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idProfesor != null ? idProfesor.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Profesor)) {
            return false;
        }
        Profesor other = (Profesor) object;
        if ((this.idProfesor == null && other.idProfesor != null) || (this.idProfesor != null && !this.idProfesor.equals(other.idProfesor))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.example.ElAulaBot.domain.Profesor[ idProfesor=" + idProfesor + " ]";
    }
    
}
