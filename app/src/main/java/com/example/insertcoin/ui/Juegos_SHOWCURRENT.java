package com.example.insertcoin.ui;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.insertcoin.R;
import com.example.insertcoin.core.GestorBD;
import com.example.insertcoin.core.VariablesGlobales;

public class Juegos_SHOWCURRENT extends AppCompatActivity {
    ImageView imgPortada = null;
    TextView txtTitulo = null;
    TextView txtDescipcion = null;
    TextView txtGenero = null;
    TextView txtPlataformas = null;
    TextView txtNacionalidad = null;
    TextView txtCompañia = null;
    TextView txtAnho = null;
    TextView txtEdadRecomendada = null;
    float puntuacion = 0;

    RatingBar barraValoracion = null;
    Cursor datos;
    Cursor puntos;
    GestorBD bd;
    SQLiteDatabase baseDatos;



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_principal, menu);
        return true;
    }



    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if(id == R.id.verPerfil) {
            Intent i = new Intent(this, Usuarios_SHOWCURRENT.class);
            startActivity(i);
        }
        else if(id == R.id.logout) {
            finish();
            VariablesGlobales.setTituloActual("");
            Intent intent = new Intent(this,LoginActivity.class);
            startActivity(intent);
        }
        else if(id == R.id.buscarJuego){
            Intent intent = new Intent(this, Juegos_SEARCH.class);
            startActivity(intent);
        }
        else {
            Intent i = new Intent(this, Comentarios_SHOWALL.class);
            startActivity(i);
        }
        return true;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juegos_showcurrent);

        Intent intent = getIntent();

        bd = VariablesGlobales.getBaseDatos();
        String juegoActual = VariablesGlobales.getTituloActual();
        baseDatos = bd.getWritableDatabase();
        datos = bd.juegosShowcurrent(baseDatos,juegoActual);
        puntos = bd.obtenerPuntuacionJuego(baseDatos,juegoActual,VariablesGlobales.getUsuario());

        String titulo = datos.getString(datos.getColumnIndex("titulo"));
        String portada = datos.getString(datos.getColumnIndex("portada"));
        String descripcion = datos.getString(datos.getColumnIndex("descripcion"));
        String genero = datos.getString(datos.getColumnIndex("genero"));
        String plataforma = datos.getString(datos.getColumnIndex("plataforma"));
        String nacionalidad = datos.getString(datos.getColumnIndex("nacionalidad"));
        String compania = datos.getString(datos.getColumnIndex("compania"));
        String anho = datos.getString(datos.getColumnIndex("anho"));
        String edad_recomendada = datos.getString(datos.getColumnIndex("edad_recomendada"));

        if(!puntos.moveToFirst()){
            puntuacion = 0;
        }
        else {
            puntuacion = puntos.getFloat(puntos.getColumnIndex("puntuacion"));
        }

        txtTitulo =  findViewById(R.id.txtTitulo);
        imgPortada = findViewById(R.id.imgPortada);
        txtDescipcion = findViewById(R.id.txtDescipcion);
        txtGenero = findViewById(R.id.txtGenero);
        txtPlataformas = findViewById(R.id.txtPlataformas);
        txtNacionalidad = findViewById(R.id.txtNacionalidad);
        txtCompañia = findViewById(R.id.txtCompañia);
        txtAnho = findViewById(R.id.txtAnho);
        txtEdadRecomendada = findViewById(R.id.txtEdadRecomendada);
        txtNacionalidad = findViewById(R.id.txtNacionalidad);

        barraValoracion = findViewById(R.id.barraValoracion);


        txtTitulo.setText(titulo);
        txtDescipcion.setText(descripcion);
        txtGenero.setText(genero);
        txtPlataformas.setText(plataforma);
        txtNacionalidad.setText(nacionalidad);
        txtCompañia.setText(compania);
        txtAnho.setText(anho);
        txtEdadRecomendada.setText(edad_recomendada);
        txtNacionalidad.setText(nacionalidad);

        barraValoracion.setRating(puntuacion);

        Drawable imagen = null;
        if(portada.contains("/storage")){
            portada = portada;
            imagen = Drawable.createFromPath(portada);
            imgPortada.setImageDrawable(imagen);
        }
        else{
            portada = "@drawable/"+portada;
            int imageResource = getResources().getIdentifier(portada, null, getPackageName());
            imagen = ContextCompat.getDrawable(getApplicationContext(), imageResource);
            imgPortada.setImageResource(imageResource);
        }

        Button btn_valoracion = findViewById(R.id.btn_valoracion);

        btn_valoracion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                puntuacion = barraValoracion.getRating();
                bd.addPuntuacion(VariablesGlobales.getUsuario(),VariablesGlobales.getTituloActual(),puntuacion);
                VariablesGlobales.setTituloActual("");
                Toast.makeText(getApplicationContext(),VariablesGlobales.getMensajeBD(), Toast.LENGTH_LONG).show();
                Intent i = new Intent(getApplicationContext(), Juegos_SHOWALL.class);
                startActivity(i);
            }
        });

    }

    //PARA MENÚ CONTEXTUAL*******************************
    //Asocia el menú contextual al xml del menú
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_comentarios, menu);
    }

    //PARA MENÚ CONTEXTUAL*******************
    //Detecta en qué opción se pulsa. Hay que lanzar las activities que queramos en cada opción
    @Override
    public boolean onContextItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.verComentarios:
                ((TextView)findViewById(R.id.txtPlataformas)).setText("Ver comentarios pulsada!");
                return true;
            case R.id.addComentario:
                ((TextView)findViewById(R.id.txtPlataformas)).setText("Añadir comentarios pulsada!");
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }
}