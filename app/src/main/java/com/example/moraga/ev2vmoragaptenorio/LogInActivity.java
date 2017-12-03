package com.example.moraga.ev2vmoragaptenorio;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LogInActivity extends AppCompatActivity {
    Button login;
    EditText username, password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        login=(Button)findViewById(R.id.btnlogin);
        username=(EditText) findViewById(R.id.txtuser);
        password=(EditText) findViewById(R.id.txtpass);
        login.setOnClickListener(new View.OnClickListener(){

            public void onClick (View v) {
                login();

            }
        });
    }
    public void login(){
        String user=username.getText().toString().trim();
        String pass=password.getText().toString().trim();
        if (user.equals("vmoraga") && pass.equals("admin")) {
            Toast.makeText(this, "Nombre de usuario y contraseña correcto", Toast.LENGTH_LONG).show();
            Intent intent=new Intent(this, MenuPrincipalActivity.class);
            startActivity(intent);
        }else if (user.equals("ptenorio") && pass.equals("admin")){
            Toast.makeText(this, "Nombre de usuario y contraseña correcto", Toast.LENGTH_LONG).show();
            Intent intent=new Intent(this, MenuPrincipalActivity.class);
            startActivity(intent);
        }else {
            Toast.makeText (this, "Nombre de usuario y contraseña incorrecto", Toast.LENGTH_LONG).show();
            EditText u=(EditText)findViewById(R.id.txtuser);
            EditText p=(EditText)findViewById(R.id.txtpass);
            u.setText("");
            p.setText("");
        }
    }

}
