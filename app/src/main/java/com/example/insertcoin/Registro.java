package com.example.insertcoin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Registro extends AppCompatActivity {

    EditText input_login = null;
    EditText input_password = null;
    EditText input_nombre = null;
    EditText input_apellidos = null;
    EditText input_email = null;

    GestorBD bd;
    SQLiteDatabase baseDatos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        Button btn_login = findViewById(R.id.btn_login);
        bd = VariablesGlobales.getBaseDatos();
        System.out.println(bd.getDatabaseName() + "````````````````````````````````````````````````````````````````````");
        baseDatos = bd.getWritableDatabase();


        input_login = findViewById(R.id.input_login);
        input_password = findViewById(R.id.input_password);
        input_nombre = findViewById(R.id.input_nombre);
        input_apellidos = findViewById(R.id.input_apellidos);
        input_email = findViewById(R.id.input_email);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validarRegistro();
            }
        });

    }

    private void validarRegistro(){

        String login = input_login.getText().toString();
        String password = input_password.getText().toString();
        String nombre = input_nombre.getText().toString();
        String apellidos = input_apellidos.getText().toString();
        String email = input_email.getText().toString();

        String mensaje = "";
        boolean toret = true;

        if (login.isEmpty() || login.equals(null)) {
            toret = false;
            mensaje = "Error: el login no puede ser vacio";
        }

        if (password.isEmpty() || password.equals(null) ){
            toret = false;
            mensaje = "Error: la contraseña no puede ser vacia";
        }

        if (nombre.isEmpty()  || nombre.equals(null) ) {
            toret = false;
            mensaje = "Error: El nombre no puede ser vacio";
        }

        if (apellidos.isEmpty()  || apellidos.equals(null) ) {
            toret = false;
            mensaje = "Error: los apellidos no pueden ser vacios";
        }

        if (email.isEmpty()  || email.equals(null) ) {
            toret = false;
            mensaje = "Error: el email no puede ser vacio";
        }

        if (!toret) {
            Toast.makeText(getApplicationContext(), mensaje, Toast.LENGTH_SHORT).show();
        } else {
            bd.addUsuario(login, password, nombre, apellidos, email);
            retorno();
        }

    } // Fin validarComprobaciones

    private void retorno() {
        Toast.makeText(getApplicationContext(),VariablesGlobales.getMensajeBD(), Toast.LENGTH_LONG).show();
        Intent i = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(i);
    } // Fin retorno
}