package edu.fiuba.algo3.modelo;

import java.util.ArrayList;
import java.util.List;

public class Turno implements IObserbable {

    private final List<Jugador> jugadores;
    private Jugador jugadorActual;
    private Fase fase;
    private final ArrayList<ObservadorTurno> observadores = new ArrayList<>();

    public Turno(List<Jugador> jugadores) {
        this.jugadores = jugadores;
        jugadores.forEach(jugador -> jugador.agregarEjercitosGenerales(5));
        jugadorActual = jugadores.get(0);
        fase = Fase.COLOCAR;
    }

    private void siguienteJugador() {
        int indice = jugadores.indexOf(jugadorActual);
        int indiceSiguiente = (indice+1 == jugadores.size()) ? 0 : indice+1;
        jugadorActual = jugadores.get(indiceSiguiente);
        if(fase == fase.COLOCAR) {jugadorActual.actualizarObservadores();}
    }

    public void rondaAtacar(Pais paisAtacante, Pais paisDefensor) {
        jugadorActual.atacarPaisDesde(paisAtacante, paisDefensor);
    }

    public void finalizarAtaque(MazoCartasPais mazo) {
        if (jugadorActual.estaHabilitadoLevantarCarta()) {
            jugadorActual.levantarCartaPais(mazo);
        }
        fase = Fase.REAGRUPAR;
        actualizarObservadores();
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
        jugadorActual.actualizarEjercitosDisponibles();
        siguienteJugador();
        actualizarObservadores();
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
        actualizarObservadores();
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


    public void asignarObservador(ObservadorTurno observador) {
        observador.asignarTurno(this);
        observadores.add(observador);
    }

    @Override
    public void actualizarObservadores() {
        observadores.forEach(observadorTurno -> observadorTurno.actualizar());
    }
}
