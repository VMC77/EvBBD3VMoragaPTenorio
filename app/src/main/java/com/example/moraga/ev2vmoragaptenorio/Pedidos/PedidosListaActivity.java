package com.example.moraga.ev2vmoragaptenorio.Pedidos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.moraga.ev2vmoragaptenorio.Modulos.BDDHelper;
import com.example.moraga.ev2vmoragaptenorio.Modulos.Pedidos;
import com.example.moraga.ev2vmoragaptenorio.R;

import java.lang.reflect.Array;
import java.util.List;

public class PedidosListaActivity extends AppCompatActivity {
    private BDDHelper helper=new BDDHelper(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedidos_lista);
        List<Pedidos> pedidos=helper.listapedidos();
        if (pedidos==null){
            TextView mensaje=(TextView)findViewById(R.id.mensaje);
            mensaje.setText("No existen pedidos sin entregar");
        }else {
            ListView lista=(ListView)findViewById(R.id.lista_pedidos);
            ArrayAdapter<Pedidos> adapter=new ArrayAdapter<Pedidos>(this, android.R.layout.simple_list_item_1,pedidos);
            lista.setAdapter(adapter);
        }
    }
    public void volver(View view){
        Intent intent=new Intent(this, PedidosActivity.class);
        startActivity(intent);
    }
}
