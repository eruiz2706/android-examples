package eduardo.enfermedades.modelos;

import android.content.ContentValues;

/**
 * Created by Eduardo on 20/05/2017.
 */

public class Medico {

    int     id;
    String  nombre;
    String  especialidad;
    ContentValues values;

    public Medico() {
        values  = new ContentValues();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
        values.put("especialidad",especialidad);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
        values.put("nombre",nombre);
    }

    /*retorna array con los valores de los campos modificados*/
    public ContentValues getValues(){
        return values;
    }
}
