package com.example.angelbiker.domain.DB.modelos.motos;

public class MotoMinimal {

    private String nombre;
    private int miniatura;

    public MotoMinimal(String nombre, int miniatura) {
        this.nombre = nombre;
        this.miniatura = miniatura;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getMiniatura() {
        return miniatura;
    }

    public void setMiniatura(int miniatura) {
        this.miniatura = miniatura;
    }

    @Override
    public String toString() {
        return "userMinimal{" +
                "nombre='" + nombre + '\'' +
                ", miniatura=" + miniatura +
                '}';
    }
}
