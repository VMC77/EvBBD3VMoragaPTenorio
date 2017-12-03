package com.example.moraga.ev2vmoragaptenorio.Productos;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.moraga.ev2vmoragaptenorio.MenuPrincipalActivity;
import com.example.moraga.ev2vmoragaptenorio.Modulos.BDDHelper;
import com.example.moraga.ev2vmoragaptenorio.Modulos.Productos;
import com.example.moraga.ev2vmoragaptenorio.R;

import java.util.List;

public class ProductosActivity extends AppCompatActivity {
    private BDDHelper helper=new BDDHelper(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productos);
        List<Productos> productos=helper.listaProductos();
        ListView lista=(ListView)findViewById(R.id.lista_productos);
        ArrayAdapter<Productos> adapter=new ArrayAdapter<Productos>(this, android.R.layout.simple_list_item_1,productos);
        lista.setAdapter(adapter);
        }

    public void volver(View view){
        Intent intent=new Intent(this, MenuPrincipalActivity.class);
        startActivity(intent);
    }
    }
