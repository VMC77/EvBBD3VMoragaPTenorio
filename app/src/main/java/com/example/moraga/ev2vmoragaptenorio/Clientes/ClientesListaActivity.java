package com.example.moraga.ev2vmoragaptenorio.Clientes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.moraga.ev2vmoragaptenorio.Modulos.BDDHelper;
import com.example.moraga.ev2vmoragaptenorio.Modulos.Clientes;
import com.example.moraga.ev2vmoragaptenorio.R;

import java.util.List;

public class ClientesListaActivity extends AppCompatActivity {

    private BDDHelper helper=new BDDHelper(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clientes);
        List<Clientes> clientes=helper.listaclientes();
        if (clientes==null){
            TextView mensaje=(TextView)findViewById(R.id.mensaje);
            mensaje.setText("No existen clientes ingresados");
        }else {
            ListView lista=(ListView)findViewById(R.id.lista_clientes);
            ArrayAdapter<Clientes> adapter=new ArrayAdapter<Clientes>(this, android.R.layout.simple_list_item_1,clientes);
            lista.setAdapter(adapter);
        }
    }
    public void volver(View view){
        Intent intent=new Intent(this, ClientesActivity.class);
        startActivity(intent);
    }
}
