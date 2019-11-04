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
@Table(name = "horario")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Horario.findAll", query = "SELECT h FROM Horario h"),
    @NamedQuery(name = "Horario.findByIdHorario", query = "SELECT h FROM Horario h WHERE h.idHorario = :idHorario"),
    @NamedQuery(name = "Horario.findByHoraEntrada", query = "SELECT h FROM Horario h WHERE h.horaEntrada = :horaEntrada"),
    @NamedQuery(name = "Horario.findByHoraSalida", query = "SELECT h FROM Horario h WHERE h.horaSalida = :horaSalida"),
    @NamedQuery(name = "Horario.findByDia", query = "SELECT h FROM Horario h WHERE h.dia = :dia"),
    @NamedQuery(name = "Horario.findByTxuser", query = "SELECT h FROM Horario h WHERE h.txuser = :txuser"),
    @NamedQuery(name = "Horario.findByTxhost", query = "SELECT h FROM Horario h WHERE h.txhost = :txhost"),
    @NamedQuery(name = "Horario.findByTxdate", query = "SELECT h FROM Horario h WHERE h.txdate = :txdate"),
    @NamedQuery(name = "Horario.findByStatus", query = "SELECT h FROM Horario h WHERE h.status = :status")})
public class Horario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idHorario")
    private Integer idHorario;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "horaEntrada")
    private String horaEntrada;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "horaSalida")
    private String horaSalida;
    @Size(max = 50)
    @Column(name = "dia")
    private String dia;
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

    public Horario() {
    }

    public Horario(Integer idHorario) {
        this.idHorario = idHorario;
    }

    public Horario(Integer idHorario, String horaEntrada, String horaSalida, String txuser, String txhost, Date txdate, int status) {
        this.idHorario = idHorario;
        this.horaEntrada = horaEntrada;
        this.horaSalida = horaSalida;
        this.txuser = txuser;
        this.txhost = txhost;
        this.txdate = txdate;
        this.status = status;
    }

    public Integer getIdHorario() {
        return idHorario;
    }

    public void setIdHorario(Integer idHorario) {
        this.idHorario = idHorario;
    }

    public String getHoraEntrada() {
        return horaEntrada;
    }

    public void setHoraEntrada(String horaEntrada) {
        this.horaEntrada = horaEntrada;
    }

    public String getHoraSalida() {
        return horaSalida;
    }

    public void setHoraSalida(String horaSalida) {
        this.horaSalida = horaSalida;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
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
        hash += (idHorario != null ? idHorario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Horario)) {
            return false;
        }
        Horario other = (Horario) object;
        if ((this.idHorario == null && other.idHorario != null) || (this.idHorario != null && !this.idHorario.equals(other.idHorario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.example.ElAulaBot.domain.Horario[ idHorario=" + idHorario + " ]";
    }
    
}
