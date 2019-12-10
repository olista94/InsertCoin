package com.example.insertcoin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class activity_add_comentario extends AppCompatActivity {

    EditText edComentario = null;
    GestorBD bd;
    SQLiteDatabase baseDatos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_comentario);

        Button btAñadirComentario = findViewById(R.id.btAñadirComentario);
        bd = VariablesGlobales.getBaseDatos();


        //bd = new GestorBD(getApplicationContext(),"",null,1);
        baseDatos = bd.getWritableDatabase();

        edComentario = findViewById(R.id.edComentario);



        btAñadirComentario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String comentario = edComentario.getText().toString();
                String titulo = VariablesGlobales.getTituloActual();
                String login = VariablesGlobales.getUsuario();


                bd.addComentario(login,titulo,comentario);
                System.out.println("Comentario*****************************************************************"+comentario);
                System.out.println("Login*****************************************************************"+login);
                System.out.println("Titulo*****************************************************************"+titulo);

                Intent i = new Intent(getApplicationContext(),Juegos_SHOWALL.class);
                startActivity(i);
            }
        });
    }


}
