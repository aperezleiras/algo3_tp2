package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.exception.*;

import java.util.ArrayList;

public class Canje {
    private CartaPais carta1;
    private CartaPais carta2;
    private CartaPais carta3;
    //private ArrayList<CartaPais> cartas;      // Ver si conviene tener las 3 cartas individuales o un arraylist
    private Jugador jugador;

    public Canje(CartaPais carta1, CartaPais carta2, CartaPais carta3, Jugador jugador) {
        this.carta1 = carta1;
        this.carta2 = carta2;
        this.carta3 = carta3;
        this.jugador = jugador;
    }

    public void efectuarCanje(MazoCartasPais mazo) {
        if (!(carta1.perteneceA(jugador) && carta2.perteneceA(jugador) && carta3.perteneceA(jugador)))
            throw new CartaNoMePerteneceException();
        if (!cartasSonCanjeables()) throw new CartasNoCanjeablesException();
        carta1.devolverA(mazo);
        carta2.devolverA(mazo);
        carta3.devolverA(mazo);
        jugador.obtenerEjercitosPorCanje();
    }

    private boolean cartasSonCanjeables() {
        return carta1.esCanjeableCon(carta2, carta3);
    }
}
