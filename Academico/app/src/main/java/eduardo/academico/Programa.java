package eduardo.academico;

/**
 * Created by Eduardo on 17/04/2017.
 */

public class Programa {

    private String  codigo;
    private String  nombre;
    private String  duracion;

    public Programa(String codigo, String nombre,String duracion) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.duracion = duracion;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDuracion() {
        return duracion;
    }

    public void setDuracion(String duracion) {
        this.duracion = duracion;
    }
}
