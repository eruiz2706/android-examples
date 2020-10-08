package eduardo.agendacontact;

import android.graphics.drawable.Drawable;

/**
 * Created by Eduardo on 10/04/2017.
 */

public class Datos {

    protected Drawable foto;
    protected String    nombre;
    protected String    documento;
    protected int      id;


    public Datos(Drawable foto,String nombre,String documento,int id){
        this.foto   =foto;
        this.nombre =nombre;
        this.documento=documento;

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
