package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.vista.ObservadorTurno;

import java.util.ArrayList;
import java.util.List;

public class Turno implements IObserbable {

    private final List<Jugador> jugadores;
    private Jugador jugadorActual;
    private Batalla ultimaBatalla;
    private Fase fase;
    private final ArrayList<ObservadorTurno> observadores = new ArrayList<>();
    private boolean primerosCincoColocados = false;
    private boolean inicial = true; //AUXILIAR, representa la fase de colocacion inicial

    public Turno(List<Jugador> jugadores) {
        this.jugadores = jugadores;
        jugadorActual = jugadores.get(0);
        jugadores.forEach(jugador -> jugador.agregarEjercitosGenerales(5));
        fase = Fase.COLOCAR;
    }

    private void siguienteJugador() {
        int indice = jugadores.indexOf(jugadorActual);
        int indiceSiguiente = (indice+1 == jugadores.size()) ? 0 : indice+1;
        jugadorActual = jugadores.get(indiceSiguiente);
        if (fase == fase.COLOCAR || fase == fase.CANJEAR)
            jugadorActual.actualizarObservadores();
    }

    public void rondaAtacar(Pais paisAtacante, Pais paisDefensor) {
        ultimaBatalla = jugadorActual.atacarPaisDesde(paisAtacante, paisDefensor);
        if (jugadorActual.haGanado());

        actualizarObservadores();
    }

    public void finalizarAtaque(MazoCartasPais mazo) {
        if (jugadorActual.estaHabilitadoLevantarCarta())
            jugadorActual.levantarCartaPais(mazo);
        fase = Fase.REAGRUPAR;
        actualizarObservadores();
    }

    public void rondaReagrupar(Pais paisOrigen, Pais paisDestino, int cantidad) {
            jugadorActual.transferirEjercitosDesde(paisOrigen, paisDestino, cantidad);
    }

    public void finalizarReagrupe() {
        // Si es el ultimo jugador
        if (jugadores.indexOf(jugadorActual) == jugadores.size()-1) {
            fase = Fase.CANJEAR;
        } else {
            fase = Fase.ATACAR;
        }
        jugadorActual.actualizarEjercitosDisponibles();
        siguienteJugador();
        actualizarObservadores();
    }

    public void finalizarCanjes(){
        fase = fase.COLOCAR;
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
            if(!primerosCincoColocados) {
                jugadores.forEach(jugador -> jugador.agregarEjercitosGenerales(3));
                primerosCincoColocados = true;
            } else {
                inicial = false;
                fase = Fase.ATACAR;
            }
        } else if(!inicial) { fase = fase.CANJEAR;}
        siguienteJugador();
        actualizarObservadores();
    }

    public Fase obtenerFase() {
        return fase;
    }

    public Jugador obtenerJugadorActual() {
        return jugadorActual;
    }

    public Batalla obtenerUltimaBatalla() {
        return ultimaBatalla;
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
