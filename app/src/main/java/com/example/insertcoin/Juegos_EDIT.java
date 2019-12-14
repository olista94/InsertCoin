package com.example.insertcoin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class Juegos_EDIT extends AppCompatActivity {

    TextView je_titulo = null;
    EditText je_descripcion = null;
    EditText je_genero = null;
    EditText je_plataforma = null;
    EditText je_nacionalidad = null;
    EditText je_compania = null;
    EditText je_anho = null;
    EditText je_edadRecomendada = null;


    Cursor juego;
    GestorBD bd;
    SQLiteDatabase baseDatos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juegos_edit);

        Button btn_editJuego = findViewById(R.id.btn_editJuego);
        bd = VariablesGlobales.getBaseDatos();
        String usuario = VariablesGlobales.getUsuario();

        baseDatos = bd.getWritableDatabase();
        juego = bd.juegosEdit(baseDatos,VariablesGlobales.getTituloActual());

        String titulo = juego.getString(juego.getColumnIndex("titulo"));
        String descripcion = juego.getString(juego.getColumnIndex("descripcion"));
        String genero = juego.getString(juego.getColumnIndex("genero"));
        String plataforma = juego.getString(juego.getColumnIndex("plataforma"));
        String nacionalidad = juego.getString(juego.getColumnIndex("nacionalidad"));
        String compania = juego.getString(juego.getColumnIndex("compania"));
        String anho = juego.getString(juego.getColumnIndex("anho"));
        String edadRecomendada = juego.getString(juego.getColumnIndex("edad_recomendada"));


        je_titulo = findViewById(R.id.je_titulo);
        je_descripcion = findViewById(R.id.je_descripcion);
        je_genero = findViewById(R.id.je_genero);
        je_plataforma = findViewById(R.id.je_plataforma);
        je_nacionalidad = findViewById(R.id.je_nacionalidad);
        je_compania = findViewById(R.id.je_compania);
        je_anho = findViewById(R.id.je_anho);
        je_edadRecomendada = findViewById(R.id.je_edadRecomendada);


        je_titulo.setText(titulo);
        je_descripcion.setText(descripcion);
        je_genero.setText(genero);
        je_plataforma.setText(plataforma);
        je_nacionalidad.setText(nacionalidad);
        je_compania.setText(compania);
        je_anho.setText(anho);
        je_edadRecomendada.setText(edadRecomendada);


        btn_editJuego.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validarJuego();
            }
        });




    }

    private void validarJuego(){

        String titulo = je_titulo.getText().toString();
        String descripcion = je_descripcion.getText().toString();
        String genero = je_genero.getText().toString();
        String plataforma = je_plataforma.getText().toString();
        String nacionalidad = je_nacionalidad.getText().toString();
        String compania = je_compania.getText().toString();
//        int anho = (Integer.parseInt(ja_anho.getText().toString()));
        String edadRecomendada = je_edadRecomendada.getText().toString();

        //System.out.println("anooooooooooooooooooooooooooooooooo"+anho);
        String mensaje = "";
        boolean toret = true;

        if( titulo.isEmpty() || titulo.equals(null) ){
            toret = false;
            mensaje = "Error: el titulo no puede ser vacio";
        }

        if( descripcion.isEmpty() || descripcion.equals(null) ){
            toret = false;
            mensaje = "Error: la descripción no puede ser vacia";
        }

        if( genero.isEmpty() || genero.equals(null) ){
            toret = false;
            mensaje = "Error: el genero no puede ser vacio";
        }

        if( plataforma.isEmpty() || plataforma.equals(null) ){
            toret = false;
            mensaje = "Error: el plataforma no puede ser vacio";
        }

        if( nacionalidad.isEmpty() || nacionalidad.equals(null) ){
            toret = false;
            mensaje = "Error: la nacionalidad no puede ser vacia";
        }

        if( compania.isEmpty() || compania.equals(null) ){
            toret = false;
            mensaje = "Error: la compañia no puede ser vacia";
        }

        if( je_anho.getText().toString().isEmpty()){
            toret = false;
            mensaje = "Error: el ano no puede ser vacio";
        }

        if( edadRecomendada.isEmpty() || edadRecomendada.equals(null) ){
            toret = false;
            mensaje = "Error: la edad recomendada no puede ser vacio";
        }



        /*if(portada.isEmpty() || portada.equals(null)){
            toret = false;
            mensaje = "Error: la portada no puede ser vacia";
        }*/
        if(!toret){
            Toast.makeText(getApplicationContext(), mensaje, Toast.LENGTH_SHORT).show();
        }
        else{
            int anho = (Integer.parseInt(je_anho.getText().toString()));

            bd.updateJuego(titulo,descripcion,genero,plataforma,nacionalidad,compania,anho,edadRecomendada);
            retorno();
        }

    } // Fin validarComprobaciones

    private void retorno(){
        Toast.makeText(getApplicationContext(),VariablesGlobales.getMensajeBD(), Toast.LENGTH_LONG).show();
        VariablesGlobales.setTituloActual("");
        Intent i = new Intent(getApplicationContext(),Juegos_SHOWALL.class);
        startActivity(i);
    } // Fin retorno
}

