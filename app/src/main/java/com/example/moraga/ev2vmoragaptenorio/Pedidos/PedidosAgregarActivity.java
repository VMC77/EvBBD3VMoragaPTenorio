package com.example.moraga.ev2vmoragaptenorio.Pedidos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.moraga.ev2vmoragaptenorio.Modulos.BDDHelper;
import com.example.moraga.ev2vmoragaptenorio.Modulos.Pedidos;
import com.example.moraga.ev2vmoragaptenorio.R;

import java.util.List;

public class PedidosAgregarActivity extends AppCompatActivity {
    private BDDHelper helper=new BDDHelper(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedidos_agregar);
        List<String> rutC=helper.listaRUT(helper.getReadableDatabase());
        Spinner lista2=(Spinner)findViewById(R.id.clientes);
        ArrayAdapter<String> adapter2=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,rutC);
        lista2.setAdapter(adapter2);
        EditText rut=(EditText)findViewById(R.id.rut);
        TextView fechaentrega=(TextView)findViewById(R.id.fechaentrega);

    }
    public void ingresarPedidoClick(View view){
        String rutcliente=((Spinner)findViewById(R.id.clientes)).getSelectedItem().toString();
        String producto=null;
        String fechaentrega=((EditText)findViewById(R.id.agregarfechaentrega)).getText().toString();
        int cantidadint=0;
        int total=0;
        if (rutcliente.compareTo("")!=0 && fechaentrega.compareTo("")!=0){
            String entrega="SIN ENTREGAR";
            Pedidos pedidos=new Pedidos(rutcliente,producto,fechaentrega,cantidadint,total,entrega);
            helper.ingresarPedido(pedidos);
            Intent intent=new Intent(this, PedidosProductosActivity.class);
            intent.putExtra(PedidosProductosActivity.rutcliente,rutcliente);
            startActivity(intent);
            Toast toast=Toast.makeText(this,"INGRESAR CANTIDAD ANTES QUE PRODUCTO",Toast.LENGTH_LONG);
            toast.show();
        }
    }
    public void volver(View view){
        Intent intent=new Intent(this, PedidosActivity.class);
        startActivity(intent);
    }
}
