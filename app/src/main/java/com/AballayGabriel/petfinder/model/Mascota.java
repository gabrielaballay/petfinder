package com.AballayGabriel.petfinder.model;

import java.time.LocalDate;

public class Mascota {
    private int mascotaId;
    private String nombreMascota;
    private String raza;
    private String tamanio;
    private int edad;
    private String descripcion;
    private String foto;
    private int estado;
    private LocalDate fecha;
    private int recompensaId;
    private int localizaId;
    private int usuarioId;

    public Mascota() {
    }

    public Mascota(int mascotaId, String nombreMascota, String raza, String tamanio, int edad, String descripcion, String foto, int estado, LocalDate fecha, int recompensaId, int localizaId, int usuarioId) {
        this.mascotaId = mascotaId;
        this.nombreMascota = nombreMascota;
        this.raza = raza;
        this.tamanio = tamanio;
        this.edad = edad;
        this.descripcion = descripcion;
        this.foto = foto;
        this.estado = estado;
        this.fecha = fecha;
        this.recompensaId = recompensaId;
        this.localizaId = localizaId;
        this.usuarioId = usuarioId;
    }

    public int getMascotaId() {
        return mascotaId;
    }

    public void setMascotaId(int mascotaId) {
        this.mascotaId = mascotaId;
    }

    public String getNombreMascota() {
        return nombreMascota;
    }

    public void setNombreMascota(String nombreMascota) {
        this.nombreMascota = nombreMascota;
    }

    public String getRaza() {
        return raza;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }

    public String getTamanio() {
        return tamanio;
    }

    public void setTamanio(String tamanio) {
        this.tamanio = tamanio;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public int getRecompensaId() {
        return recompensaId;
    }

    public void setRecompensaId(int recompensaId) {
        this.recompensaId = recompensaId;
    }

    public int getLocalizaId() {
        return localizaId;
    }

    public void setLocalizaId(int localizaId) {
        this.localizaId = localizaId;
    }

    public int getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }

    @Override
    public String toString() {
        return "Mascota{" +
                "mascotaId=" + mascotaId +
                ", nombreMascota='" + nombreMascota + '\'' +
                ", raza='" + raza + '\'' +
                ", tamanio='" + tamanio + '\'' +
                ", edad=" + edad +
                ", descripcion='" + descripcion + '\'' +
                ", foto='" + foto + '\'' +
                ", estado=" + estado +
                ", fecha=" + fecha +
                ", recompensaId=" + recompensaId +
                ", localizaId=" + localizaId +
                ", usuarioId=" + usuarioId +
                '}';
    }
}
