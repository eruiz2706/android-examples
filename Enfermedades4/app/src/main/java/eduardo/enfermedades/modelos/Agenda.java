package eduardo.enfermedades.modelos;

import android.content.ContentValues;

/**
 * Created by Eduardo on 21/05/2017.
 */

public class Agenda {

    int     id;
    String  descripcion;
    String  fecha;
    int     recordatorio;
    int     id_medico;
    String  fechar;
    ContentValues values;

    public Agenda() {
        values  = new ContentValues();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
        values.put("descripcion",descripcion);
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
        values.put("fecha",fecha);
    }

    public int getRecordatorio() {
        return recordatorio;
    }

    public void setRecordatorio(int recordatorio) {
        this.recordatorio = recordatorio;
        values.put("recordatorio",recordatorio);
    }

    public String getFechar() {
        return fechar;
    }

    public void setFechar(String fechar) {
        this.fechar = fechar;
        values.put("fechar",fechar);
    }

    public int getId_medico() {
        return id_medico;
    }

    public void setId_medico(int id_medico) {
        values.put("id_medico",id_medico);
        this.id_medico = id_medico;
    }

    public ContentValues getValues() {
        return values;
    }

    public void setValues(ContentValues values) {
        this.values = values;
    }
}
