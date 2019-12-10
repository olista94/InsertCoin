package com.example.insertcoin;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.io.Serializable;
import java.util.ArrayList;

public class GestorBD extends SQLiteOpenHelper implements Serializable {

    //Tablas
    public static String BDnombre = "insertcoin.db";
    public static final String usuarios = "usuarios";
    public static final String juegos = "juegos";
    public static final String comenta = "comenta";
    public static final String puntua = "puntua";

    // Creacion de las tablas
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS "+usuarios);
        db.execSQL("DROP TABLE IF EXISTS "+juegos);
        db.execSQL("DROP TABLE IF EXISTS "+comenta);

        db.execSQL("create table " + usuarios + " (login text PRIMARY KEY, password text, nombre text, apellidos text, email text) ");
        db.execSQL("create table " + juegos + " (titulo text PRIMARY KEY, portada text, descripcion text, genero text, plataforma text, nacionalidad text, compania text, anho int, edad_recomendada text, enlace text) ");
        db.execSQL("create table " + comenta + " (login text, titulo text, comentario text, PRIMARY KEY (login, titulo, comentario) ,FOREIGN KEY (login) REFERENCES usuarios(login), FOREIGN KEY (titulo) REFERENCES juegos(titulo)) ");
        db.execSQL("create table " + puntua + " (login text, titulo text, puntuacion integer, PRIMARY KEY (login, titulo) ,FOREIGN KEY (login) REFERENCES usuarios(login), FOREIGN KEY (titulo) REFERENCES juegos(titulo)) ");


        db.execSQL("INSERT INTO usuarios (login, password, nombre, apellidos, email) VALUES ('a','a','A','A A','A@gmail.com')");
        db.execSQL("INSERT INTO usuarios (login, password, nombre, apellidos, email) VALUES ('olrivera','olrivera','Oscar','Lista Rivera','olista@gmail.com')");
        db.execSQL("INSERT INTO usuarios (login, password, nombre, apellidos, email) VALUES ('ypgarcia','ypgarcia','Iago','Perez Garcia','ypgarcia@gmail.com')");
        db.execSQL("INSERT INTO usuarios (login, password, nombre, apellidos, email) VALUES ('ritaconde','ritaconde','Rita','Conde Sanchez','ritaorita@gmail.com')");
        db.execSQL("INSERT INTO usuarios (login, password, nombre, apellidos, email) VALUES ('abelardoglez','abelardoglez','Abelardo','Gonzalez Villamil','villamilaberlard@gmail.com')");

        db.execSQL("INSERT INTO juegos (titulo, portada, descripcion, genero, plataforma, nacionalidad, compania, anho, edad_recomendada, enlace) VALUES ('Super Mario 64','supermario64','Las aventuras de Mario en el rescate de la princesa Peach','Aventuras','Nintendo 64','Japon','Nintendo',1996,'Todos los publicos','www.google.ru')");
        db.execSQL("INSERT INTO juegos (titulo, portada, descripcion, genero, plataforma, nacionalidad, compania, anho, edad_recomendada, enlace) VALUES ('Grandt Theft Auto V','gtav','Las vidas de delincuentes de poca monta se cruzan','Accion','PS4, PS3, XBOX 360, PC','EEUU','Rockstar',2013,'+18','www.google.es')");
        db.execSQL("INSERT INTO juegos (titulo, portada, descripcion, genero, plataforma, nacionalidad, compania, anho, edad_recomendada, enlace) VALUES ('The Legend of Zelda:The Ocarina of Time','zeldaocarinaoftime','Link rescatando a Zelda','Aventuras','Nintendo 64','Japon','Nintendo',1998,'Todos los publicos','www.google.es')");
        db.execSQL("INSERT INTO juegos (titulo, portada, descripcion, genero, plataforma, nacionalidad, compania, anho, edad_recomendada, enlace) VALUES ('Tony Hawks Pro Skater 2','tonyhawksproskater2','Simulador de skate','Deportes','PS2, PC, XBOX, Nintendo 64','EEUU','Neversoft',2000,'Todos los publicos','www.google.es')");
        db.execSQL("INSERT INTO juegos (titulo, portada, descripcion, genero, plataforma, nacionalidad, compania, anho, edad_recomendada, enlace) VALUES ('Soul Calibur','soulcalibur','Io k se loko','Lucha','PS1, Arcade','Japon','Namco',1995,'+13','www.google.es')");
        db.execSQL("INSERT INTO juegos (titulo, portada, descripcion, genero, plataforma, nacionalidad, compania, anho, edad_recomendada, enlace) VALUES ('Super Mario Galaxy','supermariogalaxy1','Las aventuras espaciales de Mario y Luigi','Plataformas','Wii','Japon','Nintendo',2007,'Todos los publicos','www.google.es')");
        db.execSQL("INSERT INTO juegos (titulo, portada, descripcion, genero, plataforma, nacionalidad, compania, anho, edad_recomendada, enlace) VALUES ('Super Mario Galaxy 2','supermariogalaxy2','Continuacion de las aventuras espaciales de Mario y Luigi','Plataformas','Wii','Japon','Nintendo',2010,'Todos los publicos','www.google.es')");
        db.execSQL("INSERT INTO juegos (titulo, portada, descripcion, genero, plataforma, nacionalidad, compania, anho, edad_recomendada, enlace) VALUES ('Red Dead Redemption 2','reddeadredemption2','Una banda de forajidos debera atracar un banco sin ser capturados','Mundo abierto','PS4, XBOX Onw, PC','EEUU','Rockstar',2018,'+18','www.google.es')");
        db.execSQL("INSERT INTO juegos (titulo, portada, descripcion, genero, plataforma, nacionalidad, compania, anho, edad_recomendada, enlace) VALUES ('Metroid Prime','metroidprime','La aventuras de Sheamus en su lucha contra piratas espaciales','Aventuras','GameCube, Wii','Japon','Nintendo',2003,'+12','www.google.es')");
        db.execSQL("INSERT INTO juegos (titulo, portada, descripcion, genero, plataforma, nacionalidad, compania, anho, edad_recomendada, enlace) VALUES ('NBA2K11','nba2k11','Simulador de NBA','Deportes','PS3, PS2, Wii, PSP, XBOX 360, PC','EEUU','2K Sports',2010,'+3','www.google.es')");
        db.execSQL("INSERT INTO juegos (titulo, portada, descripcion, genero, plataforma, nacionalidad, compania, anho, edad_recomendada, enlace) VALUES ('Fortnite','fortnite','Battle Royale de 100 jugadores','Shooter','PC, Android, PS4, XBOX One, macOS','EEUU','Epic Games',2017,'+12','www.google.es')");
        db.execSQL("INSERT INTO juegos (titulo, portada, descripcion, genero, plataforma, nacionalidad, compania, anho, edad_recomendada, enlace) VALUES ('Skyrim','skyrim','Juego en primera persona,en la que habra que elegir bando en una guerra civil','Aventuras','PS4, PS3, XBOX 360, PC, XBOX One, Switch','EEUU','Bethesda',2011,'+18','www.google.es')");

        db.execSQL("INSERT INTO comenta (login, titulo, comentario) VALUES ('a','Super Mario 64','Me gusto mas el del increible hombre sardina,pero aprueba')");
        db.execSQL("INSERT INTO comenta (login, titulo, comentario) VALUES ('a','Super Mario 64','Comentario 2 de a')");
        db.execSQL("INSERT INTO comenta (login, titulo, comentario) VALUES ('olrivera','Super Mario 64','Pues no')");

        System.out.println("Tablas creadas"+"++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public GestorBD(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        this.BDnombre = name;
    }

    public ArrayList<juegos> obtenerDatosJuegos(SQLiteDatabase db){
        // Datos de la BD
        Cursor datos = db.rawQuery("SELECT * FROM juegos", null);

        ArrayList<juegos> lista = new ArrayList<>();
        long cont = 0;
        while(datos.moveToNext()){
            lista.add(new juegos(cont, datos.getString(0), datos.getString(1), datos.getString(2), datos.getString(3), datos.getString(4), datos.getString(5), datos.getString(6), datos.getInt(7), datos.getString(8), datos.getString(9)));
            //datos.getString()
            cont++;
        }

        return lista;
    }

    public Cursor juegosShowcurrent(SQLiteDatabase db, String titulo){
        // Datos de la BD
        Cursor datos = db.rawQuery("SELECT * FROM juegos WHERE titulo = '"+titulo+"'", null);

        datos.moveToFirst();

        return datos;
    }

    public boolean comprobarLogin(SQLiteDatabase db,usuarios u){
        // Datos de la BD
        Cursor datos = db.rawQuery("SELECT * FROM usuarios WHERE login = '"+u.getLogin()+"' AND password = '"+u.getPassword()+"'", null);

        if(datos.moveToNext()){
            return true;
        }else{
            return false;
        }
    }

    public ArrayList<comenta> obtenerComentarios(SQLiteDatabase db,String titulo){
        // Datos de la BD
        Cursor datos = db.rawQuery("SELECT * FROM comenta WHERE titulo = '"+titulo+"'", null);

        ArrayList<comenta> lista = new ArrayList<>();
        while(datos.moveToNext()){
            lista.add(new comenta(datos.getString(0), datos.getString(1), datos.getString(2)));
        }

        return lista;
    }

    public Cursor usuariosShowcurrent(SQLiteDatabase db, String login){
        // Datos de la BD
        Cursor datos = db.rawQuery("SELECT * FROM usuarios WHERE login = '"+login+"'", null);

        datos.moveToFirst();

        return datos;
    }

    public void addComentario(String login, String titulo, String comentario){

        SQLiteDatabase db = this.getWritableDatabase();
        try {
            db.beginTransaction();
            db.execSQL( "INSERT INTO comenta VALUES ('"+login+"','"+titulo+"','"+comentario+"')"
                    );
            db.setTransactionSuccessful();
            Log.d("DBManager","Exito al insertar");
        }catch (SQLException exc){
            Log.d("DBManager","Asido un fracaso");
        }finally {
            db.endTransaction();
        }
    }

    public void addUsuario(String login, String password, String nombre, String apellidos, String email){

        SQLiteDatabase db = this.getWritableDatabase();
        try {
            db.beginTransaction();
            db.execSQL( "INSERT INTO usuarios VALUES ('"+login+"','"+password+"','"+nombre+"','"+apellidos+"','"+email+"')"
            );
            db.setTransactionSuccessful();
            Log.d("DBManager","Exito al insertar");
        }catch (SQLException exc){
            Log.d("DBManager","Asido un fracaso");
        }finally {
            db.endTransaction();
        }
    }

    public void addJuego(String titulo, String portada, String descripcion, String genero, String plataforma, String nacionalidad, String compania, int anho, String edadRecomendad, String enlace){

        SQLiteDatabase db = this.getWritableDatabase();
        try {
            db.beginTransaction();
            db.execSQL( "INSERT INTO juegos VALUES" +
                    " ('"+titulo+"','"+portada+"','"+descripcion+"','"+genero+"','"+plataforma+"','"+nacionalidad+"','"+compania+"','"+anho+"', '"+edadRecomendad+"', '"+enlace+"')"
            );
            db.setTransactionSuccessful();
            Log.d("DBManager","Exito al insertar");
        }catch (SQLException exc){
            Log.d("DBManager","Asido un fracaso");
        }finally {
            db.endTransaction();
        }
    }


    }





