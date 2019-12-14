package com.example.insertcoin;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class activity_add_comentario extends AppCompatActivity {

    EditText edComentario = null;
    GestorBD bd;
    SQLiteDatabase baseDatos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_comentario);

        final Button btAñadirComentario = findViewById(R.id.btAñadirComentario);
        bd = VariablesGlobales.getBaseDatos();


        //bd = new GestorBD(getApplicationContext(),"",null,1);
        baseDatos = bd.getWritableDatabase();

        edComentario = findViewById(R.id.edComentario);


        btAñadirComentario.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                final String comentario = edComentario.getText().toString();
                final String titulo = VariablesGlobales.getTituloActual();
                final String login = VariablesGlobales.getUsuario();

                validarComentario();
                /*AlertDialog.Builder dialogo = new AlertDialog.Builder(btAñadirComentario.getContext());

                dialogo.setMessage("¿Seguro que deseas publicar el comentario?")
                        .setTitle("Confirmar comentario")
                        .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                bd.addComentario(login,titulo,comentario);
                                volver();
                            }
                        });
                dialogo.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                        volver();
                    }
                });
                dialogo.show();*/

            }




    public void validarComentario(){

        final String comentario = edComentario.getText().toString();
        final String titulo = VariablesGlobales.getTituloActual();
        final String login = VariablesGlobales.getUsuario();

        String mensaje = "";
        boolean toret = true;

        if (comentario.isEmpty() || comentario.equals(null)) {
            toret = false;
            mensaje = "Error: el comentario no puede ser vacio";
        }

        if (!toret) {
            Toast.makeText(getApplicationContext(), mensaje, Toast.LENGTH_SHORT).show();
        } else {
            final Button btAñadirComentario = findViewById(R.id.btAñadirComentario);
            AlertDialog.Builder dialogo = new AlertDialog.Builder(btAñadirComentario.getContext());

            dialogo.setMessage("¿Seguro que deseas publicar el comentario?")
                    .setTitle("Confirmar comentario")
                    .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            bd.addComentario(login,titulo,comentario);
                            volver();
                        }
                    });
            dialogo.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                    volver();
                }
            });
            dialogo.show();
        }

    } // Fin validarComprobaciones

    public void retorno() {
        Toast.makeText(getApplicationContext(), "Insertado correcto", Toast.LENGTH_SHORT).show();
        Intent i = new Intent(getApplicationContext(), Juegos_SHOWALL.class);
        startActivity(i);
    }

            public void volver(){
                VariablesGlobales.setTituloActual("");
                Intent i = new Intent(getApplicationContext(),Juegos_SHOWALL.class);
                startActivity(i);
            }
        });
    }


}
