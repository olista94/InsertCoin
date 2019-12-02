package com.example.insertcoin;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Button btEntrar = findViewById(R.id.btEntrar);
        btEntrar.setOnClickListener((view)-> {
            String usuario = ((EditText) findViewById(R.id.login)).getText().toString();
            String pass = ((EditText) findViewById(R.id.Contraseña)).getText().toString();
            if(usuario.equals(/*Concetarse a la BD para mirar usuarios*/) && pass.equals(/*Conectarse a la BD para comprobar la contraseña de ese usuario*/)){
                startActivity(new Intent(this, Juegos_SHOWALL.this);
            }
            else{
                Toast.makeText(getApplicationContext(),"Usuario incorrecto", Toast.LENGTH_SHORT).show();
            }
        })
        Button btRegistarse = findViewById(R.id.btRegistrarse);
                btRegistarse.setOnClickListener((view);
    }
}
