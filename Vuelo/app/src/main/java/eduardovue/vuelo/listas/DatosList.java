package eduardovue.vuelo.listas;

import android.graphics.drawable.Drawable;

/**
 * Created by Eduardo on 21/05/2017.
 */

public class DatosList {

    protected Drawable  foto;
    protected int       id;
    protected String    origen;
    protected String    destino;
    protected String    fechaida;
    protected String    fecharegreso;
    protected String    estado;

    public DatosList(Drawable foto, int id, String origen, String destino,String fechaida,String fecharegreso,String estado) {
        this.foto           =foto;
        this.id             =id;
        this.origen         =origen;
        this.destino        =destino;
        this.fechaida       =fechaida;
        this.fecharegreso   =fecharegreso;
        this.estado         =estado;
    }

    public Drawable getFoto() {
        return foto;
    }

    public void setFoto(Drawable foto) {
        this.foto = foto;
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

    public String getFechaida() {
        return fechaida;
    }

    public void setFechaida(String fechaida) {
        this.fechaida = fechaida;
    }

    public String getFecharegreso() {
        return fecharegreso;
    }

    public void setFecharegreso(String fecharegreso) {
        this.fecharegreso = fecharegreso;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
