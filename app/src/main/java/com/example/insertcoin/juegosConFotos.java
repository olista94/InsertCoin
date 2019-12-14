package com.example.insertcoin;

import android.graphics.drawable.Drawable;

public class juegosConFotos {
    long id;
    String titulo;
    Drawable portada;
    String descripcion;
    String genero;
    String plataforma;
    String nacionalidad;
    String compañia;
    int año;
    String edadRecomendada;

    public juegosConFotos(long id, String titulo, Drawable portada, String descripcion, String genero, String plataforma, String nacionalidad, String compañia, int año, String edadRecomendada) {
        this.id = id;
        this.titulo = titulo;
        this.portada = portada;
        this.descripcion = descripcion;
        this.genero = genero;
        this.plataforma = plataforma;
        this.nacionalidad = nacionalidad;
        this.compañia = compañia;
        this.año = año;
        this.edadRecomendada = edadRecomendada;
    }

    public juegosConFotos(long id, String titulo, String descripcion, String genero, String plataforma, String nacionalidad, String compañia, int año, String edadRecomendada) {
        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.genero = genero;
        this.plataforma = plataforma;
        this.nacionalidad = nacionalidad;
        this.compañia = compañia;
        this.año = año;
        this.edadRecomendada = edadRecomendada;
    }

    public juegosConFotos(String titulo) {
        this.titulo = titulo;
    }

    public long getId(){
        return id;

    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Drawable getPortada() {
        return portada;
    }

    public void setPortada(Drawable portada) {
        this.portada = portada;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getPlataforma() {
        return plataforma;
    }

    public void setPlataforma(String plataforma) {
        this.plataforma = plataforma;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public String getCompañia() {
        return compañia;
    }

    public void setCompañia(String compañia) {
        this.compañia = compañia;
    }

    public int getAño() {
        return año;
    }

    public void setAño(int año) {
        this.año = año;
    }

    public String getEdadRecomendada() {
        return edadRecomendada;
    }

    public void setEdadRecomendada(String edadRecomendada) {
        this.edadRecomendada = edadRecomendada;
    }
}
