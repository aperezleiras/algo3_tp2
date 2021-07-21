package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.exception.CantidadEjercitosInsuficienteException;

import java.util.HashMap;

public class DepositoEjercitos {
    private int ejercitosGenerales = 0;
    HashMap<Continente, Integer> ejercitosPorContinente = new HashMap<>();

    public DepositoEjercitos() {}

    public int getEjercitosGenerales() {
        return ejercitosGenerales;
    }

    public int getEjercitosContinente(Continente continente) {
        return ejercitosPorContinente.get(continente);
    }

    public void agregarEjercitosDisponibles(int ejercitosDisponibles) {
        this.ejercitosGenerales += ejercitosDisponibles;
    }

    // suponiendo que un jugador siempre va a colocar todos los ejercitos que tenga disponibles, esto funciona
    // si no, habria que sumar las cantidades de ambos mapas para cada continente
    public void agregarEjercitosDisponibles(HashMap<Continente, Integer> ejercitosPorContinente) {
        this.ejercitosPorContinente = ejercitosPorContinente;
    }

    public void agregarEjercitosAPais(Pais unPais, int cantidad) {
        extraerEjercitosPara(unPais, cantidad);
        unPais.agregarEjercitos(cantidad);
    }

    private void extraerEjercitosPara(Pais unPais, int cantidad) {
        Continente continenteDelPais = null;
        for (Continente continente : ejercitosPorContinente.keySet())
            if (continente.tienePais(unPais))
                continenteDelPais = continente;

        int ejercitosContinentalesDisponibles = 0;
        try {
            ejercitosContinentalesDisponibles = ejercitosPorContinente.get(continenteDelPais);
        } catch (Exception ignorada) {}

        // Caso 1: Hay suficientes ejercitos continentales como para usar solo estos
        if (ejercitosContinentalesDisponibles >= cantidad) {
            ejercitosPorContinente.put(continenteDelPais, ejercitosPorContinente.get(continenteDelPais) - cantidad);
        }
        // Caso 2: Se puede completar la cantidad faltante con ejercitos generales
        else if (ejercitosContinentalesDisponibles + ejercitosGenerales >= cantidad) {
            ejercitosPorContinente.put(continenteDelPais, 0);
            ejercitosGenerales -= (cantidad - ejercitosContinentalesDisponibles);
        }
        // Caso 3: No hay ejercitos suficientes
        else
            throw new CantidadEjercitosInsuficienteException();
    }
}
