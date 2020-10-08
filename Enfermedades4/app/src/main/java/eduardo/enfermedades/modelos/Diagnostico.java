package eduardo.enfermedades.modelos;

import android.content.ContentValues;

/**
 * Created by Eduardo on 21/05/2017.
 */

public class Diagnostico {

    int id;
    int id_medico;
    int id_enfermedad;
    String observaciones;
    String descripcion;

    ContentValues values;

    public Diagnostico() {
        values = new ContentValues();
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

    public int getId_enfermedad() {
        return id_enfermedad;
    }

    public void setId_enfermedad(int id_enfermedad) {
        this.id_enfermedad = id_enfermedad;
    }

    public int getId_medico() {
        return id_medico;
    }

    public void setId_medico(int id_medico) {
        this.id_medico = id_medico;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public ContentValues getValues() {
        return values;
    }

    public void setValues(ContentValues values) {
        this.values = values;
    }
}