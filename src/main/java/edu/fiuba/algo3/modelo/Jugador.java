package edu.fiuba.algo3.modelo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import edu.fiuba.algo3.exception.PaisInvalidoException;
import edu.fiuba.algo3.exception.PaisNoLimitrofeException;
import edu.fiuba.algo3.exception.PaisNoMePerteneceException;


public class Jugador {

    int color;
    List<Pais> paises = new ArrayList();

    List<CartaPais> cartas = new ArrayList();
    private int cantidadCanjes = 0;
    private DepositoEjercitos deposito = new DepositoEjercitos();
    private int ejercitosDisponibles = 0;

    Jugador(int unColor) {
        color = unColor;
    }

    //<editor-fold desc="Pais">
    public void asignarPais(Pais unPais) {
        unPais.asignarJugador(this);
        paises.add(unPais);
    }

    public boolean paisMePertenece(Pais unPais) {
        return (paises.contains(unPais));
    }

    public int obtenerCantidadPaises() {
        return paises.size();
    }
    //</editor-fold>

    //<editor-fold desc="Ejercitos">
    public void agregarEjercitosDisponibles(int cantidad) {
    deposito.agregarEjercitosDisponibles(cantidad);
}

    public void agregarEjercitosDisponibles(HashMap<Continente, Integer> ejercitosPorContinente) {
        deposito.agregarEjercitosDisponibles(ejercitosPorContinente);
    }

    public int obtenerCantidadEjercitosDisponibles() {
        return deposito.getEjercitosGenerales();
    }

    public int obtenerCantidadEjercitosDisponibleEnContinente(Continente continente) {
        return deposito.getEjercitosContinente(continente);
    }

    public void colocarEjercitos(Pais unPais, int cantidad) {
        if (!paisMePertenece(unPais)) throw new PaisNoMePerteneceException();
        deposito.agregarEjercitosAPais(unPais, cantidad);
    }

    public void transferirEjercitosDesde(Pais paisOrigen, Pais paisDestino, int cantidad) {
        if (!paisMePertenece(paisOrigen) || !paisMePertenece(paisDestino)) throw new PaisInvalidoException();
        if (!paisOrigen.esLimitrofeCon(paisDestino)) throw new PaisNoLimitrofeException();

        paisOrigen.transferirEjercitos(paisDestino, cantidad);
    }

    public void atacarPaisDesde(Pais miPais, Pais paisEnemigo) {
        if (!paisMePertenece(miPais) || paisMePertenece(paisEnemigo)) throw new PaisInvalidoException();

        Batalla batalla = new Batalla(miPais,paisEnemigo, new Dado());
        batalla.realizarAtaque();
    }
    //</editor-fold>

    //<editor-fold desc="Cartas">
    public void levantarCartaPais(MazoCartasPais mazo) {
        cartas.add(mazo.levantarCarta());
    }

    public boolean cartaMePertenece(CartaPais unaCarta) {
        return (cartas.contains(unaCarta));
    }

    public void devolverCartaA(CartaPais carta, MazoCartasPais mazo) {
        cartas.remove(carta);
        carta.devolverA(mazo);
    }

    public void activarCarta(CartaPais carta) {
        carta.serActivadaPor(this);
    }

    public void canjearCartas(CartaPais carta1, CartaPais carta2, CartaPais carta3, MazoCartasPais mazo) {
        Canje canje = new Canje(carta1, carta2, carta3, this);
        canje.efectuarCanje(mazo);
        cantidadCanjes ++;
    }

    public void obtenerEjercitosPorCanje() {
        int cantidadEjercitos;
        switch (cantidadCanjes) {
            case 0: cantidadEjercitos = 3; break;
            case 1: cantidadEjercitos = 7; break;
            case 2: cantidadEjercitos = 10; break;
            default: cantidadEjercitos = (cantidadCanjes - 1) * 5;
        }
        agregarEjercitosDisponibles(cantidadEjercitos);
    }
    //</editor-fold>

}

