package edu.fiuba.algo3.modelo;

public class ObjetivoDerrotarJugador implements IObjetivo {

    private Jugador jugador;

    public ObjetivoDerrotarJugador(Jugador jugadorADerrotar) {
        jugador = jugadorADerrotar;
    }

    public boolean cumplido(Jugador jugador) {
        return jugador.derrotoA(this.jugador);
    }

    public boolean esValidoPara(Jugador jugador) {
        return (jugador != this.jugador);
    }
}
