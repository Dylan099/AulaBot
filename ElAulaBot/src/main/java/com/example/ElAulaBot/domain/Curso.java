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
@Table(name = "curso")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Curso.findAll", query = "SELECT c FROM Curso c"),
    @NamedQuery(name = "Curso.findByIdCurso", query = "SELECT c FROM Curso c WHERE c.idCurso = :idCurso"),
    @NamedQuery(name = "Curso.findByFechaCreacion", query = "SELECT c FROM Curso c WHERE c.fechaCreacion = :fechaCreacion"),
    @NamedQuery(name = "Curso.findByNombre", query = "SELECT c FROM Curso c WHERE c.nombre = :nombre"),
    @NamedQuery(name = "Curso.findByCodigoCurso", query = "SELECT c FROM Curso c WHERE c.codigoCurso = :codigoCurso"),
    @NamedQuery(name = "Curso.findByTxuser", query = "SELECT c FROM Curso c WHERE c.txuser = :txuser"),
    @NamedQuery(name = "Curso.findByTxhost", query = "SELECT c FROM Curso c WHERE c.txhost = :txhost"),
    @NamedQuery(name = "Curso.findByTxdate", query = "SELECT c FROM Curso c WHERE c.txdate = :txdate"),
    @NamedQuery(name = "Curso.findByStatus", query = "SELECT c FROM Curso c WHERE c.status = :status")})
public class Curso implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idCurso")
    private Integer idCurso;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "fechaCreacion")
    private String fechaCreacion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "codigoCurso")
    private String codigoCurso;
    @Size(max = 50)
    @Column(name = "txuser")
    private String txuser;
    @Size(max = 50)
    @Column(name = "txhost")
    private String txhost;
    @Column(name = "txdate")
    @Temporal(TemporalType.DATE)
    private Date txdate;
    @Column(name = "status")
    private Integer status;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cursoidCurso")
    private Collection<Horario> horarioCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cursoidCurso")
    private Collection<Anuncio> anuncioCollection;
    @JoinColumn(name = "Profesor_idProfesor", referencedColumnName = "idProfesor")
    @ManyToOne(optional = false)
    private Profesor profesoridProfesor;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cursoidCurso")
    private Collection<CursoHasEstudiante> cursoHasEstudianteCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cursoidCurso")
    private Collection<Examen> examenCollection;

    public Curso() {
    }

    public Curso(Integer idCurso) {
        this.idCurso = idCurso;
    }

    public Curso(Integer idCurso, String fechaCreacion, String nombre, String codigoCurso) {
        this.idCurso = idCurso;
        this.fechaCreacion = fechaCreacion;
        this.nombre = nombre;
        this.codigoCurso = codigoCurso;
    }

    public Integer getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(Integer idCurso) {
        this.idCurso = idCurso;
    }

    public String getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(String fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCodigoCurso() {
        return codigoCurso;
    }

    public void setCodigoCurso(String codigoCurso) {
        this.codigoCurso = codigoCurso;
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }



    @XmlTransient
    public Collection<Horario> getHorarioCollection() {
        return horarioCollection;
    }

    public void setHorarioCollection(Collection<Horario> horarioCollection) {
        this.horarioCollection = horarioCollection;
    }

    @XmlTransient
    public Collection<Anuncio> getAnuncioCollection() {
        return anuncioCollection;
    }

    public void setAnuncioCollection(Collection<Anuncio> anuncioCollection) {
        this.anuncioCollection = anuncioCollection;
    }

    public Profesor getProfesoridProfesor() {
        return profesoridProfesor;
    }

    public void setProfesoridProfesor(Profesor profesoridProfesor) {
        this.profesoridProfesor = profesoridProfesor;
    }

    @XmlTransient
    public Collection<CursoHasEstudiante> getCursoHasEstudianteCollection() {
        return cursoHasEstudianteCollection;
    }

    public void setCursoHasEstudianteCollection(Collection<CursoHasEstudiante> cursoHasEstudianteCollection) {
        this.cursoHasEstudianteCollection = cursoHasEstudianteCollection;
    }

    @XmlTransient
    public Collection<Examen> getExamenCollection() {
        return examenCollection;
    }

    public void setExamenCollection(Collection<Examen> examenCollection) {
        this.examenCollection = examenCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCurso != null ? idCurso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Curso)) {
            return false;
        }
        Curso other = (Curso) object;
        if ((this.idCurso == null && other.idCurso != null) || (this.idCurso != null && !this.idCurso.equals(other.idCurso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.example.ElAulaBot.domain.Curso[ idCurso=" + idCurso + " ]";
    }
    
}
