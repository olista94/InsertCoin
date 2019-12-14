
package com.example.insertcoin;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {

    GestorBD bd;
    SQLiteDatabase baseDatos;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Button btEntrar = findViewById(R.id.btEntrar);
        bd = new GestorBD(getApplicationContext(),"insertcoin48",null,1);
        baseDatos = bd.getWritableDatabase();

        btEntrar.setOnClickListener( new View.OnClickListener(){
            public void onClick(View v){
            String usu = ((EditText) findViewById(R.id.login)).getText().toString();
            String pass = ((EditText) findViewById(R.id.Contraseña)).getText().toString();

            usuarios u = new usuarios(usu,pass);

            if(bd.comprobarLogin(baseDatos, u) == true ){
                Intent i = new Intent(getApplicationContext(),Juegos_SHOWALL.class);
                VariablesGlobales.setUsuario(usu);
                VariablesGlobales.setBaseDatos(bd);
                startActivity(i);

            }
            else{
                Toast.makeText(getApplicationContext(),VariablesGlobales.getMensajeBD(), Toast.LENGTH_LONG).show();
            }
        }});


        Button btRegistarse = findViewById(R.id.btRegistrarse);
        btRegistarse.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bd = new GestorBD(getApplicationContext(),"insertcoin48",null,1);
                baseDatos = bd.getWritableDatabase();
                Intent i = new Intent(getApplicationContext(),Registro.class);
                VariablesGlobales.setBaseDatos(bd);
                startActivity(i);
            }
        }));
    }
}
