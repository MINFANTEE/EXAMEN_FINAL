package com.example.examenfinal2022.entities;

import java.util.List;


public class Cuenta {


    public List<Moviment> l_moviment;

    public int id;

    public String nombre;

    public String saldoIni;

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getSaldoIni() {
        return saldoIni;
    }


    public void setId(int id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setSaldoIni(String saldoIni) {
        this.saldoIni = saldoIni;
    }

    public Cuenta(List<Moviment> l_moviment, int id, String nombre, String saldoIni) {
        this.l_moviment = l_moviment;
        this.id = id;
        this.nombre = nombre;
        this.saldoIni = saldoIni;
    }

    public List<Moviment> getL_moviment() {
        return l_moviment;
    }

    public void setL_moviment(List<Moviment> l_moviment) {
        this.l_moviment = l_moviment;
    }

    public Cuenta() {
    }
}
