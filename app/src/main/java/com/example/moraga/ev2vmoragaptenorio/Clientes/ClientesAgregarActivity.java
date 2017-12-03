package com.example.moraga.ev2vmoragaptenorio.Clientes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.moraga.ev2vmoragaptenorio.Modulos.BDDHelper;
import com.example.moraga.ev2vmoragaptenorio.Modulos.Clientes;
import com.example.moraga.ev2vmoragaptenorio.R;

public class ClientesAgregarActivity extends AppCompatActivity {
    private BDDHelper helper=new BDDHelper(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clientes_agregar);
    }
    public void ingresarClienteClick(View view){
        String rut=((EditText)findViewById(R.id.rut)).getText().toString();
        String nombrelocal=((EditText)findViewById(R.id.nombrelocal)).getText().toString();
        String nombrecontacto=((EditText)findViewById(R.id.nombrecontacto)).getText().toString();
        String direccion=((EditText)findViewById(R.id.direccion)).getText().toString();
        String telefono=((EditText)findViewById(R.id.telefono)).getText().toString();
        int telefonoint=0;
        try{
            telefonoint=Integer.parseInt(telefono);
        }catch (NumberFormatException e){
            Toast toast=Toast.makeText(this, "Rut o Telefono incorrecto", Toast.LENGTH_SHORT);
            toast.show();
            return;
        }
        if(rut.compareTo("")!=0 && nombrelocal.compareTo("")!=0 && nombrecontacto.compareTo("")!=0 && direccion.compareTo("")!=0 && telefono.compareTo("")!=0){
            String estado="ACTIVO";
            Clientes clientes=new Clientes(rut,nombrelocal,nombrecontacto,direccion,telefonoint,estado);
            helper.ingresarCliente(clientes);
            Intent intent=new Intent(this, ClientesActivity.class);
            startActivity(intent);
            Toast toast=Toast.makeText(this,"Cliente "+rut+" ingresado",Toast.LENGTH_SHORT);
            toast.show();
        }
    }
    public void volver(View view){
        Intent intent=new Intent(this, ClientesActivity.class);
        startActivity(intent);
    }
}
