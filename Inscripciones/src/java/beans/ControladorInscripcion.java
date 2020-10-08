/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.util.List;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.FacesConverter;
import javax.persistence.Converter;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import logica.Estudiante;
import logica.Inscripcion;

/**
 *
 * @author Eduardo
 */
@Named(value = "controladorInscripcion")
@SessionScoped
public class ControladorInscripcion implements Serializable {

    Inscripcion inscripcion = new Inscripcion();
    List<Inscripcion> l_inscripcion;
    
    String b_programa="";
    boolean b_admitidos=false;

    public boolean isB_admitidos() {
        return b_admitidos;
    }

    public void setB_admitidos(boolean b_admitidos) {
        this.b_admitidos = b_admitidos;
    }
    
    public ControladorInscripcion() {
        inscripcion =new Inscripcion();
         findAll();
    }
    
    

    public String getB_programa() {
        return b_programa;
    }

    public void setB_programa(String b_programa) {
        this.b_programa = b_programa;
    }

    
    
    public Inscripcion getInscripcion() {
        return inscripcion;
    }

    public void setInscripcion(Inscripcion inscripcion) {
        this.inscripcion = inscripcion;
    }

    public List<Inscripcion> getL_inscripcion() {
        return l_inscripcion;
    }

    public void setL_inscripcion(List<Inscripcion> l_inscripcion) {
        this.l_inscripcion = l_inscripcion;
    }
    
    
    public List<Inscripcion> listarTodos(){
        
        EntityManager em;
        em = inscripcion.getEntityManager();
        //Query tipado con el tipo de objeto a extraer
         TypedQuery<Inscripcion> consultar = em.createNamedQuery("Inscripcion.findAll",Inscripcion.class);
         //captura de listado de la consulta
       return  consultar.getResultList();
     
    }
    
    public void findAll(){
        inscripcion =new Inscripcion();
        EntityManager em;
        em = inscripcion.getEntityManager();
        TypedQuery<Inscripcion> consultar = em.createNamedQuery("Inscripcion.findAll",Inscripcion.class);
        
        l_inscripcion=  consultar.getResultList();
    }
    
    public String save(){
        
        EntityManager em = inscripcion.getEntityManager();
        em.getTransaction().begin();
        em.persist(inscripcion);
        em.getTransaction().commit();
        
        inscripcion =new Inscripcion();
        findAll();
        return "gestionInscripcion";
    }
    
    private void searchPrograma(){
        EntityManager em;
        em = inscripcion.getEntityManager();
        TypedQuery<Inscripcion> consultar = em.createNamedQuery("Inscripcion.findByPrograma",Inscripcion.class).setParameter("programa",b_programa);
        l_inscripcion=  consultar.getResultList();
    }
    private void searchAdmitidos(){
        EntityManager em;
        em = inscripcion.getEntityManager();
        TypedQuery<Inscripcion> consultar = em.createNamedQuery("Inscripcion.findByAdmitidos",Inscripcion.class).setParameter("programa",b_programa);
        l_inscripcion=  consultar.setMaxResults(5).getResultList();
    }
    
    public String search(){
        
         if(b_admitidos==true){
           searchAdmitidos();
        }else if(!b_programa.equals("")){
            searchPrograma();
        }else{
            findAll();
        }
        
        b_programa="";
        b_admitidos=false;
        return "gestionInscripcion";
   }
   
}
