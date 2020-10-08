package eduardo.menu;

import android.graphics.drawable.Drawable;

/**
 * Created by Eduardo on 10/04/2017.
 */

public class Datos {

    protected Drawable foto;
    protected String    nombre;
    protected String    documento;
    protected String    fechan;
    protected int      id;


    public Datos(Drawable foto,String nombre,String documento,int id,String fechan){
        this.foto   =foto;
        this.nombre =nombre;
        this.documento=documento;
        this.fechan=fechan;
    }

    public String getFechan() {
        return fechan;
    }

    public void setFechan(String fechan) {
        this.fechan = fechan;
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

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
