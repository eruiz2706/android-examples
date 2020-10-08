package com.example.eduardo.agenda;

/**
 * Created by Eduardo on 27/02/2017.
 */

public class Contacto {
    private int     id;
    private String  nombre;
    private String  apellido;
    private String  direccion;
    private String  telefono;
    private String  correo;
    private String  sexo;

    public Contacto(int id, String nombre, String apellido, String direccion, String telefono, String correo, String sexo) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.direccion = direccion;
        this.telefono = telefono;
        this.correo = correo;
        this.sexo = sexo;
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

    public String getSexo() {
        return sexo;
    }

    public String getApellido() {
        return apellido;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }
}
