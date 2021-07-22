package edu.fiuba.algo3.modelo;

import java.util.HashMap;

public class Turno {

    public Turno() {

    }

    public void rondaAtacar(Jugador atacante, Jugador defensor) {
        boolean fin = false;
        while(!fin) {
            //paises hardcodeados por el momento
            Pais paisAtacante = solicitarPais(atacante, "Argentina");
            Pais paisDefensor = solicitarPais(defensor, "Brasil");

            atacante.atacarPaisDesde(paisAtacante, paisDefensor);
            //todo: consultar al usuario, hardcodeado por el momento
            fin = true;
        }
    }
    // llamar a este metodo primero
    public void actualizarEjercitosDisponibles(Jugador jugador, HashMap<String, Continente> continentes) {
        int cantidadEjercitosGeneral = jugador.obtenerCantidadPaises() / 2;
        jugador.agregarEjercitosGenerales(cantidadEjercitosGeneral);

        for (Continente continente : continentes.values()) {
            jugador.agregarEjercitosPorContinente(continente, continente.obtenerEjercitosExtra(jugador));
        }
    }

    public void rondaReagrupar(Jugador jugador) {
        boolean fin = false;
        while (!fin) {
            Pais paisOrigen = solicitarPais(jugador, "Argentina");
            Pais paisDestino = solicitarPais(jugador, "Brasil");
            //todo: solicitar cantidad al usuario
            int cantidad = 2;
            jugador.transferirEjercitosDesde(paisOrigen, paisDestino, cantidad);
            //todo: consultar al usuario, hardcodeade por le momento
            fin = true;
        }

    }

    public void agregarEjercitosGenerales(Jugador jugador) {
        while (jugador.tieneEjercitosGenerales()) {
            //todo: obtener input del usuario
            Pais pais = solicitarPais(jugador, "Argentina");
            int cantidad = 2;
            jugador.colocarEjercitos(pais, cantidad);
            jugador.quitarEjercitosGenerales(cantidad); //viola tell dont ask pero como diferencio entre ejercitosGenerales y Continentales?
        }
    }

    public void agregarEjercitosPorContinente(Jugador jugador, HashMap<String, Continente> continentes) {
        for (Continente continente : continentes.values()) {
            while (jugador.tieneEjercitosEnContinente(continente)) {
                //todo: obtener input del usuario
                Pais pais = solicitarPais(jugador, "Argentina");
                int cantidad = 2;
                jugador.colocarEjercitos(pais, cantidad);
                jugador.quitarEjercitosPorContinente(continente, cantidad); //viola tell dont ask pero como diferencio entre ejercitosGenerales y Continentales?
            }
        }
    }

    private Pais solicitarPais(Jugador jugador, String nombrePais) {
        //TODO: integrar con ui mas tarde
        return jugador.buscarPais(nombrePais);
    }
}
