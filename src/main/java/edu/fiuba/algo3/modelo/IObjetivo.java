package edu.fiuba.algo3.modelo;

public interface IObjetivo {

    boolean cumplido(Jugador jugador);

    boolean esValidoPara(Jugador jugador);

    String getTexto();
}
