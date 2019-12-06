package com.AballayGabriel.petfinder.model;

public class Encontrada {
    private int encontradaId;
    private String foto;
    private String imagen;
    private String descripcion;
    private String fecha;
    private String lugar;
    private int usuarioId;

    public Encontrada() {
    }

    public Encontrada(int encontradaId, String foto, String imagen, String descripcion, String fecha, String lugar, int usuarioId) {
        this.encontradaId = encontradaId;
        this.foto = foto;
        this.imagen = imagen;
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.lugar = lugar;
        this.usuarioId = usuarioId;
    }

    public int getEncontradaId() {
        return encontradaId;
    }

    public void setEncontradaId(int encontradaId) {
        this.encontradaId = encontradaId;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public int getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }

    @Override
    public String toString() {
        return "Encontrada{" +
                "encontradaId=" + encontradaId +
                ", foto='" + foto + '\'' +
                ", imagen='" + imagen + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", fecha='" + fecha + '\'' +
                ", lugar='" + lugar + '\'' +
                ", usuarioId=" + usuarioId +
                '}';
    }
}
