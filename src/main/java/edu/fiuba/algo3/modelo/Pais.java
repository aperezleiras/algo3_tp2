package edu.fiuba.algo3.modelo;

import java.util.ArrayList;
import java.util.List;

public class Pais { //todo: crear interfaz de pais

    private final String nombre;
    private Ejercitos ejercitos;
    private final List<Pais> limitrofes;
    private Jugador jugador;

    Pais(String nombre, List<Pais> limitrofes, Ejercitos ejercitos) {
        this.nombre = nombre;
        this.limitrofes = limitrofes;
        this.ejercitos = ejercitos;
    }

    public void agregarLimitrofe(Pais pais) {
        limitrofes.add(pais);
    }

    public boolean esLimitrofeCon(Pais pais) {
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

    public Dados obtenerDadosAtacante() { return (ejercitos.calcularDadosAtacante()); }
    public Dados obtenerDadosDefensor() { return (ejercitos.calcularDadosDefensor()); }

    public void fueConquistadoPor(Pais atacante) {
        jugador = atacante.getJugador();
        ejercitos = new Ejercitos(0);
        atacante.transferirEjercitosA(this,1);
    }

    private Jugador getJugador() {
        return jugador;
    }
}

