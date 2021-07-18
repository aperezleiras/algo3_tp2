package edu.fiuba.algo3.modelo;

import java.util.List;

public class Juego {
    private final List<Jugador> jugadores;

    public Juego(List<Jugador> jugadores) {
        this.jugadores = jugadores;
    }

    public void asignarPaises(List<Pais> paises) {
        jugadores.forEach(j -> paises.forEach(j::asignarPais));
    }

    public void asignarEjercitos(int jugadorId, Pais pais) {
        Jugador jugador = jugadores.get(jugadorId);

        int cantidadEjercitos = obtenerCantidadEjercitos(jugadorId); //Aca podriamos acumular las otras cantidades calculadas

        jugador.colocarEjercitos(cantidadEjercitos, pais);
    }

    public int obtenerCantidadEjercitos(int jugadorId) {

        Jugador jugador = jugadores.get(jugadorId);

        return jugador.obtenerCantidadPaises() / 2;
    }


    /* TODO revisear implementacion con o sin sobrecarga
        public int obtenerCantidadEjercitos(Jugador jugador){
            return  jugador.obtenerCantidadPaises() / 2;
        }
    */

    /* TODO pensarlo mejor la implementacion
    public void colocarEjercitosContinente(Jugador jugador, Continente continente){
        int cantidad = obtenerCantidadEjercitos(jugador, continente);

        Pais pais;

        while (cantidad > 0){
            pais = elergirPais(jugador, continente);
            pais.agregarEjercitos(1);
            cantidad--;
        }
    }*/

    public int obtenerCantidadEjercitos(Jugador jugador, Continente continente){
        return  continente.getEjercitosExtra(jugador);
    }

    //TODO pensar implementacion
    public Pais elergirPais(Jugador jugador, Continente continente){
        return null;
    }

}