package com.example.insertcoin;

public class puntua {

    String login;
    String titulo;
    float valoracion;

    public puntua(String login, String titulo, float valoracion) {
        this.login = login;
        this.titulo = titulo;
        this.valoracion = valoracion;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public float getValoracion() {
        return valoracion;
    }

    public void setValoracion(float valoracion) {
        this.valoracion = valoracion;
    }
}