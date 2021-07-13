package edu.fiuba.algo3.modelo;

import java.util.List;

public class Pais { //todo: crear interfaz de pais

    private final String nombre;
    private Ejercitos ejercitos;
    private List<String> limitrofes;
    private Jugador jugador;

    Pais(String nombre, List<String> limitrofes, Ejercitos ejercitos) {
        this.nombre = nombre;
        this.limitrofes = limitrofes;
        this.ejercitos = ejercitos;
    }

    public void agregarLimitrofe(String pais) {
        limitrofes.add(pais);
    }


    //TODO esto o getter?
    public boolean esLimitrofeCon(Pais pais) {
        return pais.esLimitrofeCon(nombre);
    }

    public boolean esLimitrofeCon(String pais) {
        return limitrofes.contains(pais);
    }


    public Ejercitos getEjercitos() {
        return ejercitos;
    }

    public boolean noTieneEjercitos() {
        return ejercitos.noQuedanEjercitos();
    }

    public boolean cantidadEjercitosSuperiorA(int cantidad) {
        return ejercitos.cantidadEjercitosSuperiorA(cantidad);
    }

    public void agregarEjercitos(int cantidad) {
        ejercitos.agregarEjercitos(cantidad);
    }

    public void quitarEjercitos(int cantidad) {
        ejercitos.quitarEjercitos(cantidad);
    } //todo: validar que no quede en numeros negativos y conquista

    public void transferirEjercitosA(Pais paisDestino, int cantidad) {
        ejercitos.transferirEjercitos(paisDestino, cantidad);
    }

    public void asignarJugador(Jugador unJugador) {
        jugador = unJugador;
    }


    public void fueConquistadoPor(Pais atacante) {
        jugador = atacante.getJugador();
        ejercitos = new Ejercitos(0);
        atacante.transferirEjercitosA(this,1);
    }

    public Jugador getJugador() {
        return jugador;
    }
}

