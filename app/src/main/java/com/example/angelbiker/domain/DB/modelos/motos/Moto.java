package com.example.angelbiker.domain.DB.modelos.motos;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Motos")
public class Moto {

    @PrimaryKey @NonNull
    private String nombre;
    @ColumnInfo @NonNull
    private int cilindrada;
    @ColumnInfo @NonNull private int anio;
    @ColumnInfo @NonNull private int potencia;
    @ColumnInfo @NonNull private int manejo;
    @ColumnInfo @NonNull private int velPunta;
    @ColumnInfo @NonNull private int aerodinamica;
    @ColumnInfo @NonNull private int frenado;
    @ColumnInfo @NonNull private int aceleracion;
    @ColumnInfo @NonNull private int cilindros;
    @ColumnInfo @NonNull private int parMotor;
    @ColumnInfo @NonNull private int peso;
    @ColumnInfo @NonNull private int precio;
    @ColumnInfo @NonNull private String suspension;
    @ColumnInfo @NonNull private String motor;
    @ColumnInfo @NonNull private String frenos;
    @ColumnInfo @NonNull private String especificaciones;
    @ColumnInfo @NonNull private String historia;
    @ColumnInfo @NonNull private String categoria;
    @ColumnInfo @NonNull private String marca;
    @ColumnInfo @NonNull private int miniatura;
    @ColumnInfo @NonNull private String tipo_carnet;

    public Moto(){

    }
    public Moto(int cilindrada, int anio, int potencia, int manejo, int velPunta, int aerodinamica, int frenado, int aceleracion, int cilindros, int parMotor, int peso, int precio, String nombre, String suspension, String motor, String frenos, String especificaciones, String historia, String categoria, String marca, String tipo_carnet,  int miniatura ) {
        this.nombre = nombre;
        this.cilindrada = cilindrada;
        this.anio = anio;
        this.potencia = potencia;
        this.precio = precio;

        this.manejo = manejo;
        this.velPunta = velPunta;
        this.aerodinamica = aerodinamica;
        this.frenado = frenado;
        this.aceleracion = aceleracion;


        this.cilindros = cilindros;
        this.parMotor = parMotor;
        this.peso = peso;

        this.suspension = suspension;
        this.motor = motor;
        this.frenos = frenos;
        this.especificaciones = especificaciones;
        this.historia = historia;
        this.categoria = categoria;
        this.marca = marca;
        this.tipo_carnet= tipo_carnet;
        this.miniatura = miniatura;

    }

    public int getMiniatura() {
        return miniatura;
    }

    public void setMiniatura(int miniatura) {
        this.miniatura = miniatura;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public int getCilindrada() {
        return cilindrada;
    }

    public void setCilindrada(int cilindrada) {
        this.cilindrada = cilindrada;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public int getPotencia() {
        return potencia;
    }

    public void setPotencia(int potencia) {
        this.potencia = potencia;
    }

    public int getManejo() {
        return manejo;
    }

    public void setManejo(int manejo) {
        this.manejo = manejo;
    }

    public int getVelPunta() {
        return velPunta;
    }

    public void setVelPunta(int velPunta) {
        this.velPunta = velPunta;
    }

    public int getAerodinamica() {
        return aerodinamica;
    }

    public void setAerodinamica(int aerodinamica) {
        this.aerodinamica = aerodinamica;
    }

    public int getFrenado() {
        return frenado;
    }

    public void setFrenado(int frenado) {
        this.frenado = frenado;
    }

    public int getAceleracion() {
        return aceleracion;
    }

    public void setAceleracion(int aceleracion) {
        this.aceleracion = aceleracion;
    }

    public int getCilindros() {
        return cilindros;
    }

    public void setCilindros(int cilindros) {
        this.cilindros = cilindros;
    }

    public int getParMotor() {
        return parMotor;
    }

    public void setParMotor(int parMotor) {
        this.parMotor = parMotor;
    }

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getSuspension() {
        return suspension;
    }

    public void setSuspension(String suspension) {
        this.suspension = suspension;
    }

    public String getMotor() {
        return motor;
    }

    public void setMotor(String motor) {
        this.motor = motor;
    }

    public String getFrenos() {
        return frenos;
    }

    public void setFrenos(String frenos) {
        this.frenos = frenos;
    }

    public String getEspecificaciones() {
        return especificaciones;
    }

    public void setEspecificaciones(String especificaciones) {
        this.especificaciones = especificaciones;
    }

    public String getHistoria() {
        return historia;
    }

    public void setHistoria(String historia) {
        this.historia = historia;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    @NonNull
    public String getTipo_carnet() {
        return tipo_carnet;
    }

    public void setTipo_carnet(@NonNull String tipo_carnet) {
        this.tipo_carnet = tipo_carnet;
    }

    @Override
    public String toString() {
        return "Moto{" +
                "cilindrada=" + cilindrada +
                ", anio=" + anio +
                ", potencia=" + potencia +
                ", manejo=" + manejo +
                ", velPunta=" + velPunta +
                ", aerodinamica=" + aerodinamica +
                ", frenado=" + frenado +
                ", aceleracion=" + aceleracion +
                ", cilindros=" + cilindros +
                ", parMotor=" + parMotor +
                ", peso=" + peso +
                ", nombre='" + nombre + '\'' +
                ", suspension='" + suspension + '\'' +
                ", motor='" + motor + '\'' +
                ", frenos='" + frenos + '\'' +
                ", especificaciones='" + especificaciones + '\'' +
                ", historia='" + historia + '\'' +
                ", categoria='" + categoria + '\'' +
                ", precio='"+ precio + '\''+
                ", marca='"+ marca + '\''+
                ", tipo_carnet='"+ tipo_carnet + '\''+
                ", miniatura='"+ miniatura + '\''+

                '}';
    }


}

