package eduardovue.vuelo;

/**
 * Created by Eduardo on 05/06/2017.
 */

public class Vuelo {

    static int idgeneral=0;
    static int idgeneral2=0;
    int id;
    String origen;
    String destino;
    String fechaorigen;
    String fechadestino;
    String estado;
    int cantidad;
    double total;

    public static int getIdgeneral2() {
        return idgeneral2;
    }

    public static void setIdgeneral2(int idgeneral2) {
        Vuelo.idgeneral2 = idgeneral2;
    }

    public static int getIdgeneral() {
        return idgeneral;
    }

    public static void setIdgeneral(int idgeneral) {
        Vuelo.idgeneral = idgeneral;
    }

    public Vuelo() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public String getFechaorigen() {
        return fechaorigen;
    }

    public void setFechaorigen(String fechaorigen) {
        this.fechaorigen = fechaorigen;
    }

    public String getFechadestino() {
        return fechadestino;
    }

    public void setFechadestino(String fechadestino) {
        this.fechadestino = fechadestino;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
