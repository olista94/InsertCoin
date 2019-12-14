package com.example.insertcoin;

import android.content.ClipData;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.sql.SQLOutput;
import java.util.ArrayList;

public class Juegos_SHOWALL extends AppCompatActivity {

    //Button logout = (Button)findViewById(R.id.logout);


    ArrayList<juegos> datos = new ArrayList<>();
    GestorBD bd;
    SQLiteDatabase baseDatos;
    ListView lista;

    @Override
    protected void onPause() {
        super.onPause();
        if(bd != null){
            bd.close();
        }
    }

    protected void onResume() {
        super.onResume();
        super.onPause();
        if (bd == null) {
            bd = new GestorBD(getApplicationContext(), "", null, 1);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_principal, menu);
        return true;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo){
        super.onCreateContextMenu( menu, v, menuInfo );

        if ( v.getId() == R.id.lista ) {
            this.getMenuInflater().inflate( R.menu.menu_comentarios, menu );
        }

        return;
    }


    @Override
    public boolean onContextItemSelected(MenuItem item) {
        int id = item.getItemId();
        int pos;

        if(id == R.id.addComentario) {
            pos = ((AdapterView.AdapterContextMenuInfo) item.getMenuInfo()).position;
            VariablesGlobales.setTituloActual(datos.get(pos).getTitulo());
            Intent i = new Intent(this, activity_add_comentario.class);
            startActivity(i);
        }

        else if(id == R.id.verComentarios) {
            pos = ((AdapterView.AdapterContextMenuInfo) item.getMenuInfo()).position;
            VariablesGlobales.setTituloActual(datos.get(pos).getTitulo());
            Intent i = new Intent(this, Comentarios_SHOWALL.class);
            startActivity(i);
        }
        else if(id == R.id.editJuego) {
            pos = ((AdapterView.AdapterContextMenuInfo) item.getMenuInfo()).position;
            VariablesGlobales.setTituloActual(datos.get(pos).getTitulo());
            Intent i = new Intent(this, Juegos_EDIT.class);
            startActivity(i);
        }
        else{
            Intent i = new Intent(this, Comentarios_SHOWALL.class);
            startActivity(i);
        }
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
            this.finish();
            VariablesGlobales.setTituloActual("");
            Intent intent = new Intent(this,LoginActivity.class);
            startActivity(intent);
        }
        else if(id == R.id.buscarJuego){
            Intent intent = new Intent(this, ActivityBuscarJuego.class);
            startActivity(intent);
        }
        else {
            Intent i = new Intent(this, Comentarios_SHOWALL.class);
            startActivity(i);
        }
        return true;

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        bd = VariablesGlobales.getBaseDatos();
        String usuario = VariablesGlobales.getUsuario();
        //bd = new GestorBD(getApplicationContext(),"",null,1);
        baseDatos = bd.getWritableDatabase();
        datos = bd.buscarJuegos(baseDatos,VariablesGlobales.getTituloActual());
        ArrayList<juegosConFotos> datosConFoto = new ArrayList<>();
        loadDatosConFoto(datosConFoto);

        //ADAPTER
        Adaptador adaptador = new Adaptador(this, datosConFoto);

        lista.setAdapter(adaptador);
        adaptador.notifyDataSetChanged();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juegos_showall );

         lista = (ListView) findViewById(R.id.lista);
        this.registerForContextMenu(lista);
        Intent intent = getIntent();

        bd = VariablesGlobales.getBaseDatos();
        String usuario = VariablesGlobales.getUsuario();
        //bd = new GestorBD(getApplicationContext(),"",null,1);
        baseDatos = bd.getWritableDatabase();

        datos = bd.buscarJuegos(baseDatos,VariablesGlobales.getTituloActual());
        ArrayList<juegosConFotos> datosConFoto = new ArrayList<>();
        loadDatosConFoto(datosConFoto);

        //ADAPTER
        Adaptador adaptador = new Adaptador(this, datosConFoto);
        lista.setAdapter(adaptador);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                juegos datoSeleccionado = (juegos) datos.get(position);
                int posicion = position;

                VariablesGlobales.setTituloActual(datos.get(posicion).getTitulo());

                Intent intent = new Intent(getApplicationContext(), Juegos_SHOWCURRENT.class);
                 /*intent.putExtra("elemento", datoSeleccionado);//Debe ser serializable datoSeleccionado
                intent.putExtra("nombre", selectedProduct.getName());
                intent.putExtra("contenido", selectedProduct.getContent());
                intent.putExtra("extra1", selectedProduct.getExtra());*/
                startActivity(intent);



        /*String nombre = intent.getStringExtra("nombre");
        String contenido = intent.getStringExtra("contenido");
        String extra = intent.getStringExtra("extra1");*/

            }

        });

        FloatingActionButton btn_addJuego = findViewById(R.id.btn_addJuego);

        btn_addJuego.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),Juegos_ADD.class);
                startActivityForResult(i,1);
            }
        });
    }

    private void loadDatosConFoto(ArrayList<juegosConFotos> datosConFoto) {
        String ruta;
        for(juegos e : datos) {
            Drawable imagen = null;
            if(e.getPortada().contains("/")){
                ruta = e.getPortada();

                imagen = Drawable.createFromPath(ruta);
            }
            else{
                ruta = "@drawable/"+e.getPortada();

                int imageResource = getResources().getIdentifier(ruta, null, getPackageName());

                imagen = ContextCompat.getDrawable(getApplicationContext(), imageResource);

            }
            //System.out.println("rutinhassssssssssssssssssssssssssssssssssssssss"+ruta);

            //juegosConFotos f = new juegosConFotos(e.getId(), e.getTitulo(), e.getDescripcion(), e.getGenero(), e.getPlataforma(), e.getNacionalidad(), e.getCompañia(), e.getAño(), e.getEdadRecomendada());
            juegosConFotos f = new juegosConFotos(e.getId(), e.getTitulo(), imagen, e.getDescripcion(), e.getGenero(), e.getPlataforma(), e.getNacionalidad(), e.getCompañia(), e.getAño(), e.getEdadRecomendada());
            datosConFoto.add(f);
        }
    }

}