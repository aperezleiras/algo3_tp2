package edu.fiuba.algo3.modelo;

public class Objetivo {

    public boolean cumplido(Jugador jugador) {
        return jugador.obtenerCantidadPaises() >= 30;
    }
}
