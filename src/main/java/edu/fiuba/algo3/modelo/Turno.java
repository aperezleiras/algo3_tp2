package edu.fiuba.algo3.modelo;

import java.util.List;

public class Turno {

    private final List<Jugador> jugadores;
    private Jugador jugadorActual;
    private Fase fase;

    public Turno(List<Jugador> jugadores) {
        this.jugadores = jugadores;
        jugadorActual = jugadores.get(0);

    }

    private void siguienteJugador() {
        int indice = jugadores.indexOf(jugadorActual);
        int indiceSiguiente = (indice+1 == jugadores.size()) ? 0 : indice+1;
        jugadorActual = jugadores.get(indiceSiguiente);
    }

    public void rondaAtacar(Pais paisAtacante, Pais paisDefensor) {
        jugadorActual.atacarPaisDesde(paisAtacante, paisDefensor);
    }

    public void finalizarAtaque(MazoCartasPais mazo) {
        if (jugadorActual.estaHabilitadoLevantarCarta()) {
            jugadorActual.levantarCartaPais(mazo);
        }
        fase = Fase.REAGRUPAR;
    }

    public void rondaReagrupar(Pais paisOrigen, Pais paisDestino, int cantidad) {
            jugadorActual.transferirEjercitosDesde(paisOrigen, paisDestino, cantidad);
    }

    public void finalizarReagrupe() {
        // Si es el ultimo jugador
        if (jugadores.indexOf(jugadorActual) == jugadores.size()-1) {
            fase = Fase.COLOCAR;
        } else {
            fase = Fase.ATACAR;
        }
        siguienteJugador();
    }

    public void realizarCanje(List<CartaPais> cartas, MazoCartasPais mazo) {
        jugadorActual.canjearCartas(cartas, mazo);
    }

    public void actualizarEjercitosDisponibles() {
        jugadorActual.actualizarEjercitosDisponibles();
    }

    public void agregarEjercitosSegunCarta(CartaPais carta) {
        carta.serActivadaPor(jugadorActual);
    }

    public void colocarEjercito(Pais pais, int cantidad) {
        jugadorActual.colocarEjercitos(pais, cantidad);
    }

    public void finalizarColocacion() {
        // Si es el ultimo jugador
        if (jugadores.indexOf(jugadorActual) == jugadores.size()-1) {
            fase = Fase.ATACAR;
        }
        siguienteJugador();
    }

    public Fase obtenerFase() {
        return fase;
    }

    public Jugador obtenerJugadorActual() {
        return jugadorActual;
    }

    private boolean chequearObjetivo() {
        return true; // todo
    }
}
