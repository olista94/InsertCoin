package com.example.insertcoin;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class GestorBD extends SQLiteOpenHelper {

    //Tablas
    public static final String BDnombre = "insertcoin.db";
    public static final String usuarios = "usuarios";
    public static final String juegos = "juegos";
    public static final String comenta = "comenta";
    public static final String puntua = "puntua";

    // Creacion de las tablas
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table if not exists " + usuarios + " (login text PRIMARY KEY, password text, nombre text, apellidos text, email text) ");
        db.execSQL("create table if not exists " + juegos + " (titulo text PRIMARY KEY, portada text, descripcion text, genero text, plataforma text, nacionalidad text, compañia text, año int, edad_recomendada text, enlace text) ");
        db.execSQL("create table if not exists " + comenta + " (login text, titulo text, comentario text, PRIMARY KEY (login, titulo) ,FOREIGN KEY (login) REFERENCES usuarios(login), FOREIGN KEY (titulo) REFERENCES juegos(titulo)) ");
        db.execSQL("create table if not exists " + puntua + " (login text, titulo text, puntuacion integer, PRIMARY KEY (login, titulo) ,FOREIGN KEY (login) REFERENCES usuarios(login), FOREIGN KEY (titulo) REFERENCES juegos(titulo)) ")


        db.execSQL("INSERT INTO usuarios (login, password, nombre, apellidos, email) VALUES ('olrivera','olrivera','Oscar','Lista Rivera','olista@gmail.com')");
        db.execSQL("INSERT INTO usuarios (login, password, nombre, apellidos, email) VALUES ('ypgarcia','ypgarcia','Iago','Perez Garcia','ypgarcia@gmail.com')");
        db.execSQL("INSERT INTO usuarios (login, password, nombre, apellidos, email) VALUES ('ritaconde','ritaconde','Rita','Conde Sanchez','ritaorita@gmail.com')");
        db.execSQL("INSERT INTO usuarios (login, password, nombre, apellidos, email) VALUES ('abelardoglez','abelardoglez','Abelardo','Gonzale Villamil','villamilaberlard@gmail.com')");

        db.execSQL("INSERT INTO juegos (titulo, portada, descripcion, genero, plataforma, nacionalidad, compañia, año, edad_recomendada, enlace) VALUES ('Super Mario 64','','Las aventuras de Mario en el rescate de la princesa Peach','aventuras','Nintendo 64','Japon','Nintendo',1996,'Todos los publicos','www.google.ru')");
        db.execSQL("INSERT INTO juegos (titulo, portada, descripcion, genero, plataforma, nacionalidad, compañia, año, edad_recomendada, enlace) VALUES ('Grandt Theft Auto V','','Las vidas de delincuentes de poca monta se cruzan','accion','PS4, PS3, XBOX 360, PC','EEUU','Rockstar',2013,'+18','www.google.es')");

    }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        }

        public GestorBD(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
            super(context, BDnombre, factory, version);
        }

        public ArrayList<juegos> obtenerDatosPelis(SQLiteDatabase db){
            // Datos de la BD
            Cursor datos = db.rawQuery("SELECT * FROM peliculas", null);

            ArrayList<juegos> lista = new ArrayList<>();
            while(datos.moveToNext()){
                lista.add(new juegos(datos.getString(0), datos.getString(1), datos.getString(2), datos.getString(3), datos.getString(4), datos.getString(5), datos.getString(6), datos.getInt(7), datos.getString(8), datos.getString(9));
            }

            return lista;
        }
}
