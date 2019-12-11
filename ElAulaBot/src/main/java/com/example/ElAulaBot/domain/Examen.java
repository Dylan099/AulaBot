/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.ElAulaBot.domain;

import java.io.Serializable;
import java.math.BigDecimal;
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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "examen")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Examen.findAll", query = "SELECT e FROM Examen e"),
    @NamedQuery(name = "Examen.findByIdExamen", query = "SELECT e FROM Examen e WHERE e.idExamen = :idExamen"),
    @NamedQuery(name = "Examen.findByTitulo", query = "SELECT e FROM Examen e WHERE e.titulo = :titulo"),
    @NamedQuery(name = "Examen.findByPuntuacion", query = "SELECT e FROM Examen e WHERE e.puntuacion = :puntuacion"),
    @NamedQuery(name = "Examen.findByFechaPublicacionEx", query = "SELECT e FROM Examen e WHERE e.fechaPublicacionEx = :fechaPublicacionEx"),
    @NamedQuery(name = "Examen.findByTxuser", query = "SELECT e FROM Examen e WHERE e.txuser = :txuser"),
    @NamedQuery(name = "Examen.findByTxhost", query = "SELECT e FROM Examen e WHERE e.txhost = :txhost"),
    @NamedQuery(name = "Examen.findByTxdate", query = "SELECT e FROM Examen e WHERE e.txdate = :txdate"),
    @NamedQuery(name = "Examen.findByStatus", query = "SELECT e FROM Examen e WHERE e.status = :status")})
public class Examen implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_examen")
    private Integer idExamen;
    @Size(max = 100)
    @Column(name = "titulo")
    private String titulo;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "puntuacion")
    private BigDecimal puntuacion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_publicacion_ex")
    @Temporal(TemporalType.DATE)
    private Date fechaPublicacionEx;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idExamen", fetch = FetchType.LAZY)
    private Collection<EstudianteHasExamen> estudianteHasExamenCollection;
    @JoinColumn(name = "id_curso", referencedColumnName = "id_curso")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Curso idCurso;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idExamen", fetch = FetchType.LAZY)
    private Collection<Pregunta> preguntaCollection;

    public Examen() {
    }

    public Examen(Integer idExamen) {
        this.idExamen = idExamen;
    }

    public Examen(Integer idExamen, Date fechaPublicacionEx, String txuser, String txhost, Date txdate, int status) {
        this.idExamen = idExamen;
        this.fechaPublicacionEx = fechaPublicacionEx;
        this.txuser = txuser;
        this.txhost = txhost;
        this.txdate = txdate;
        this.status = status;
    }

    public Integer getIdExamen() {
        return idExamen;
    }

    public void setIdExamen(Integer idExamen) {
        this.idExamen = idExamen;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public BigDecimal getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(BigDecimal puntuacion) {
        this.puntuacion = puntuacion;
    }

    public Date getFechaPublicacionEx() {
        return fechaPublicacionEx;
    }

    public void setFechaPublicacionEx(Date fechaPublicacionEx) {
        this.fechaPublicacionEx = fechaPublicacionEx;
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
    public Collection<EstudianteHasExamen> getEstudianteHasExamenCollection() {
        return estudianteHasExamenCollection;
    }

    public void setEstudianteHasExamenCollection(Collection<EstudianteHasExamen> estudianteHasExamenCollection) {
        this.estudianteHasExamenCollection = estudianteHasExamenCollection;
    }

    public Curso getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(Curso idCurso) {
        this.idCurso = idCurso;
    }

    @XmlTransient
    public Collection<Pregunta> getPreguntaCollection() {
        return preguntaCollection;
    }

    public void setPreguntaCollection(Collection<Pregunta> preguntaCollection) {
        this.preguntaCollection = preguntaCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idExamen != null ? idExamen.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Examen)) {
            return false;
        }
        Examen other = (Examen) object;
        if ((this.idExamen == null && other.idExamen != null) || (this.idExamen != null && !this.idExamen.equals(other.idExamen))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.example.ElAulaBot.domain.Examen[ idExamen=" + idExamen + " ]";
    }
    
}
