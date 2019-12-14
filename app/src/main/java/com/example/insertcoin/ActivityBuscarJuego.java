package com.example.insertcoin;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ActivityBuscarJuego extends AppCompatActivity {

    ArrayList<juegos> datos = new ArrayList<>();
    GestorBD bd;
    SQLiteDatabase baseDatos;

    TextView edTitulo = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar_juego);

        Button btBuscarTitulo = findViewById(R.id.btBuscarTitulo);

        bd = VariablesGlobales.getBaseDatos();

        //String juegoActual = VariablesGlobales.getTituloActual();


        baseDatos = bd.getWritableDatabase();
        //datos = bd.buscarJuegos(baseDatos,juegoActual);

        edTitulo = findViewById(R.id.edTitulo);

        btBuscarTitulo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                retorno();
            }
        });
    }

    /*public void validarTitulo(){
        String titulo = edTitulo.getText().toString();

        String mensaje = "";
        boolean toret = true;

        if( titulo.isEmpty() || titulo.equals(null) ){
            toret = false;
            mensaje = "Error: el titulo no puede estar vacio";
        }

        if(!toret){
            Toast.makeText(getApplicationContext(), mensaje, Toast.LENGTH_SHORT).show();
        }
        else{
            //bd.buscarJuegos(baseDatos, titulo);
            retorno();
        }
    }*/

    private void retorno(){

        String titulo = edTitulo.getText().toString();

        VariablesGlobales.setTituloActual(titulo);

        //Toast.makeText(getApplicationContext(),VariablesGlobales.getMensajeBD(), Toast.LENGTH_LONG).show();
        Intent i = new Intent(getApplicationContext(), Juegos_SHOWALL.class);
        startActivity(i);
    }
}
