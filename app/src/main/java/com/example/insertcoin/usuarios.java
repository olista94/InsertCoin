package com.example.insertcoin;

public class usuarios {
    String login;
    String password;
    String email;
    String nombre;
    String apellidos;

    public usuarios(String login, String password, String email, String nombre, String apellidos) {
        this.login = login;
        this.password = password;
        this.email = email;
        this.nombre = nombre;
        this.apellidos = apellidos;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }
}