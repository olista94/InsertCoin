package com.example.insertcoin;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

public class Juegos_SHOWCURRENT extends AppCompatActivity {

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_principal, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        switch (id){
            case: R.id.verPerfil:"hacer codiog";
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
        setContentView(R.layout.activity_juegos_showall);

        final TextView id = (TextView)findViewById(R.id.txtId);
        final ImageView foto = (ImageView)findViewById(R.id.imgFoto);
        EditText texto = (EditText) findViewById(R.id.txtTexto);
        RatingBar valoracion = (RatingBar)findViewById(R.id.barraValoracion);
        TextView lblcomentarios = (TextView)findViewById(R.id.lblComentarios); //SOBRE ESTE LABEL LANZAMOS MENÚ CONTEXTUAL***************
        final Button btndialogo = (Button)findViewById(R.id.btnCuadroDialogo);//PARA LANZAR CUADRO DE DIÁLOGO*******************
        Button volver = (Button)findViewById(R.id.btnVolver);

        Intent intent = getIntent();
        /*String nombre = intent.getStringExtra("nombre");
        String contenido = intent.getStringExtra("contenido");
        String extra = intent.getStringExtra("extra1");*/
        final juegos elemento = (juegos) intent.getSerializableExtra("elemento");

        id.setText(String.valueOf(elemento.getId()));
        String ruta = "@drawable/"+elemento.getPortada();
        int imageResource = getResources().getIdentifier(ruta, null, getPackageName());
        Drawable imagen = ContextCompat.getDrawable(getApplicationContext(), imageResource);
        foto.setImageDrawable(imagen);
        texto.setText(elemento.getTitulo());

        volver.setOnClickListener(new View.OnClickListener() {
            @Override
            // Vuelve de la peli a la vista
            public void onClick(View v) {
                Intent intent2 = new Intent(getApplicationContext(), Juegos_SHOWALL.class);
                setResult(RESULT_OK, intent2);
                finish();
            }
        });

        //MENÚ CONTEXTUAL AL PULSAR EN EL LABEL COMENTARIOS**************************
        registerForContextMenu(lblcomentarios);

        //LANZAMOS CUADRO DE DIÁLOGO AL PULSAR UN BOTÓN*******************
        btndialogo.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                AlertDialog.Builder dialogo = new AlertDialog.Builder(btndialogo.getContext());
                dialogo.setMessage("¿Seguro que desea publicar el comentario?")
                        .setTitle("Confirmar publicación de comentario")
                        .setPositiveButton("Publicar", new DialogInterface.OnClickListener()  {
                            public void onClick(DialogInterface dialog, int id) {
                                Log.i("Dialogos", "Publicación Aceptada.");
                                aceptar();
                            }
                        });
                dialogo.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Log.i("Dialogos", "Publicación Cancelada.");
                        cancelar();
                    }
                });
                dialogo.show();
            }

            public void aceptar() {
                ((TextView)findViewById(R.id.editText4)).setText("Publicar comentario");
                //dialogo.finish();
            }

            public void cancelar() {
                ((TextView)findViewById(R.id.editText4)).setText("Cancelar publicación");
                //finish();
            }




                /*VariablesGlobales globales = new VariablesGlobales();//EJEMPLO DE USO DE VARIABLES GLOBALES
                FragmentManager fragmentManager = getSupportFragmentManager();
                cuadroDialogoConfirmarAddComentario dialogo = new cuadroDialogoConfirmarAddComentario();
                dialogo.show(fragmentManager, "tagAlerta");

                if(globales.getRespuestaDialogo().equals("publicar")){
                    ((TextView)findViewById(R.id.editText4)).setText("Publicar comentario");
                }
                if(globales.getRespuestaDialogo().equals("cancelar")){
                    ((TextView)findViewById(R.id.editText4)).setText("Cancelar publicación");
                }
            }*/
    });
}

    //PARA MENÚ CONTEXTUAL*******************************
    //Asocia el menú contextual al xml del menú
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_comentarios, menu);
    }

    //PARA MENÚ CONTEXTUAL*******************
    //Detecta en qué opción se pulsa. Hay que lanzar las activities que queramos en cada opción
    @Override
    public boolean onContextItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.verComentarios:
                ((TextView)findViewById(R.id.editText3)).setText("Ver comentarios pulsada!");
                return true;
            case R.id.addComentario:
                ((TextView)findViewById(R.id.editText3)).setText("Añadir comentarios pulsada!");
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }
}