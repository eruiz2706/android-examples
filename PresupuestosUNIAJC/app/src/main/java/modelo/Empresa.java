package modelo;

/**
 * Created by jhonyrenteria on 13/05/17.
 */

public class Empresa {
    private int codigo;
    private String nit;
    private String nombre;
    private String razonSocial;
    private String sector;
    private int numEmpleados;
    private String descripcion;

    public Empresa() {
    }

    public Empresa(int codigo, String nit, String nombre, String razonSocial, String sector, int numEmpleados, String descripcion) {
        this.codigo = codigo;
        this.nit = nit;
        this.nombre = nombre;
        this.razonSocial = razonSocial;
        this.sector = sector;
        this.numEmpleados = numEmpleados;
        this.descripcion = descripcion;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    public int getNumEmpleados() {
        return numEmpleados;
    }

    public void setNumEmpleados(int numEmpleados) {
        this.numEmpleados = numEmpleados;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
