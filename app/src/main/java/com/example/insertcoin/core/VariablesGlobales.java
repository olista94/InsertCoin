package com.example.insertcoin.core;

import android.app.Application;

public class VariablesGlobales extends Application {

    private static String usuario = "";
    private static GestorBD baseDatos=null;
    private static String respuestaDialogo="";
    private static String tituloActual= "";
    private static String portada="";
    private static String descripcion="";
    private static String genero="";
    private static String plataforma="";
    private static String nacionalidad="";
    private static String compania="";
    private static int anho=0;
    private static String edadRecomendad="";

    private static String mensajeBD="";

    public static String getPortada() {
        return portada;
    }

    public static void setPortada(String portadaa) {
        VariablesGlobales.portada = portadaa;
    }

    public static String getDescripcion() {
        return descripcion;
    }

    public static void setDescripcion(String descripcionn) {
        VariablesGlobales.descripcion = descripcionn;
    }

    public static String getGenero() {
        return genero;
    }

    public static void setGenero(String generoo) {
        VariablesGlobales.genero = genero;
    }

    public static String getPlataforma() {
        return plataforma;
    }

    public static void setPlataforma(String plataformaa) {
        VariablesGlobales.plataforma = plataformaa;
    }

    public static String getNacionalidad() {
        return nacionalidad;
    }

    public static void setNacionalidad(String nacionalidadd) {
        VariablesGlobales.nacionalidad = nacionalidadd;
    }

    public static String getCompania() {
        return compania;
    }

    public static void setCompania(String companiaa) {
        VariablesGlobales.compania = companiaa;
    }

    public static int getAnho() {
        return anho;
    }

    public static void setAnho(int anhoo) {
        VariablesGlobales.anho = anhoo;
    }

    public static String getEdadRecomendad() {
        return edadRecomendad;
    }

    public static void setEdadRecomendad(String edadRecomendadd) {
        VariablesGlobales.edadRecomendad = edadRecomendadd;
    }

    public static String getMensajeBD() {
        return mensajeBD;
    }

    public static void setMensajeBD(String mensajeBDD) {
        VariablesGlobales.mensajeBD = mensajeBDD;
    }

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
