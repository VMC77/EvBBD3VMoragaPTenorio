package com.example.moraga.ev2vmoragaptenorio.Modulos;

public class Clientes {
    private int id;
    private String rut;
    private String nombreLocal;
    private String nombreContacto;
    private String direccion;
    private int telefono;
    private String estado;

    public Clientes(String rut, String nombreLocal, String nombreContacto, String direccion, int telefono, String estado) {
        this.rut = rut;
        this.nombreLocal = nombreLocal;
        this.nombreContacto = nombreContacto;
        this.direccion = direccion;
        this.telefono = telefono;
        this.estado = estado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public String getNombreLocal() {
        return nombreLocal;
    }

    public String getNombreContacto() {
        return nombreContacto;
    }

    public void setNombreContacto(String nombreContacto) {
        this.nombreContacto = nombreContacto;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void setNombreLocal(String nombreLocal) {
        this.nombreLocal = nombreLocal;
    }

    @Override
    public String toString() {
        return "Cliente:" + rut + ", Local:" + nombreLocal + ", Contacto:" + nombreContacto + ", Direccion:" + direccion + ", Telefono:" + telefono;
    }
}

