package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.exception.CantidadEjercitosInsuficienteException;

import java.util.ArrayList;
import java.util.HashMap;

public class DepositoEjercitos {

    private int ejercitosGenerales;
    HashMap<Continente, Integer> ejercitosPorContinente;
    ArrayList<Continente> continentes;

    public DepositoEjercitos(ArrayList<Continente> continentes) {
        ejercitosGenerales = 0;
        ejercitosPorContinente = new HashMap<>();
        this.continentes = continentes;
        for (Continente c : continentes)
            ejercitosPorContinente.put(c, 0);
    }

    public void actualizarEjercitosDisponibles(Jugador jugador) {

        // Ejercitos generales
        int cantidadEjercitosGenerales = jugador.obtenerCantidadPaises() / 2;
        agregarEjercitosGenerales(cantidadEjercitosGenerales);

        // Ejercitos por continente
        for (Continente continente : continentes) {
            agregarEjercitosPorContinente(continente, continente.obtenerEjercitosExtra(jugador));
        }
    }

    // Para la UI y tests
    public int obtenerEjercitosGenerales() {
        return ejercitosGenerales;
    }

    // Para la UI y tests
    public int obtenerEjercitosContinente(Continente continente) {
        return ejercitosPorContinente.get(continente);
    }

    public void agregarEjercitosGenerales(int cantidad) {
        ejercitosGenerales += cantidad;
    }

    public void quitarEjercitosGenerales(int cantidad) { ejercitosGenerales -= cantidad; }

    public void agregarEjercitosPorContinente(Continente continente, int cantidad) {
        ejercitosPorContinente.put(continente, ejercitosPorContinente.get(continente) + cantidad);
    }

    public void quitarEjercitosPorContinente(Continente continente, int cantidad) {
        ejercitosPorContinente.put(continente, ejercitosPorContinente.get(continente) - cantidad);
    }

    public void agregarEjercitosAPais(Pais unPais, int cantidad) {
        extraerEjercitosPara(unPais, cantidad);
        unPais.agregarEjercitos(cantidad);
    }

    private void extraerEjercitosPara(Pais unPais, int cantidad) {
        Continente continenteDelPais = null;
        for (Continente continente : continentes)
            if (continente.tienePais(unPais))
                continenteDelPais = continente;

        int ejercitosContinentalesDisponibles = ejercitosPorContinente.get(continenteDelPais);

        // Caso 1: Hay suficientes ejercitos continentales como para usar solo estos
        if (ejercitosContinentalesDisponibles >= cantidad) {
            quitarEjercitosPorContinente(continenteDelPais, cantidad);
        }
        // Caso 2: Se puede completar la cantidad faltante con ejercitos generales
        else if (ejercitosContinentalesDisponibles + ejercitosGenerales >= cantidad) {
            quitarEjercitosPorContinente(continenteDelPais, ejercitosContinentalesDisponibles);
            quitarEjercitosGenerales(cantidad - ejercitosContinentalesDisponibles);
        }
        // Caso 3: No hay ejercitos suficientes
        else
            throw new CantidadEjercitosInsuficienteException();
    }
}
