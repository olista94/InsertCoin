package com.example.insertcoin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class Comentarios_SHOWALL extends AppCompatActivity {

    ArrayList<comenta> datos = new ArrayList<>();
    GestorBD bd;
    SQLiteDatabase baseDatos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comentarios_showall);

        ListView listaComentarios = (ListView) findViewById(R.id.listaComentarios);

        Intent intent = getIntent();

        bd = VariablesGlobales.getBaseDatos();
        //String usuario = VariablesGlobales.getUsuario();
        String titulo = VariablesGlobales.getTituloActual();
        //bd = new GestorBD(getApplicationContext(),"",null,1);
        baseDatos = bd.getWritableDatabase();
        datos = bd.obtenerComentarios(baseDatos,titulo);
        ArrayList<comenta> comentarios = new ArrayList<>();
        for(comenta e : datos) {
            //juegosConFotos f = new juegosConFotos(e.getId(), e.getTitulo(), e.getDescripcion(), e.getGenero(), e.getPlataforma(), e.getNacionalidad(), e.getCompañia(), e.getAño(), e.getEdadRecomendada(), e.getEnlace());
            comenta c = new comenta(e.getId(), e.getLogin(), e.getTitulo(), e.getComentario());
            comentarios.add(c);
        }

        //ADAPTER
            AdaptadorComentarios adaptador = new AdaptadorComentarios(this, comentarios);
            listaComentarios.setAdapter(adaptador);
        System.out.println("--------------------------------------------------"+adaptador.getCount());




    }
}
