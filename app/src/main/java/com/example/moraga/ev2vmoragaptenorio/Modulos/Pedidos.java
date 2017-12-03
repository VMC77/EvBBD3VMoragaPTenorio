package com.example.moraga.ev2vmoragaptenorio.Modulos;

import java.util.List;

public class Pedidos {
    private int pedido;
    private String Clientes_rut;
    private String producto;
    private List<String> productos;
    private String fecha;
    private int cantidad;
    private String estado;
    private int total;
    public Pedidos(String Clientes_rut, String producto, String fecha, int cantidad,int total , String estado) {
        this.Clientes_rut = Clientes_rut;
        this.producto = producto;
        this.fecha = fecha;
        this.cantidad=cantidad;
        this.total=total;
        this.estado=estado;
    }
    public int getPedido() {
        return this.pedido;
    }

    public void setPedido(int pedido) {
        this.pedido = pedido;
    }

    public String getClientes_Rut() {
        return Clientes_rut;
    }

    public List<String> getProductos() {
        return productos;
    }

    public String getProducto() {
        return producto;
    }

    public int getTotal() {
        return total;
    }

    public String getFecha() {
        return fecha;
    }

    public int getCantidad() {
        return cantidad;
    }

    public String getEstado() {
        return estado;
    }

    @Override
    public String toString() {
        return "Pedido: Cliente: "+Clientes_rut+" Producto: "+producto+" Fecha: "+fecha+" Cant: "+cantidad+" Total: "+total;
    }
}
