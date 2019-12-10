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
        System.out.println(bd.getDatabaseName()+"````````````````````````````````````````````````````````````````````");
       // bd = new GestorBD(getApplicationContext(),"",null,1);
        baseDatos = bd.getWritableDatabase();

        input_login = findViewById(R.id.input_login);
        input_password = findViewById(R.id.input_password);
        input_nombre = findViewById(R.id.input_nombre);
        input_apellidos = findViewById(R.id.input_apellidos);
        input_email = findViewById(R.id.input_email);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String login = input_login.getText().toString();
                String password = input_password.getText().toString();
                String nombre = input_nombre.getText().toString();
                String apellidos = input_apellidos.getText().toString();
                String email = input_email.getText().toString();

                bd.addUsuario(login,password,nombre,apellidos,email);
                System.out.println("Login*****************************************************************"+login);
                System.out.println("password*****************************************************************"+password);
                System.out.println("nombre*****************************************************************"+nombre);
                System.out.println("apellidos*****************************************************************"+apellidos);
                System.out.println("email*****************************************************************"+email);
                Toast.makeText(getApplicationContext(),"Insertado correcto",Toast.LENGTH_SHORT).show();
                Intent i = new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(i);
            }
        });
    }
}
