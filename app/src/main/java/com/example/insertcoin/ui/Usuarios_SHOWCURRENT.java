package com.example.insertcoin.ui;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.insertcoin.R;
import com.example.insertcoin.core.GestorBD;
import com.example.insertcoin.core.VariablesGlobales;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

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
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        switch (id){

            case R.id.logout:
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

        bd = VariablesGlobales.getBaseDatos();
        String usuario = VariablesGlobales.getUsuario();

        baseDatos = bd.getWritableDatabase();
        user = bd.usuariosShowcurrent(baseDatos,VariablesGlobales.getUsuario());



        String login = user.getString(user.getColumnIndex("login"));
        String email = user.getString(user.getColumnIndex("email"));
        String nombre = user.getString(user.getColumnIndex("nombre"));
        String apellidos = user.getString(user.getColumnIndex("apellidos"));
        String password = user.getString(user.getColumnIndex("password"));

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

        FloatingActionButton btn_editUsuario = findViewById(R.id.btn_editUsuario);

        btn_editUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), Usuarios_EDIT.class);
                startActivity(i);
            }
        });

    }
}
