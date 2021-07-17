package edu.fiuba.algo3.modelo;

import java.util.ArrayList;
import java.util.List;

import edu.fiuba.algo3.exception.CantidadEjercitosInsuficienteException;
import edu.fiuba.algo3.exception.PaisInvalidoException;
import edu.fiuba.algo3.exception.PaisNoLimitrofeException;

public class Jugador {

    int color;

    List<Pais> paises = new ArrayList<>();
    ArrayList cartas = new ArrayList();
    private int cantidadCanjes = 0;

    Jugador(int unColor) {
        color = unColor;
    }

    public void asignarPais(Pais unPais) {
        unPais.asignarJugador(this);
        paises.add(unPais);
    }

    public void levantarCartaPais(MazoCartasPais mazo) {
        cartas.add(mazo.levantarCarta());
    }

    public void canjearCartas(CartaPais carta1, CartaPais carta2, CartaPais carta3, MazoCartasPais mazo) {
        Canje canje = new Canje(carta1, carta2, carta3, this);
        canje.efectuarCanje(mazo);
    }

    public void activarCarta(CartaPais carta) {
        carta.activar(this);
    }

    public void obtenerEjercitosPorCanje() {
        int cantidadEjercitos;
        switch (cantidadCanjes) {
            case 0: cantidadEjercitos = 3;
            case 1: cantidadEjercitos = 7;
            case 2: cantidadEjercitos = 10;
            default: cantidadEjercitos = cantidadCanjes * 5;
        }
        // agregarEjercitosDisponibles(cantidadEjercitos) ??
    }

    public int obtenerCantidadPaises() {
        return paises.size();
    }

    public boolean paisMePertenece(Pais unPais) {
        return (paises.contains(unPais));
    }

    public boolean cartaMePertenece(CartaPais unaCarta) {return (cartas.contains(unaCarta));}

    public void colocarEjercitos(int cantidad, Pais unPais) {
        if (!paisMePertenece(unPais)) throw new PaisInvalidoException();

        unPais.agregarEjercitos(cantidad);
    }

    public void transferirEjercitosDesde(Pais paisOrigen, Pais paisDestino, int cantidad) {
        if (!paisMePertenece(paisOrigen) || !paisMePertenece(paisDestino)) throw new PaisInvalidoException();
        if (!paisOrigen.esLimitrofeCon(paisDestino)) throw new PaisNoLimitrofeException();

        paisOrigen.transferirEjercitos(paisDestino, cantidad);
    }

    public void atacarPaisDesde(Pais miPais, Pais paisEnemigo) {
        if (!paisMePertenece(miPais) || paisMePertenece(paisEnemigo)) throw new PaisInvalidoException();

        Batalla batalla = new Batalla(miPais,paisEnemigo,new Dado(), new Dado());
        batalla.realizarAtaque();
    }
}

