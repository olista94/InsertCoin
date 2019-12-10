package com.example.insertcoin;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;

public class Usuarios_SHOWCURRENT extends AppCompatActivity {

    // ArrayList <usuarios> datos = new ArrayList<>();


    GestorBD bd;
    SQLiteDatabase baseDatos;
    Cursor user;

    TextView sc_login = null;
    TextView sc_email = null;
    TextView sc_nombre = null;
    TextView sc_apellidos = null;
    TextView sc_contraseña = null;


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        switch (id){

            case R.id.logout: //poner login a " " "hacer codigo";
                this.finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuarios_showcurrent );

        //String pos = this.getIntent().getExtras().getString("login");

        // Falta algo
       // ListView lista = (ListView) findViewById(R.id.lista);
        //Intent intent = getIntent();

        bd = VariablesGlobales.getBaseDatos();
        String usuario = VariablesGlobales.getUsuario();
        System.out.println("el usuarios es "+usuario+"*******************************************");
        //bd = new GestorBD(getApplicationContext(),"",null,1);
        baseDatos = bd.getWritableDatabase();
        user = bd.usuariosShowcurrent(baseDatos,VariablesGlobales.getUsuario());


        /*ArrayList<usuarios> usuariosArrayList = new ArrayList<>();
        for(usuarios e : datos) {

            usuarios u = new usuarios(e.getLogin(), e.getEmail(), e.getNombre(), e.getApellidos(),e.getPassword());
            usuariosArrayList.add(u);
        }*/

        String login = user.getString(user.getColumnIndex("login"));
        String email = user.getString(user.getColumnIndex("email"));
        String nombre = user.getString(user.getColumnIndex("nombre"));
        String apellidos = user.getString(user.getColumnIndex("apellidos"));
        String password = user.getString(user.getColumnIndex("password"));

        System.out.println("El login es: "+login+"*********************************************");
        System.out.println("El email es: "+email+"*********************************************");
        System.out.println("El nombre es: "+nombre+"*********************************************");
        System.out.println("El apellidos es: "+apellidos+"*********************************************");
        System.out.println("El password es: "+password+"*********************************************");

        sc_login = findViewById(R.id.sc_login);
        sc_email = findViewById(R.id.sc_email);
        sc_nombre = findViewById(R.id.sc_nombre);
        sc_apellidos = findViewById(R.id.sc_apellidos);
        sc_contraseña = findViewById(R.id.sc_contraseña);

        sc_login.setText(login);
        sc_email.setText(email);
        sc_nombre.setText(nombre);
        sc_apellidos.setText(apellidos);
        sc_contraseña.setText(password);

       /* //ADAPTER
        Adaptador adaptador = new Adaptador(this, usuariosArrayList);
        lista.setAdapter(adaptador);*/

        /*lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                usuarios usuariosArrayList = (usuarios) datos.get(position);
                int posicion = position;

                Intent intent = new Intent(getApplicationContext(), Juegos_SHOWCURRENT.class);
                 /*intent.putExtra("elemento", datoSeleccionado);//Debe ser serializable datoSeleccionado
                intent.putExtra("nombre", selectedProduct.getName());
                intent.putExtra("contenido", selectedProduct.getContent());
                intent.putExtra("extra1", selectedProduct.getExtra());*/
        //startActivity(intent);

        /*String nombre = intent.getStringExtra("nombre");
        String contenido = intent.getStringExtra("contenido");
        String extra = intent.getStringExtra("extra1");

            }

        });*/
    }
}
