package com.example.examenfinal2022.entities;

public class Moviment {

    public int id;

    public int cuentaId;

    public String Tipo;

    public String motivo;

    public String monto;

    public String latitud;

    public String longitud;

    public String image;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCuentaId() {
        return cuentaId;
    }

    public void setCuentaId(int cuentaId) {
        this.cuentaId = cuentaId;
    }

    public String getTipo() {
        return Tipo;
    }

    public void setTipo(String tipo) {
        Tipo = tipo;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public String getMonto() {
        return monto;
    }

    public void setMonto(String monto) {
        this.monto = monto;
    }

    public String getLatitud() {
        return latitud;
    }

    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }

    public String getLongitud() {
        return longitud;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Moviment(int id, int cuentaId, String tipo, String motivo, String monto, String latitud, String longitud, String image) {
        this.id = id;
        this.cuentaId = cuentaId;
        Tipo = tipo;
        this.motivo = motivo;
        this.monto = monto;
        this.latitud = latitud;
        this.longitud = longitud;
        this.image = image;
    }

    public Moviment() {
    }
}
