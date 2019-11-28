package com.AballayGabriel.petfinder.model;

public class LoginView {
    private String email;
    private String clave;
    private int estado;

    public LoginView() {
    }

    public LoginView(String email, String clave, int estado) {
        this.email = email;
        this.clave = clave;
        this.estado = estado;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "LoginView{" +
                "email='" + email + '\'' +
                ", clave='" + clave + '\'' +
                ", estado=" + estado +
                '}';
    }
}
