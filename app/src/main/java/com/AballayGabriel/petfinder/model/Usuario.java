package com.AballayGabriel.petfinder.model;

public class Usuario {
    private int UsuarioId;
    private String Apellido;
    private String Nombre;
    private String Ciudad;
    private String Direccion;
    private String Telefono;
    private String Email;
    private String Clave;
    private int Estado;
    private int ProvinciaId;

    public Usuario() {
    }

    public Usuario(int usuarioId, String apellido, String nombre, String ciudad, String direccion, String telefono, String email, String clave, int estado, int provinciaId) {
        UsuarioId = usuarioId;
        Apellido = apellido;
        Nombre = nombre;
        Ciudad = ciudad;
        Direccion = direccion;
        Telefono = telefono;
        Email = email;
        Clave = clave;
        Estado = estado;
        ProvinciaId = provinciaId;
    }

    public int getUsuarioId() {
        return UsuarioId;
    }

    public void setUsuarioId(int usuarioId) {
        UsuarioId = usuarioId;
    }

    public String getApellido() {
        return Apellido;
    }

    public void setApellido(String apellido) {
        Apellido = apellido;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getCiudad() {
        return Ciudad;
    }

    public void setCiudad(String ciudad) {
        Ciudad = ciudad;
    }

    public String getDireccion() {
        return Direccion;
    }

    public void setDireccion(String direccion) {
        Direccion = direccion;
    }

    public String getTelefono() {
        return Telefono;
    }

    public void setTelefono(String telefono) {
        Telefono = telefono;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getClave() {
        return Clave;
    }

    public void setClave(String clave) {
        Clave = clave;
    }

    public int getEstado() {
        return Estado;
    }

    public void setEstado(int estado) {
        Estado = estado;
    }

    public int getProvinciaId() {
        return ProvinciaId;
    }

    public void setProvinciaId(int provinciaId) {
        ProvinciaId = provinciaId;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "UsuarioId=" + UsuarioId +
                ", Apellido='" + Apellido + '\'' +
                ", Nombre='" + Nombre + '\'' +
                ", Ciudad='" + Ciudad + '\'' +
                ", Direccion='" + Direccion + '\'' +
                ", Telefono='" + Telefono + '\'' +
                ", Email='" + Email + '\'' +
                ", Clave='" + Clave + '\'' +
                ", Estado=" + Estado +
                ", ProvinciaId=" + ProvinciaId +
                '}';
    }
}
