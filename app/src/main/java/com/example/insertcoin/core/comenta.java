package com.example.insertcoin.core;

public class comenta {
    long id;
    String login;
    String titulo;
    String comentario;

    public comenta(String login, String titulo, String comentario) {
        this.login = login;
        this.titulo = titulo;
        this.comentario = comentario;
    }

    public comenta(long id, String login, String titulo, String comentario) {
        this.id = id;
        this.login = login;
        this.titulo = titulo;
        this.comentario = comentario;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
}
