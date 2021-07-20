package edu.fiuba.algo3.modelo;

import java.util.ArrayList;
import java.util.List;

import edu.fiuba.algo3.exception.CantidadEjercitosInsuficienteException;
import edu.fiuba.algo3.exception.PaisInvalidoException;
import edu.fiuba.algo3.exception.PaisNoLimitrofeException;


public class Jugador {

    int color;
    List<Pais> paises = new ArrayList();

    List<CartaPais> cartas = new ArrayList();
    private int cantidadCanjes = 0;
    private int ejercitosDisponibles = 0;

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

    public void devolverCartaA(CartaPais carta, MazoCartasPais mazo) {
        cartas.remove(carta);
        carta.devolverA(mazo);
    }

    public void canjearCartas(CartaPais carta1, CartaPais carta2, CartaPais carta3, MazoCartasPais mazo) {
        Canje canje = new Canje(carta1, carta2, carta3, this);
        canje.efectuarCanje(mazo);
        cantidadCanjes ++;
    }

    public void activarCarta(CartaPais carta) {
        carta.serActivadaPor(this);
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

    public int obtenerCantidadPaises() {
        return paises.size();
    }

    public int obtenerCantidadEjercitosDisponibles() { return ejercitosDisponibles; }

    public boolean paisMePertenece(Pais unPais) {
        return (paises.contains(unPais));
    }

    public boolean cartaMePertenece(CartaPais unaCarta) {return (cartas.contains(unaCarta));}

    public void agregarEjercitosDisponibles(int cantidad) {
        ejercitosDisponibles += cantidad;
    }

    public void colocarEjercitos(int cantidad, Pais unPais) {
        if (!paisMePertenece(unPais)) throw new PaisInvalidoException();
        if (cantidad > ejercitosDisponibles) throw new CantidadEjercitosInsuficienteException();

        unPais.agregarEjercitos(cantidad);
        ejercitosDisponibles -= cantidad;
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

