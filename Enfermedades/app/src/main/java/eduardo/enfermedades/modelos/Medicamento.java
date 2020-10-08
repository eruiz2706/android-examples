package eduardo.enfermedades.modelos;

import android.content.ContentValues;

/**
 * Created by Eduardo on 21/05/2017.
 */

public class Medicamento {

    int     id;
    String  nombre;
    ContentValues values;

    public Medicamento() {
        values  = new ContentValues();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
