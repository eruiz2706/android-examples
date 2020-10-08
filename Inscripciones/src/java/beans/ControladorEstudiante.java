/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import logica.Estudiante;

/**
 *
 * @author Eduardo
 */
@Named(value = "controladorEstudiante")
@SessionScoped
public class ControladorEstudiante implements Serializable {

    Estudiante estudiante = new Estudiante();
    List<Estudiante> l_estudiante;
    
    public ControladorEstudiante() {
        estudiante =new Estudiante();
         findAll();
    }

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }

    public List<Estudiante> getL_estudiante() {
        return l_estudiante;
    }

    public void setL_estudiante(List<Estudiante> l_estudiante) {
        this.l_estudiante = l_estudiante;
    }
    
    public List<Estudiante> listarTodos(){
        
        EntityManager em;
        em = estudiante.getEntityManager();
        //Query tipado con el tipo de objeto a extraer
         TypedQuery<Estudiante> consultar = em.createNamedQuery("Estudiante.findAll",Estudiante.class);
         //captura de listado de la consulta
       return  consultar.getResultList();
     
    }
    
    public void findAll(){
        estudiante =new Estudiante();
        EntityManager em;
        em = estudiante.getEntityManager();
        TypedQuery<Estudiante> consultar = em.createNamedQuery("Estudiante.findAll",Estudiante.class);
        
        l_estudiante=  consultar.getResultList();
    }
    
    public String save(){
        
        EntityManager em = estudiante.getEntityManager();
        em.getTransaction().begin();
        em.persist(estudiante);
        em.getTransaction().commit();
        
        estudiante =new Estudiante();
        findAll();
        return "gestionEstudiante";
    }
    
    private Estudiante cargarEstudiante(String value) {
        EntityManager em = estudiante.getEntityManager();
        TypedQuery<Estudiante> consultar = em.createNamedQuery("Estudiante.id", Estudiante.class);
        consultar.setParameter("id",Integer.parseInt(value));
        //System.out.println("holaaa" + consultar.getResultList().get(0).getNombre());
        return consultar.getResultList().get(0);
    }
    
        // conversor para l a clase Iem
    @FacesConverter(forClass = Estudiante.class)
    public static class EstudianteControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            ControladorEstudiante controller = (ControladorEstudiante) facesContext.getApplication()
                    .evaluateExpressionGet(facesContext, "#{controladorEstudiante}", ControladorEstudiante.class);

            return controller.cargarEstudiante(value);
        }

        java.lang.Integer getKey(String value) {
            java.lang.Integer key;
            key = Integer.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Integer value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Estudiante) {
                Estudiante t = (Estudiante) object;
                return getStringKey(t.getId());
            } else {
                return "error";
            }

        }

    }
}
