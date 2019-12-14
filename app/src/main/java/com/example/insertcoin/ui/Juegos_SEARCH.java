package com.example.insertcoin.ui;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.insertcoin.R;
import com.example.insertcoin.core.GestorBD;
import com.example.insertcoin.core.VariablesGlobales;
import com.example.insertcoin.core.juegos;

import java.util.ArrayList;

public class Juegos_SEARCH extends AppCompatActivity {

    ArrayList<juegos> datos = new ArrayList<>();
    GestorBD bd;
    SQLiteDatabase baseDatos;

    TextView edTitulo = null;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juegos_search);

        Button btBuscarTitulo = findViewById(R.id.btBuscarTitulo);

        bd = VariablesGlobales.getBaseDatos();



        baseDatos = bd.getWritableDatabase();

        edTitulo = findViewById(R.id.edTitulo);

        btBuscarTitulo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                retorno();
            }
        });
    }

    private void retorno(){

        String titulo = edTitulo.getText().toString();

        VariablesGlobales.setTituloActual(titulo);

        //Toast.makeText(getApplicationContext(),VariablesGlobales.getMensajeBD(), Toast.LENGTH_LONG).show();
        Intent i = new Intent(getApplicationContext(), Juegos_SHOWALL.class);
        startActivity(i);
    }
}
