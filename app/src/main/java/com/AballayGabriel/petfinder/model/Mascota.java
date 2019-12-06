package com.AballayGabriel.petfinder.model;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.util.Log;
import android.widget.ImageView;

import org.json.JSONException;

import java.io.File;
import java.time.LocalDate;

public class Mascota {
    private int mascotaId;
    private String nombreMascota;
    private String raza;
    private String tamanio;
    private int edad;
    private String descripcion;
    private String foto;
    private String imagen;
    private Bitmap imagenMostar;
    private int estado;
    private String fecha;
    private int recompensaId;
    private String lugar;
    private int usuarioId;

    public Mascota() {
    }

    public Mascota(int mascotaId, String nombreMascota, String raza, String tamanio, int edad, String descripcion, String foto, String imagen, Bitmap imagenMostar, int estado, String fecha, int recompensaId, String lugar, int usuarioId) {
        this.mascotaId = mascotaId;
        this.nombreMascota = nombreMascota;
        this.raza = raza;
        this.tamanio = tamanio;
        this.edad = edad;
        this.descripcion = descripcion;
        this.foto = foto;
        this.imagen = imagen;
        this.imagenMostar = imagenMostar;
        this.estado = estado;
        this.fecha = fecha;
        this.recompensaId = recompensaId;
        this.lugar = lugar;
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

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public Bitmap getImagenMostar() {
        return imagenMostar;
    }

    public void setImagenMostar(Bitmap imagenMostar) {
        this.imagenMostar = imagenMostar;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getRecompensaId() {
        return recompensaId;
    }

    public void setRecompensaId(int recompensaId) {
        this.recompensaId = recompensaId;
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
        return "Mascota{" +
                "mascotaId=" + mascotaId +
                ", nombreMascota='" + nombreMascota + '\'' +
                ", raza='" + raza + '\'' +
                ", tamanio='" + tamanio + '\'' +
                ", edad=" + edad +
                ", descripcion='" + descripcion + '\'' +
                ", foto='" + foto + '\'' +
                ", imagen='" + imagen + '\'' +
                ", imagenMostar=" + imagenMostar +
                ", estado=" + estado +
                ", fecha='" + fecha + '\'' +
                ", recompensaId=" + recompensaId +
                ", lugar='" + lugar + '\'' +
                ", usuarioId=" + usuarioId +
                '}';
    }
}
