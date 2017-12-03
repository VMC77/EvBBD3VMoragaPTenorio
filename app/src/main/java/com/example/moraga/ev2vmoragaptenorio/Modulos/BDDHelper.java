package com.example.moraga.ev2vmoragaptenorio.Modulos;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Moraga on 02-12-2017.
 */

public class BDDHelper extends SQLiteOpenHelper{
    private static final String DB_NAME="bd_evaluacion.db";
    private static final int DB_VERSION=4;

    public BDDHelper(Context context){
        super(context, DB_NAME, null, DB_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sqlClientes="CREATE TABLE CLIENTES("+"_id INTEGER PRIMARY KEY AUTOINCREMENT, RUT TEXT, NOMBRE_LOCAL TEXT, NOMBRE_CONTACTO TEXT, DIRECCION TEXT, TELEFONO TEXT, ESTADO TEXT);";
        sqLiteDatabase.execSQL(sqlClientes);
        String sqlPedidos="CREATE TABLE PEDIDOS("+"_id INTEGER PRIMARY KEY AUTOINCREMENT, RUT TEXT, PRODUCTOS TEXT, FECHA_ENTREGA TEXT, CANTIDAD INTEGER, TOTAL INTEGER, ENTREGA TEXT);";
        String sqlTotal="CREATE TABLE PEDIDOS_PRODUCTOS("+"_id INTEGER PRIMARY KEY AUTOINCREMENT, PRODUCTO INTEGER, PEDIDO INTEGER, CANTIDAD INTEGER, TOTAL INTEGER);";
        sqLiteDatabase.execSQL(sqlPedidos);
        sqLiteDatabase.execSQL(sqlTotal);
        String sqlProductos="CREATE TABLE PRODUCTOS("+"_id INTEGER PRIMARY KEY AUTOINCREMENT, NOMBRE TEXT, PRECIO INTEGER);";
        sqLiteDatabase.execSQL(sqlProductos);
        sqLiteDatabase.execSQL("INSERT INTO PRODUCTOS(NOMBRE, PRECIO) VALUES('Limones', 4500);");
        sqLiteDatabase.execSQL("INSERT INTO PRODUCTOS(NOMBRE, PRECIO) VALUES('Papas', 5000);");
        sqLiteDatabase.execSQL("INSERT INTO PRODUCTOS(NOMBRE, PRECIO) VALUES('Lechugas', 4000);");
        sqLiteDatabase.execSQL("INSERT INTO PRODUCTOS(NOMBRE, PRECIO) VALUES('Zanahorias', 4100);");
        sqLiteDatabase.execSQL("INSERT INTO PRODUCTOS(NOMBRE, PRECIO) VALUES('Tomates', 4300);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS CLIENTES");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS PRODUCTOS");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS PEDIDOS");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS PEDIDOS_PRODUCTOS");
        onCreate(sqLiteDatabase);
    }
    public List<Clientes> listaclientes(){
        List<Clientes> clientes=new ArrayList<>();
        String sqlTxt="SELECT RUT, NOMBRE_LOCAL, NOMBRE_CONTACTO, DIRECCION, TELEFONO, ESTADO FROM CLIENTES WHERE ESTADO='ACTIVO'";
        try{
            Cursor cursor=getReadableDatabase().rawQuery(sqlTxt,null);
            cursor.moveToFirst();
            do{
                clientes.add(new Clientes(cursor.getString(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getInt(4),cursor.getString(5)));
            }while (cursor.moveToNext());
            cursor.close();

        }catch (CursorIndexOutOfBoundsException e){
            clientes=null;
        }
        return clientes;
    }
    public List<String> listaModificar(SQLiteDatabase sqLiteDatabase){
        List<String> modificar=new ArrayList<>();
        Cursor cursor=sqLiteDatabase.rawQuery("SELECT RUT FROM CLIENTES WHERE ESTADO='ACTIVO'",null);
        cursor.moveToFirst();
        do {
            modificar.add(cursor.getString(0));
        }while (cursor.moveToNext());
        cursor.close();
        sqLiteDatabase.close();
        return  modificar;
    }
    public void ingresarCliente(Clientes clientes){
        ContentValues valores=new ContentValues();
        valores.put("RUT", clientes.getRut());
        valores.put("NOMBRE_LOCAL", clientes.getNombreLocal());
        valores.put("NOMBRE_CONTACTO", clientes.getNombreContacto());
        valores.put("DIRECCION", clientes.getDireccion());
        valores.put("TELEFONO", clientes.getTelefono());
        valores.put("ESTADO", clientes.getEstado());
        getWritableDatabase().insert("CLIENTES",null, valores);
    }
    public String modificarCliente(String RUT,String nombreLocal, String nombreContacto, String direccion, int telefono, String antiguoRut){
        String mensaje="Cliente modificado";
        String sqlTxt="UPDATE CLIENTES SET RUT=?, NOMBRE_LOCAL=?, NOMBRE_CONTACTO=?, DIRECCION=?, TELEFONO=? WHERE RUT=?";
        Object[] argumentos=new Object[]{RUT, nombreLocal, nombreContacto, direccion, telefono, antiguoRut};
        try {
            getWritableDatabase().execSQL(sqlTxt,argumentos);
        }catch (SQLiteException e){
            mensaje="Error al modificar "+e.getMessage();
            return mensaje;
        }
        return mensaje;
    }
    public String eliminarCliente(String RUT){
        String mensaje="Cliente eliminado";
        String sqlTxt="UPDATE CLIENTES SET ESTADO='INACTIVO' WHERE RUT=?";
        Object[] argumentos=new Object[]{RUT};
        try {
            getWritableDatabase().execSQL(sqlTxt,argumentos);
        }catch (SQLiteException e){
            mensaje="Error al eliminar "+e.getMessage();
            return mensaje;
        }
        return mensaje;
    }
    public List<Productos> listaProductos(){
        List<Productos> productos=new ArrayList<>();
        String sqlTxt="SELECT NOMBRE, PRECIO FROM PRODUCTOS";
        try{
            Cursor cursor=getReadableDatabase().rawQuery(sqlTxt,null);
            cursor.moveToFirst();
            do{
                productos.add(new Productos(cursor.getString(0),cursor.getInt(1)));
            }while (cursor.moveToNext());
            cursor.close();

        }catch (CursorIndexOutOfBoundsException e){
            productos=null;
        }
        return productos;
    }
    public List<String> listaProdPed(SQLiteDatabase sqLiteDatabase){
        List<String> prodped=new ArrayList<>();
        List<Integer> cant=new ArrayList<>();
        Cursor cursor=sqLiteDatabase.rawQuery("SELECT NOMBRE, PRECIO FROM PRODUCTOS",null);
        cursor.moveToFirst();
        do {
            prodped.add(cursor.getString(0));
            cant.add(cursor.getInt(1));
        }while (cursor.moveToNext());
        cursor.close();
        sqLiteDatabase.close();
        return prodped;
    }
    public List<String> listaRUT(SQLiteDatabase sqLiteDatabase){
        List<String> rutC=new ArrayList<>();
        Cursor cursor=sqLiteDatabase.rawQuery("SELECT RUT FROM CLIENTES",null);
        cursor.moveToFirst();
        do {
            rutC.add(cursor.getString(0));
        }while (cursor.moveToNext());
        cursor.close();
        sqLiteDatabase.close();
        return rutC;
    }
    public List<Pedidos> listapedidos(){
        List<Pedidos> pedidos=new ArrayList<>();
        String sqlTxt="SELECT RUT, PRODUCTOS, FECHA_ENTREGA, CANTIDAD, TOTAL, ENTREGA FROM PEDIDOS WHERE ENTREGA='SIN ENTREGAR'";
        try{
            Cursor cursor=getReadableDatabase().rawQuery(sqlTxt,null);
            cursor.moveToFirst();
            do{
                pedidos.add(new Pedidos(cursor.getString(0),cursor.getString(1),cursor.getString(2),cursor.getInt(3),cursor.getInt(4),cursor.getString(5)));
            }while (cursor.moveToNext());
            cursor.close();

        }catch (CursorIndexOutOfBoundsException e){
            pedidos=null;
        }
        return pedidos;
    }
    public String agregarProductoTotal(String producto,int cantidad ,int total,String rut){
        String mensaje="Pedido creado";
        String sqlTxt="UPDATE PEDIDOS SET PRODUCTOS=?, CANTIDAD=?, TOTAL=? WHERE RUT=?";
        Object[] argumentos=new Object[]{producto,cantidad,total,rut};
        try {
            getWritableDatabase().execSQL(sqlTxt,argumentos);
        }catch (SQLiteException e){
            mensaje="Error al agregar "+e.getMessage();
            return mensaje;
        }
        return mensaje;
    }
    public String entregaPedido(String rut){
        String mensaje="Pedido entregado";
        String sqlTxt="UPDATE PEDIDOS SET ENTREGA='ENTREGADO' WHERE RUT= ?";
        Object[] argumentos=new Object[]{rut};
        try {
            getWritableDatabase().execSQL(sqlTxt,argumentos);
        }catch (SQLiteException e){
            mensaje="Error al agregar "+e.getMessage();
            return mensaje;
        }
        return mensaje;
    }
    public List<Pedidos> totalPedidossin(){
        List<Pedidos> pedidos=new ArrayList<>();
        String sqlTxt="SELECT TOTAL FROM PEDIDOS WHERE ENTREGA='SIN ENTREGAR'";
        try{
            Cursor cursor=getReadableDatabase().rawQuery(sqlTxt,null);
            cursor.moveToFirst();
            do{
                pedidos.add(new Pedidos(cursor.getString(0),cursor.getString(1),cursor.getString(2),cursor.getInt(3),cursor.getInt(4),cursor.getString(5)));
            }while (cursor.moveToNext());
            cursor.close();
        }catch (CursorIndexOutOfBoundsException e){
            pedidos=null;
        }
        return pedidos;
    }
    public void ingresarPedido(Pedidos pedidos){
        ContentValues valores=new ContentValues();
        valores.put("RUT", pedidos.getClientes_Rut());
        valores.put("PRODUCTOS", pedidos.getProducto());
        valores.put("FECHA_ENTREGA", pedidos.getFecha());
        valores.put("CANTIDAD", pedidos.getCantidad());
        valores.put("TOTAL", pedidos.getTotal());
        valores.put("ENTREGA", pedidos.getEstado());
        getWritableDatabase().insert("PEDIDOS",null,valores);
    }
}
