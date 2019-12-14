package com.example.insertcoin.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.insertcoin.R;
import com.example.insertcoin.core.GestorBD;
import com.example.insertcoin.core.VariablesGlobales;

public class Usuarios_EDIT extends AppCompatActivity {

    TextView ue_login = null;
    EditText ue_password = null;
    EditText ue_nombre = null;
    EditText ue_apellidos = null;
    EditText ue_email = null;


    Cursor usuario;
    GestorBD bd;
    SQLiteDatabase baseDatos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuarios_edit);

        Button btn_ConfirmarEdit = findViewById(R.id.btn_ConfirmarEdit);
        bd = VariablesGlobales.getBaseDatos();

        baseDatos = bd.getWritableDatabase();
        usuario = bd.usuariosEdit(baseDatos,VariablesGlobales.getUsuario());

        String login = usuario.getString(usuario.getColumnIndex("login"));
        String password = usuario.getString(usuario.getColumnIndex("password"));
        String nombre = usuario.getString(usuario.getColumnIndex("nombre"));
        String apellidos = usuario.getString(usuario.getColumnIndex("apellidos"));
        String email = usuario.getString(usuario.getColumnIndex("email"));

        ue_login = findViewById(R.id.ue_login);
        ue_password = findViewById(R.id.ue_password);
        ue_nombre = findViewById(R.id.ue_nombre);
        ue_apellidos = findViewById(R.id.ue_apellidos);
        ue_email = findViewById(R.id.ue_email);

        ue_login.setText(login);
        ue_password.setText(password);
        ue_nombre.setText(nombre);
        ue_apellidos.setText(apellidos);
        ue_email.setText(email);

        btn_ConfirmarEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validarJuego();
            }
        });

    }

    private void validarJuego(){

        String login = ue_login.getText().toString();
        String password = ue_password.getText().toString();
        String nombre = ue_nombre.getText().toString();
        String apellidos = ue_apellidos.getText().toString();
        String email = ue_email.getText().toString();

        String mensaje = "";
        boolean toret = true;

        if( login.isEmpty() || login.equals(null) ){
            toret = false;
            mensaje = "Error: el login no puede ser vacio";
        }

        if( password.isEmpty() || password.equals(null) ){
            toret = false;
            mensaje = "Error: la password no puede ser vacia";
        }

        if( nombre.isEmpty() || nombre.equals(null) ){
            toret = false;
            mensaje = "Error: el nombre no puede ser vacio";
        }

        if( apellidos.isEmpty() || apellidos.equals(null) ){
            toret = false;
            mensaje = "Error: los apellidos no pueden ser vacios";
        }

        if( email.isEmpty() || email.equals(null) ){
            toret = false;
            mensaje = "Error: el email no puede ser vacio";
        }

        if(!toret){
            Toast.makeText(getApplicationContext(), mensaje, Toast.LENGTH_SHORT).show();
        }
        else{

            bd.updateUsuario(login,password,nombre,apellidos,email);
            retorno();
        }

    } // Fin validarComprobaciones

    private void retorno(){
        VariablesGlobales.setTituloActual("");
        Toast.makeText(getApplicationContext(),VariablesGlobales.getMensajeBD(), Toast.LENGTH_LONG).show();
        Intent i = new Intent(getApplicationContext(),Juegos_SHOWALL.class);
        startActivity(i);
    }
}
