package edu.fiuba.algo3.modelo;

import java.util.ArrayList;
import java.util.HashMap;

public class Turno {

    public Turno() {

    }
/*
    public void rondaAtacar(Jugador atacante, Jugador defensor, MazoCartasPais mazo) {
        atacante.habilitadoLevantarCarta = false;
        boolean fin = false;

        while(!fin) {
            //paises hardcodeados por el momento
            Pais paisAtacante = solicitarPais(atacante, "Argentina");
            Pais paisDefensor = solicitarPais(defensor, "Brasil");

            atacante.atacarPaisDesde(paisAtacante, paisDefensor);
            //todo: consultar al usuario, hardcodeado por el momento
            fin = true;
        }

        Pais paisAtacante = solicitarPais(atacante, "Argentina");
        Pais paisDefensor = solicitarPais(defensor, "Brasil");

        atacante.atacarPaisDesde(paisAtacante, paisDefensor);

        paisDefensor = solicitarPais(defensor, "Chile");

        atacante.atacarPaisDesde(paisAtacante, paisDefensor);

        if (atacante.habilitadoLevantarCarta) {
            atacante.levantarCartaPais(mazo);
        }

    }

    public void realizarCanje(Jugador jugador, MazoCartasPais mazo) {
        //todo: obtener seleccion de cartas del usuario
        jugador.canjearCartas(new ArrayList<>(), mazo);
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

    public void actualizarEjercitosDisponibles(Jugador jugador) {
        jugador.actualizarEjercitosDisponibles();
    }

    public void agregarEjercitosSegunCartas(Jugador jugador) {
        for (CartaPais carta : jugador.cartas) {
            //todo: obtener input del usuario: Desea activar la carta?
            carta.serActivadaPor(jugador);
        }
    }

    //todo: no diferenciar entre colocacion de ejercitos generales y continentales, esa logica se hace en Deposito
    public void colocarEjercitosGenerales(Jugador jugador) {
        while (jugador.tieneEjercitosGenerales()) {
            //todo: obtener input del usuario
            Pais pais = solicitarPais(jugador, "Argentina");
            int cantidad = 2;
            jugador.validarCantidad(cantidad);
            jugador.colocarEjercitos(pais, cantidad);
            jugador.quitarEjercitosGenerales(cantidad); //viola tell dont ask pero como diferencio entre ejercitosGenerales y Continentales?
        }
    }

    //todo: no diferenciar entre colocacion de ejercitos generales y continentales, esa logica se hace en Deposito
    public void colocarEjercitosPorContinente(Jugador jugador, HashMap<String, Continente> continentes) {
        for (Continente continente : continentes.values()) {
            while (jugador.tieneEjercitosEnContinente(continente)) {
                //todo: obtener input del usuario
                Pais pais = solicitarPais(jugador, "Argentina");
                int cantidad = 2;
                jugador.validarCantidad(continente, cantidad);
                jugador.colocarEjercitos(pais, cantidad);
                jugador.quitarEjercitosPorContinente(continente, cantidad); //viola tell dont ask pero como diferencio entre ejercitosGenerales y Continentales?
            }
        }
    }

    private Pais solicitarPais(Jugador jugador, String nombrePais) {
        //TODO: integrar con ui mas tarde
        return jugador.buscarPais(nombrePais);
    }*/
}
