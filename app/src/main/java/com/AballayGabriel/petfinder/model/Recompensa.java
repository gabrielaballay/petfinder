package com.AballayGabriel.petfinder.model;

public class Recompensa {
    private int recompensaId;
    private double monto;
    private String tiempo;
    private int estado;

    public Recompensa() {
    }

    public Recompensa(int recompensaId, double monto, String tiempo, int estado) {
        this.recompensaId = recompensaId;
        this.monto = monto;
        this.tiempo = tiempo;
        this.estado = estado;
    }

    public int getRecompensaId() {
        return recompensaId;
    }

    public void setRecompensaId(int recompensaId) {
        this.recompensaId = recompensaId;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public String getTiempo() {
        return tiempo;
    }

    public void setTiempo(String tiempo) {
        this.tiempo = tiempo;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Recompensa{" +
                "recompensaId=" + recompensaId +
                ", monto=" + monto +
                ", tiempo='" + tiempo + '\'' +
                ", estado=" + estado +
                '}';
    }
}
