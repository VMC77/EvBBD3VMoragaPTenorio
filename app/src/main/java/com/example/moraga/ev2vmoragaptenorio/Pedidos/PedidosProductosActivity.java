package com.example.moraga.ev2vmoragaptenorio.Pedidos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.moraga.ev2vmoragaptenorio.Modulos.BDDHelper;
import com.example.moraga.ev2vmoragaptenorio.R;

import java.util.List;

public class PedidosProductosActivity extends AppCompatActivity {
    private BDDHelper helper=new BDDHelper(this);
    public static final String rutcliente="rut";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedidos_productos);
        final String rut=(String) getIntent().getExtras().get(rutcliente);
        List<String> prodped=helper.listaProdPed(helper.getReadableDatabase());
        ListView lista=(ListView)findViewById(R.id.productos);
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,prodped);
        TextView PT=(TextView)findViewById(R.id.PT);
        PT.setText("Total: ");
        TextView cantidad=(TextView)findViewById(R.id.cantidad);
        AdapterView.OnItemClickListener itemClickListener=new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String producto=parent.getItemAtPosition(position).toString();
                EditText cant=(EditText)findViewById(R.id.agregarcantidad);
                int cantidad=Integer.parseInt(cant.getText().toString());
                Intent intent=new Intent(PedidosProductosActivity.this, PedidosActivity.class);
                switch (producto){
                    case "Limones":
                        int total=cantidad*4500;
                        String preciototal=Integer.toString(total);
                        TextView PT=(TextView)findViewById(R.id.PT);
                        PT.setText("Total: "+preciototal);
                        helper.agregarProductoTotal(producto,cantidad,total,rut);
                        startActivity(intent);
                        break;
                    case "Papas":
                        total=cantidad*5000;
                        preciototal=Integer.toString(total);
                        PT=(TextView)findViewById(R.id.PT);
                        PT.setText("Total: "+preciototal);
                        helper.agregarProductoTotal(producto,cantidad,total,rut);
                        Intent intent1=new Intent(PedidosProductosActivity.this, PedidosActivity.class);
                        startActivity(intent);
                        break;
                    case "Lechugas":
                        total=cantidad*4000;
                        preciototal=Integer.toString(total);
                        PT=(TextView)findViewById(R.id.PT);
                        PT.setText("Total: "+preciototal);
                        helper.agregarProductoTotal(producto,cantidad,total,rut);
                        startActivity(intent);
                        break;
                    case "Zanahorias":
                        total=cantidad*4100;
                        preciototal=Integer.toString(total);
                        PT=(TextView)findViewById(R.id.PT);
                        PT.setText("Total: "+preciototal);
                        helper.agregarProductoTotal(producto,cantidad,total,rut);
                        startActivity(intent);
                        break;
                    case "Tomates":
                        total=cantidad*4300;
                        preciototal=Integer.toString(total);
                        PT=(TextView)findViewById(R.id.PT);
                        PT.setText("Total: "+preciototal);
                        helper.agregarProductoTotal(producto,cantidad,total,rut);
                        startActivity(intent);
                        break;
                }
            }
        };
        lista.setAdapter(adapter);
        lista.setOnItemClickListener(itemClickListener);
    }
}
