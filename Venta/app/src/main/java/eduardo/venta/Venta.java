package eduardo.venta;

/**
 * Created by Eduardo on 06/03/2017.
 */

public class Venta {

    private String  cccliente;
    private String  nombreclient;
    private String  telefono;
    private String  direccion;
    private double  valorbruto;
    private double  bono;



    public Venta(String cccliente, String nombreclient, String telefono, String direccion, double valorbruto, double bono) {
        this.cccliente = cccliente;
        this.nombreclient = nombreclient;
        this.telefono = telefono;
        this.direccion = direccion;
        this.valorbruto = valorbruto;
        this.bono = bono;
    }

    public String getCccliente() {
        return cccliente;
    }

    public String getNombreclient() {
        return nombreclient;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public double getValorbruto() {
        return valorbruto;
    }

    public double getBono() {
        return bono;
    }

    public void setCccliente(String cccliente) {
        this.cccliente = cccliente;
    }

    public void setNombreclient(String nombreclient) {
        this.nombreclient = nombreclient;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setValorbruto(double valorbruto) {
        this.valorbruto = valorbruto;
    }

    public void setBono(double bono) {
        this.bono = bono;
    }
}


