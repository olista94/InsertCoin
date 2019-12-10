package com.example.insertcoin;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.Drawable;

public class VariablesGlobales extends Application {

    private static String usuario = "";
    private static GestorBD baseDatos=null;
    private static String respuestaDialogo="";
    private static String tituloActual= "";

    public static String getTituloActual() {
        return tituloActual;
    }

    public static void setTituloActual(String tituloActuall) {
        VariablesGlobales.tituloActual = tituloActuall;
    }

    public static String getUsuario() {
        return usuario;
    }

    public static void setUsuario(String usuarioo) {
        usuario = usuarioo;
    }

    public static GestorBD getBaseDatos() {
        return baseDatos;
    }

    public static void setBaseDatos(GestorBD baseDatoss) {
        baseDatos = baseDatoss;
    }

    public static String getRespuestaDialogo() {
        return respuestaDialogo;
    }

    public static void setRespuestaDialogo(String respuestaDialogoo) {
        respuestaDialogo = respuestaDialogoo;
    }
}
