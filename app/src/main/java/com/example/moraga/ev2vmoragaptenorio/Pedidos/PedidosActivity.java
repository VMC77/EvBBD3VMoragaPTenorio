package com.example.moraga.ev2vmoragaptenorio.Pedidos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.moraga.ev2vmoragaptenorio.MenuPrincipalActivity;
import com.example.moraga.ev2vmoragaptenorio.R;

public class PedidosActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedidos);
        AdapterView.OnItemClickListener itemClickListener= new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int posicion, long id) {
                if (posicion==0){
                    Intent intent=new Intent(PedidosActivity.this, PedidosAgregarActivity.class);
                    startActivity(intent);
                }else if (posicion==1){
                    Intent intent=new Intent(PedidosActivity.this, PedidosListaActivity.class);
                    startActivity(intent);
                }else if (posicion==2){
                    Intent intent=new Intent(PedidosActivity.this, PedidosListaActivity.class);
                    startActivity(intent);
                }
            }
        };
        ListView listView = (ListView) findViewById(R.id.opcionesPedidos);
        listView.setOnItemClickListener(itemClickListener);
    }
    public void volver(View view){
        Intent intent=new Intent(this, MenuPrincipalActivity.class);
        startActivity(intent);
    }
}
