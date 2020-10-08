/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Persistence;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Eduardo
 */
@Entity
@Table(name = "inscripcion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Inscripcion.findAll", query = "SELECT i FROM Inscripcion i")
    , @NamedQuery(name = "Inscripcion.findById", query = "SELECT i FROM Inscripcion i WHERE i.id = :id")
    , @NamedQuery(name = "Inscripcion.findByFecha", query = "SELECT i FROM Inscripcion i WHERE i.fecha = :fecha")
    , @NamedQuery(name = "Inscripcion.findByPrograma", query = "SELECT i FROM Inscripcion i WHERE i.programa = :programa")
    , @NamedQuery(name = "Inscripcion.findByAdmitidos", query = "SELECT i FROM Inscripcion i WHERE i.programa = :programa order by i.puntaje desc")
    , @NamedQuery(name = "Inscripcion.findByPin", query = "SELECT i FROM Inscripcion i WHERE i.pin = :pin")
    , @NamedQuery(name = "Inscripcion.findByPuntaje", query = "SELECT i FROM Inscripcion i WHERE i.puntaje = :puntaje")})
public class Inscripcion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Size(max = 100)
    @Column(name = "programa")
    private String programa;
    @Size(max = 50)
    @Column(name = "pin")
    private String pin;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "puntaje")
    private Double puntaje;
    @JoinColumn(name = "id_estudiante", referencedColumnName = "id")
    @ManyToOne
    private Estudiante idEstudiante;

    public Inscripcion() {
    }

    public Inscripcion(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getPrograma() {
        return programa;
    }

    public void setPrograma(String programa) {
        this.programa = programa;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public Double getPuntaje() {
        return puntaje;
    }

    public void setPuntaje(Double puntaje) {
        this.puntaje = puntaje;
    }

    public Estudiante getIdEstudiante() {
        return idEstudiante;
    }

    public void setIdEstudiante(Estudiante idEstudiante) {
        this.idEstudiante = idEstudiante;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Inscripcion)) {
            return false;
        }
        Inscripcion other = (Inscripcion) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "logica.Inscripcion[ id=" + id + " ]";
    }
    
    
    public EntityManager getEntityManager(){   
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("InscripcionesPU");
        EntityManager em = emf.createEntityManager();
        return em;
    }
}
