package com.example.moraga.ev2vmoragaptenorio.Modulos;

public class Productos {
    private int id;
    private String nombre;
    private int precio;
    private int idFoto;

    public Productos(String nombre, int precio) {
        this.nombre = nombre;
        this.precio = precio;
    }

    public String getNombre() {
        return nombre;
    }

    public int getPrecio() {
        return precio;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public int getIdFoto() {
        return idFoto;
    }

    @Override
    public String toString() {
        return "Producto: "+ nombre +" Precio: $" + precio;
    }
}
