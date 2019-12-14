package com.example.insertcoin;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Array;
import java.nio.channels.FileChannel;
import java.util.Random;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;

public class Juegos_ADD extends AppCompatActivity {

    EditText ja_titulo = null;
    EditText ja_descripcion = null;
    EditText ja_genero = null;
    EditText ja_plataforma = null;
    EditText ja_nacionalidad = null;
    EditText ja_compania = null;
    EditText ja_anho = null;
    EditText ja_edadRecomendada = null;

    ImageView ja_portada = null;
    static final int SELECT_FILE = 1;

    int GALLERY = 1, CAMERA = 2;
    String rutaFoto = null;
    Random random = new Random();
    int cont = random.nextInt(1000);
    Intent datosRetorno = new Intent();

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
               /* String titulo = ja_titulo.getText().toString();
                String portada = String.valueOf(ja_portada.getTag());
                String descripcion = ja_descripcion.getText().toString();
                String genero = ja_genero.getText().toString();
                String plataforma = ja_plataforma.getText().toString();
                String nacionalidad = ja_nacionalidad.getText().toString();
                String compania = ja_compania.getText().toString();
                int anho = (Integer.parseInt(ja_anho.getText().toString()));
                String edadRecomendada = ja_edadRecomendada.getText().toString();


                bd.addJuego(titulo,portada,descripcion,genero,plataforma,nacionalidad,compania,anho,edadRecomendada);

                */

                validarJuego();

            }
        });

    }

    public void cargarImagen() {
        Intent galleryIntent;

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
            galleryIntent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
            galleryIntent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
            galleryIntent.addFlags(Intent.FLAG_GRANT_PERSISTABLE_URI_PERMISSION);
        }else{
            galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
        }

        galleryIntent.putExtra(Intent.EXTRA_LOCAL_ONLY, true);
        galleryIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        galleryIntent.setType("image/*");

        startActivityForResult(galleryIntent, GALLERY);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == this.RESULT_CANCELED) {
            return;
        }
        if (requestCode == GALLERY) {
            if (data != null) {
                Uri contentURI = data.getData();

                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                    final int takeFlags = data.getFlags() & Intent.FLAG_GRANT_READ_URI_PERMISSION;
                    ContentResolver resolver = this.getContentResolver();
                    resolver.takePersistableUriPermission(contentURI, takeFlags);
                }

                rutaFoto = contentURI.toString();

                ContextWrapper cw = new ContextWrapper(getApplicationContext());
                File directory = cw.getDir("profile", Context.MODE_PRIVATE);
                if (!directory.exists()) {
                    directory.mkdir();
                }

                String nombreFoto = "foto_" + cont + ".jpg";
                cont++;

                File mypath = new File(directory, nombreFoto);
                //System.out.println("directorioooooooooooooooooooooooooo"+directory);
                FileOutputStream fos = null;
                try {
                    fos = new FileOutputStream(mypath);
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), contentURI);
                    Bitmap resizedBitmap = Bitmap.createScaledBitmap(bitmap, bitmap.getWidth()/3, bitmap.getHeight()/3, false);
                    resizedBitmap.compress(Bitmap.CompressFormat.JPEG, 40, fos);
                    fos.close();
                    Toast.makeText(this, "Imagen guardada", Toast.LENGTH_LONG).show();
                } catch (Exception e) {
                    Log.e("SAVE_IMAGE", e.getMessage(), e);
                }

                rutaFoto = mypath.toString();
                //Log.d("NuevoUsuarioActivity", "--------------------------------- rutaFoto: " + rutaFoto);
                //Log.d("NuevoUsuarioActivity", "--------------------------------- contentURI: " + contentURI.toString());
                try {
                    Bitmap bitmap = BitmapFactory.decodeFile(mypath.getAbsolutePath());

                    ja_portada.setImageBitmap(bitmap);
                    ja_portada.setTag("Foto"+cont);

                    OutputStream out;
                    String root = Environment.getExternalStorageDirectory().getAbsolutePath()+"/";
                    File createDir = getApplicationContext().getExternalFilesDir(null);
                    if(createDir == null){
                        createDir = getApplicationContext().getFilesDir();
                    }
                   // File createDir = new File("C:\\Users\\micro\\AndroidStudioProjects\\InsertCoin\\app\\src\\main\\res\\drawable"+File.separator);
                    /*if(!createDir.exists()) {
                        createDir.mkdir();
                    }*/
                    //System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^"+createDir);
                    File file = new File(createDir,ja_portada.getTag()+".jpg");
                    //System.out.println("rutaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"+file);
                    file.createNewFile();
                    //System.out.println("DATAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA"+getRealPathFromURI_API19(getApplicationContext(),data.getData()));

                    copyFile(new File(getRealPathFromURI_API19(getApplicationContext(),data.getData())), file);
                    // System.out.println("Nameeeeeeeeeeeeeeeeeeeeeeeeeeeeee"+file.getName());
                    //System.out.println("Absoluteeeeeeeeeeeeeeeeeeeeeee"+file.getAbsolutePath());
                    rutaFoto = file.getAbsolutePath();

                    System.out.println("RUTAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA"+rutaFoto);
                    out = new FileOutputStream(file);


                    bitmap = ((BitmapDrawable)ja_portada.getDrawable()).getBitmap();
                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.JPEG,100,stream);
                    byte[] ByteArray = stream.toByteArray();

                    out.write(ByteArray);
                    //System.out.println("*************************************"+ByteArray);
                    out.close();
                    VariablesGlobales.setPortada(rutaFoto);

                } catch (Exception e) {
                    Log.d("Juegos_ADD", "--------------------------------- error: " + e.getMessage());
                }


            }

        }
    }

    private void copyFile(File sourceFile, File destFile) throws IOException {
        if (!sourceFile.exists()) {
            return;
        }

        FileChannel source = null;
        FileChannel destination = null;
        source = new FileInputStream(sourceFile).getChannel();
        destination = new FileOutputStream(destFile).getChannel();
        //System.out.println("Origeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeen"+source);
        //System.out.println("Destinooooooooooooooooooooooooooooooooooooooooooo"+destination);
        if (destination != null && source != null) {

            destination.transferFrom(source, 0, source.size());
        }
        if (source != null) {
            source.close();
        }
        if (destination != null) {
            destination.close();
        }


    }

    /*private String getRealPathFromURI(Uri contentUri) {

        String[] proj = { MediaStore.Images.Media.DATA };
        String res = null;
        Cursor cursor = getContentResolver().query(contentUri, proj, null, null, null);
        if(cursor.moveToFirst()){

            int column_index = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
            res = cursor.getString(column_index);
        }

        cursor.close();
        return res;
    }*/

    public static String getRealPathFromURI_API19(final Context context, final Uri uri) {

        final boolean isKitKat = Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT;

        // DocumentProvider
        if (isKitKat && DocumentsContract.isDocumentUri(context, uri)) {
            // ExternalStorageProvider
            if (isExternalStorageDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];

                if ("primary".equalsIgnoreCase(type)) {
                    return Environment.getExternalStorageDirectory() + "/" + split[1];
                }

                // TODO handle non-primary volumes
            }
            // DownloadsProvider
            else if (isDownloadsDocument(uri)) {

                final String id = DocumentsContract.getDocumentId(uri);
                final Uri contentUri = ContentUris.withAppendedId(
                        Uri.parse("content://downloads/public_downloads"), Long.valueOf(id));

                return getDataColumn(context, contentUri, null, null);
            }
            // MediaProvider
            else if (isMediaDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];

                Uri contentUri = null;
                if ("image".equals(type)) {
                    contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                } else if ("video".equals(type)) {
                    contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                } else if ("audio".equals(type)) {
                    contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                }

                final String selection = "_id=?";
                final String[] selectionArgs = new String[]{
                        split[1]
                };

                return getDataColumn(context, contentUri, selection, selectionArgs);
            }
        }
        // MediaStore (and general)
        else if ("content".equalsIgnoreCase(uri.getScheme())) {

            // Return the remote address
            if (isGooglePhotosUri(uri))
                return uri.getLastPathSegment();

            return getDataColumn(context, uri, null, null);
        }
        // File
        else if ("file".equalsIgnoreCase(uri.getScheme())) {
            return uri.getPath();
        }

        return null;
    }

    /**
     * Get the value of the data column for this Uri. This is useful for
     * MediaStore Uris, and other file-based ContentProviders.
     *
     * @param context       The context.
     * @param uri           The Uri to query.
     * @param selection     (Optional) Filter used in the query.
     * @param selectionArgs (Optional) Selection arguments used in the query.
     * @return The value of the _data column, which is typically a file path.
     */
    public static String getDataColumn(Context context, Uri uri, String selection,
                                       String[] selectionArgs) {

        Cursor cursor = null;
        final String column = "_data";
        final String[] projection = {
                column
        };

        try {
            cursor = context.getContentResolver().query(uri, projection, selection, selectionArgs,
                    null);
            if (cursor != null && cursor.moveToFirst()) {
                final int index = cursor.getColumnIndexOrThrow(column);
                return cursor.getString(index);
            }
        } finally {
            if (cursor != null)
                cursor.close();
        }
        return null;
    }


    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is ExternalStorageProvider.
     */
    public static boolean isExternalStorageDocument(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is DownloadsProvider.
     */
    public static boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is MediaProvider.
     */
    public static boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is Google Photos.
     */
    public static boolean isGooglePhotosUri(Uri uri) {
        return "com.google.android.apps.photos.content".equals(uri.getAuthority());
    }

    private void validarJuego(){

        String titulo = ja_titulo.getText().toString();
        String portada = ja_portada.getTransitionName();
        String descripcion = ja_descripcion.getText().toString();
        String genero = ja_genero.getText().toString();
        String plataforma = ja_plataforma.getText().toString();
        String nacionalidad = ja_nacionalidad.getText().toString();
        String compania = ja_compania.getText().toString();
//        int anho = (Integer.parseInt(ja_anho.getText().toString()));
        String edadRecomendada = ja_edadRecomendada.getText().toString();

        //System.out.println("anooooooooooooooooooooooooooooooooo"+anho);
        String mensaje = "";
        boolean toret = true;

        if( titulo.isEmpty() || titulo.equals(null) ){
            toret = false;
            mensaje = "Error: el titulo no puede ser vacio";
        }

        if( descripcion.isEmpty() || descripcion.equals(null) ){
            toret = false;
            mensaje = "Error: la descripción no puede ser vacia";
        }

        if( genero.isEmpty() || genero.equals(null) ){
            toret = false;
            mensaje = "Error: el genero no puede ser vacio";
        }

        if( plataforma.isEmpty() || plataforma.equals(null) ){
            toret = false;
            mensaje = "Error: el plataforma no puede ser vacio";
        }

        if( nacionalidad.isEmpty() || nacionalidad.equals(null) ){
            toret = false;
            mensaje = "Error: la nacionalidad no puede ser vacia";
        }

        if( compania.isEmpty() || compania.equals(null) ){
            toret = false;
            mensaje = "Error: la compañia no puede ser vacia";
        }

        if( ja_anho.getText().toString().isEmpty()){
            toret = false;
            mensaje = "Error: el ano no puede ser vacio";
        }

        if( edadRecomendada.isEmpty() || edadRecomendada.equals(null) ){
            toret = false;
            mensaje = "Error: la edad recomendada no puede ser vacio";
        }


        /*if(portada.isEmpty() || portada.equals(null)){
            toret = false;
            mensaje = "Error: la portada no puede ser vacia";
        }*/
        if(!toret){
            Toast.makeText(getApplicationContext(), mensaje, Toast.LENGTH_SHORT).show();
        }
        else{
            int anho = (Integer.parseInt(ja_anho.getText().toString()));

            bd.addJuego(titulo,VariablesGlobales.getPortada(),descripcion,genero,plataforma,nacionalidad,compania,anho,edadRecomendada);
            retorno();
        }

    } // Fin validarComprobaciones

    private void retorno(){
        VariablesGlobales.setTituloActual("");
        setResult(1);
        finish();
    } // Fin retorno




}
