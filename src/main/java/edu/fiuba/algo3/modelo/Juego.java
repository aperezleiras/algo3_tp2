package edu.fiuba.algo3.modelo;

import java.util.List;

public class Juego {
    private final List<Jugador> jugadores;

    public Juego(List<Jugador> jugadores) {
        this.jugadores = jugadores;
    }

    void asignarPaises(List<Pais> paises) {
        jugadores.forEach(j -> paises.forEach(j::asignarPais));
    }

    void asignarEjercitos(int jugadorId, Pais pais) {
        Jugador jugador = jugadores.get(jugadorId);
        int cantidadEjercitos = obtenerCantidadEjercitos(jugadorId); //Aca podriamos acumular las otras cantidades calculadas
        jugador.colocarEjercitos(cantidadEjercitos, pais);
    }

    int obtenerCantidadEjercitos(int jugadorId) {
        Jugador jugador = jugadores.get(jugadorId);
        int cantidadEjercitos = jugador.obtenerCantidadPaises();
        return cantidadEjercitos/2;
    }
}