package com.springbootsecurityjwt.security.payload;

public class LoginRequest {

    private String usuario;
    private String password;

    public LoginRequest() {
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
