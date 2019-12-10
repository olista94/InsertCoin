package com.example.insertcoin;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class Juegos_SHOWCURRENT extends AppCompatActivity {
    ImageView imgPortada = null;
    TextView txtTitulo = null;
    TextView txtDescipcion = null;
    TextView txtGenero = null;
    TextView txtPlataformas = null;
    TextView txtNacionalidad = null;
    TextView txtCompañia = null;
    TextView txtAnho = null;
    TextView txtEdadRecomendada = null;
    TextView txtEnlace = null;

    RatingBar barraValoracion = null;
    //TextView lblComentarios = null;
    //Button btnVolver = null;
    //Button btnCuadroDialogo = null;
    Cursor datos;
    GestorBD bd;
    SQLiteDatabase baseDatos;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_principal, menu);
        return true;
    }



    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        switch (id){
            case R.id.verPerfil:
                Intent i = new Intent(this,Usuarios_SHOWCURRENT.class);
                startActivity(i);

            case R.id.logout: //poner login a " " "hacer codigo";
                this.finish();
                Intent intent = new Intent(this,LoginActivity.class);
                startActivity(intent);
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juegos_showcurrent);

        /*final TextView id = (TextView)findViewById(R.id.txtId);
        final ImageView foto = (ImageView)findViewById(R.id.imgFoto);
        EditText texto = (EditText) findViewById(R.id.txtTexto);
        RatingBar valoracion = (RatingBar)findViewById(R.id.barraValoracion);
        TextView lblcomentarios = (TextView)findViewById(R.id.lblComentarios); //SOBRE ESTE LABEL LANZAMOS MENÚ CONTEXTUAL***************
        final Button btndialogo = (Button)findViewById(R.id.btnCuadroDialogo);//PARA LANZAR CUADRO DE DIÁLOGO*******************
        Button volver = (Button) findViewById(R.id.btnVolver);*/

        Intent intent = getIntent();
        /*String nombre = intent.getStringExtra("nombre");
        String contenido = intent.getStringExtra("contenido");
        String extra = intent.getStringExtra("extra1");*/
        final juegos elemento = (juegos) intent.getSerializableExtra("elemento");

        bd = VariablesGlobales.getBaseDatos();
        //id.setText(String.valueOf(elemento.getId()));
        String juegoActual = VariablesGlobales.getTituloActual();
        baseDatos = bd.getWritableDatabase();
        datos = bd.juegosShowcurrent(baseDatos,juegoActual);

        String titulo = datos.getString(datos.getColumnIndex("titulo"));
        String portada = datos.getString(datos.getColumnIndex("portada"));
        String descripcion = datos.getString(datos.getColumnIndex("descripcion"));
        String genero = datos.getString(datos.getColumnIndex("genero"));
        String plataforma = datos.getString(datos.getColumnIndex("plataforma"));
        String nacionalidad = datos.getString(datos.getColumnIndex("nacionalidad"));
        String compania = datos.getString(datos.getColumnIndex("compania"));
        String anho = datos.getString(datos.getColumnIndex("anho"));
        String edad_recomendada = datos.getString(datos.getColumnIndex("edad_recomendada"));
        String enlace = datos.getString(datos.getColumnIndex("enlace"));

        System.out.println(portada+"*******************************************************");


        txtTitulo =  findViewById(R.id.txtTitulo);
        imgPortada = findViewById(R.id.imgPortada);
        txtDescipcion = findViewById(R.id.txtDescipcion);
        txtGenero = findViewById(R.id.txtGenero);
        txtPlataformas = findViewById(R.id.txtPlataformas);
        txtNacionalidad = findViewById(R.id.txtNacionalidad);
        txtCompañia = findViewById(R.id.txtCompañia);
        txtAnho = findViewById(R.id.txtAnho);
        txtEdadRecomendada = findViewById(R.id.txtEdadRecomendada);
        txtNacionalidad = findViewById(R.id.txtNacionalidad);
        txtEnlace = findViewById(R.id.txtEnlace);
        barraValoracion = findViewById(R.id.barraValoracion);


        txtTitulo.setText(titulo);
        txtDescipcion.setText(descripcion);
        txtGenero.setText(genero);
        txtPlataformas.setText(plataforma);
        txtNacionalidad.setText(nacionalidad);
        txtCompañia.setText(compania);
        txtAnho.setText(anho);
        txtEdadRecomendada.setText(edad_recomendada);
        txtNacionalidad.setText(nacionalidad);
        txtEnlace.setText(enlace);
        System.out.println("Titulo*****************************************************************"+juegoActual);
        String rutaFoto = "@drawable/"+portada;
        System.out.println("------------------------------------------------"+rutaFoto);
        int imageResource = getResources().getIdentifier(rutaFoto, null, getPackageName());
        //Drawable imagen = ContextCompat.getDrawable(getApplicationContext(), imageResource);

        imgPortada.setImageResource(imageResource);

        //Bitmap bitmap = BitmapFactory.decodeFile(rutaFoto);
        //imgFoto.setImageBitmap(bitmap);

       /* for(juegos e : datos) {
            String ruta = "@drawable/"+e.getPortada();
            System.out.println(ruta+"*********************************");
            int imageResource = getResources().getIdentifier(ruta, null, getPackageName());
            Drawable imagen = ContextCompat.getDrawable(getApplicationContext(), imageResource);
            //juegosConFotos f = new juegosConFotos(e.getId(), e.getTitulo(), e.getDescripcion(), e.getGenero(), e.getPlataforma(), e.getNacionalidad(), e.getCompañia(), e.getAño(), e.getEdadRecomendada(), e.getEnlace());
            juegosConFotos f = new juegosConFotos(e.getId(), e.getTitulo(), imagen, e.getDescripcion(), e.getGenero(), e.getPlataforma(), e.getNacionalidad(), e.getCompañia(), e.getAño(), e.getEdadRecomendada(), e.getEnlace());
            datosConFoto.add(f);

            foto.setImageDrawable(imagen);
            texto.setText(elemento.getTitulo());
        }*/



        //Adaptador adaptador = new Adaptador(this, datosConFoto);
        //volver.setAdapter(adaptador);
/*
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
            }
    });
*/
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
                ((TextView)findViewById(R.id.txtPlataformas)).setText("Ver comentarios pulsada!");
                return true;
            case R.id.addComentario:
                ((TextView)findViewById(R.id.txtPlataformas)).setText("Añadir comentarios pulsada!");
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }
}