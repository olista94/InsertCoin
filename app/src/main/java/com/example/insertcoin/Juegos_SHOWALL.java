package com.example.insertcoin;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;

import java.util.ArrayList;

public class Juegos_SHOWALL extends AppCompatActivity {

    ArrayList<juegos> datos = new ArrayList<>();
    GestorBD bd;
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
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        switch (id){
            case: R.id.verPerfil;"hacer codigo";
            return true;
            case: R.id.logout; //poner login a " " "hacer codigo";
            return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juegos_showall );

        // Falta algo
        ListView lista = (ListView) findViewById(R.id.lista);
        bd = new GestorBD(getApplicationContext(),"",null,1);
        SQLiteDatabase baseDatos = bd.getWritableDatabase();
        datos = bd.obtenerDatosPelis(baseDatos);
        ArrayList<juegosConFotos> datosConFoto = new ArrayList<>();
        for(juegos e : datos) {
            String ruta = "@drawable/"+e.getPortada();
            System.out.println(e.getPortada());

            int imageResource = getResources().getIdentifier(ruta, null, getPackageName());
            Drawable imagen = ContextCompat.getDrawable(getApplicationContext(), imageResource);
            juegosConFotos f = new juegosConFotos(e.getId(), e.getTitulo(), imagen, e.getDescripcion(), e.getGenero(), e.getPlataforma(), e.getNacionalidad(), e.getCompañia(), e.getAño(), e.getEdadRecomendada(), e.getEnlace());
            datosConFoto.add(f);
        }

        //ADAPTER
        Adaptador adaptador = new Adaptador(this, datosConFoto);
        lista.setAdapter(adaptador);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                juegos datoSeleccionado = (juegos) datos.get(position);
                int posicion = position;

                Intent intent = new Intent(getApplicationContext(), Juegos_SHOWCURRENT.class);
                // intent.putExtra("elemento", datoSeleccionado);//Debe ser serializable datoSeleccionado
               /* intent.putExtra("nombre", selectedProduct.getName());
                intent.putExtra("contenido", selectedProduct.getContent());
                intent.putExtra("extra1", selectedProduct.getExtra());*/
                startActivity(intent);

        /*String nombre = intent.getStringExtra("nombre");
        String contenido = intent.getStringExtra("contenido");
        String extra = intent.getStringExtra("extra1");*/

            }

        });
    }

}