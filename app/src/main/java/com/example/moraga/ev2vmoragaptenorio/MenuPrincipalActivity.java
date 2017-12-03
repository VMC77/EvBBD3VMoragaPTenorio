package com.example.moraga.ev2vmoragaptenorio;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.moraga.ev2vmoragaptenorio.Clientes.ClientesActivity;
import com.example.moraga.ev2vmoragaptenorio.Pedidos.PedidosActivity;
import com.example.moraga.ev2vmoragaptenorio.Productos.ProductosActivity;

public class MenuPrincipalActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> listView, View v, int posicion, long id) {
                if (posicion == 2) {
                    Intent intent = new Intent(MenuPrincipalActivity.this, ProductosActivity.class);
                    startActivity(intent);
                }else if(posicion==0){
                    Intent intent=new Intent(MenuPrincipalActivity.this, ClientesActivity.class);
                    startActivity(intent);
                }else if (posicion==1){
                    Intent intent=new Intent(MenuPrincipalActivity.this, PedidosActivity.class);
                    startActivity(intent);
                }
            }
        };
        ListView listView = (ListView) findViewById(R.id.menuOpciones);
        listView.setOnItemClickListener(itemClickListener);
    }
}
