package com.example.insertcoin;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class Juegos_ADD extends AppCompatActivity {

    EditText ja_titulo = null;
    EditText ja_descripcion = null;
    EditText ja_genero = null;
    EditText ja_plataforma = null;
    EditText ja_nacionalidad = null;
    EditText ja_compania = null;
    EditText ja_anho = null;
    EditText ja_edadRecomendada = null;
    EditText ja_enlace = null;
    ImageView ja_portada = null;

    GestorBD bd;
    SQLiteDatabase baseDatos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juegos_add);

        Button btn_addJuego = findViewById(R.id.btn_addJuego);
        Button btn_addPortada = findViewById(R.id.btn_addPortada);

        bd = VariablesGlobales.getBaseDatos();

        // bd = new GestorBD(getApplicationContext(),"",null,1);
        baseDatos = bd.getWritableDatabase();

        ja_titulo = findViewById(R.id.ja_titulo);
        ja_descripcion = findViewById(R.id.ja_descripcion);
        ja_genero = findViewById(R.id.ja_genero);
        ja_plataforma = findViewById(R.id.ja_plataforma);
        ja_nacionalidad = findViewById(R.id.ja_nacionalidad);
        ja_compania = findViewById(R.id.ja_compania);
        ja_anho = findViewById(R.id.ja_anho);
        ja_edadRecomendada = findViewById(R.id.ja_edadRecomendada);
        ja_enlace = findViewById(R.id.ja_enlace);
        ja_portada = findViewById(R.id.imgPortada);

        btn_addPortada.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cargarImagen();
            }
        });

        btn_addJuego.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String titulo = ja_titulo.getText().toString();
                String portada = ja_portada.getTransitionName();
                String descripcion = ja_descripcion.getText().toString();
                String genero = ja_genero.getText().toString();
                String plataforma = ja_plataforma.getText().toString();
                String nacionalidad = ja_nacionalidad.getText().toString();
                String compania = ja_compania.getText().toString();
                int anho = (Integer.parseInt(ja_anho.getText().toString()));
                String edadRecomendada = ja_edadRecomendada.getText().toString();
                String enlace = ja_enlace.getText().toString();

                bd.addJuego(titulo,portada,descripcion,genero,plataforma,nacionalidad,compania,anho,edadRecomendada,enlace);
                System.out.println("portada*****************************************************************"+portada);

                Toast.makeText(getApplicationContext(),"Insertado correcto",Toast.LENGTH_SHORT).show();
                Intent i = new Intent(getApplicationContext(),Juegos_SHOWALL.class);
                startActivity(i);
            }
        });

    }

    private void cargarImagen() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/");
        startActivityForResult(intent.createChooser(intent,"Selecciona la portada"),10);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK){
            Uri path = data.getData();
            ja_portada.setImageURI(path);
        }
    }
}
