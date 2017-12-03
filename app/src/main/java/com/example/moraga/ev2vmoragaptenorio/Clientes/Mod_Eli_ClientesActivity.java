package com.example.moraga.ev2vmoragaptenorio.Clientes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.moraga.ev2vmoragaptenorio.Modulos.BDDHelper;
import com.example.moraga.ev2vmoragaptenorio.Modulos.Clientes;
import com.example.moraga.ev2vmoragaptenorio.R;

import java.util.List;

public class Mod_Eli_ClientesActivity extends AppCompatActivity {
    private BDDHelper helper=new BDDHelper(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mod__eli__clientes);
        List<String> modificar=helper.listaModificar(helper.getReadableDatabase());
        Spinner lista=(Spinner)findViewById(R.id.modificarClientes);
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,modificar);
        lista.setAdapter(adapter);
    }
    public void editarClienteClick(View view){
        String rutAntiguo=((Spinner)findViewById(R.id.modificarClientes)).getSelectedItem().toString();
        String rutCliente=((EditText)findViewById(R.id.editarRutCliente)).getText().toString();
        String nombrelocal=((EditText)findViewById(R.id.editarNombrelocalCliente)).getText().toString();
        String nombrecontacto=((EditText)findViewById(R.id.editarNombrecontactoCliente)).getText().toString();
        String direccion=((EditText)findViewById(R.id.editarDireccionCliente)).getText().toString();
        String telefono=((EditText)findViewById(R.id.editarTelefonoCliente)).getText().toString();
        int telefonoint=0;
        try {
            telefonoint=Integer.parseInt(telefono);
        }catch (NumberFormatException e){
            Toast toast=Toast.makeText(this, "Telefono incorrecto", Toast.LENGTH_SHORT);
            toast.show();
            return;
        }
        helper.modificarCliente(rutCliente,nombrelocal,nombrecontacto,direccion,telefonoint,rutAntiguo);
        Toast toast=Toast.makeText(this, "Cliente: "+rutAntiguo+" modificado", Toast.LENGTH_SHORT);
        toast.show();
        Intent intent=new Intent(Mod_Eli_ClientesActivity.this, ClientesActivity.class);
        startActivity(intent);
    }
    public void eliminarClienteClick(View view){
        String rutAntiguo=((Spinner)findViewById(R.id.modificarClientes)).getSelectedItem().toString();
        helper.eliminarCliente(rutAntiguo);
        Toast toast=Toast.makeText(this, "Cliente: "+rutAntiguo+" eliminado", Toast.LENGTH_SHORT);
        toast.show();
        Intent intent=new Intent(Mod_Eli_ClientesActivity.this, ClientesActivity.class);
        startActivity(intent);
    }
    public void volver(View view){
        Intent intent=new Intent(this, ClientesActivity.class);
        startActivity(intent);
    }
}
