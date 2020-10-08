package eduardo.enfermedades.helpers.listgeneric;

import android.graphics.drawable.Drawable;

/**
 * Created by Eduardo on 21/05/2017.
 */

public class DatosList {

    protected Drawable  foto;
    protected int       id;
    protected String    nombre;
    protected String    descripcion;
    protected String    descripcion2;
    protected String    descripcion3;

    public DatosList(Drawable foto, int id, String nombre, String descripcion) {
        this.foto = foto;
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public DatosList(Drawable foto, int id, String nombre, String descripcion,String descripcion2) {
        this.foto = foto;
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.descripcion2 = descripcion2;
    }

    public DatosList(Drawable foto, int id, String nombre, String descripcion,String descripcion2,String descripcion3) {
        this.foto = foto;
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.descripcion2 = descripcion2;
        this.descripcion3 = descripcion3;
    }

    public Drawable getFoto() {
        return foto;
    }

    public void setFoto(Drawable foto) {
        this.foto = foto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescripcion2() {
        return descripcion2;
    }

    public void setDescripcion2(String descripcion2) {
        this.descripcion2 = descripcion2;
    }

    public String getDescripcion3() {
        return descripcion3;
    }

    public void setDescripcion3(String descripcion3) {
        this.descripcion3 = descripcion3;
    }
}
