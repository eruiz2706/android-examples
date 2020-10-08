package eduardo.academico;

/**
 * Created by Eduardo on 17/04/2017.
 */

public class Estudiante {

    private String codigo;
    private String nombre;
    private int programa;

    public Estudiante(String codigo, String nombre, int programa) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.programa = programa;
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

    public int getPrograma() {
        return programa;
    }

    public void setPrograma(int programa) {
        this.programa = programa;
    }
}
