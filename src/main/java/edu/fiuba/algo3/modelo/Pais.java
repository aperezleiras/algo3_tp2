package edu.fiuba.algo3.modelo;

import java.util.ArrayList;
import java.util.List;

public class Pais {

    private final String nombre;
    private final Ejercitos ejercitos;
    private final List<Pais> limitrofes;

    Pais (String nombre, List<Pais> limitrofes, Ejercitos ejercitos) {
        this.nombre = nombre;
        this.limitrofes = limitrofes;
        this.ejercitos = ejercitos;
    }

    public Ejercitos getEjercitos() {
        return ejercitos;
    }

    public void agregarLimitrofe(Pais pais) {
        limitrofes.add(pais);
    }

    public void colocarEjercitos(int cantidad) {
        ejercitos.agregarEjercitos(cantidad);
    }

    public boolean cantidadEjercitosSuperiorA(int cantidad) {
        return ejercitos.cantidadEjercitosSuperiorA(cantidad);
    }

    public boolean esLimitrofeCon(Pais pais) {
        return limitrofes.contains(pais);
    }

    public void transferirEjercitosA(Pais paisDestino, int cantidad) {
        ejercitos.transferirEjercitos(paisDestino, cantidad);
    }

    public void asignarJugador(Jugador jugador) {
        ejercitos.asignarJugador(jugador);
    }

}

