package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.exception.*;

import java.util.*;

public class Canje {
    private CartaPais carta1;
    private CartaPais carta2;
    private CartaPais carta3;
    //private ArrayList<CartaPais> cartas;      // Ver si conviene tener las 3 cartas individuales o un arraylist
    private List<CartaPais> cartas;
    private Jugador jugador;

    public Canje(CartaPais carta1, CartaPais carta2, CartaPais carta3, Jugador jugador) {
        this.carta1 = carta1;
        this.carta2 = carta2;
        this.carta3 = carta3;
        this.jugador = jugador;
    }

    public Canje(List<CartaPais> cartas, Jugador jugador) {
        this.cartas = cartas;
        this.jugador = jugador;
    }

    public void efectuarCanje(MazoCartasPais mazo) {
        //if (!(carta1.perteneceA(jugador) && carta2.perteneceA(jugador) && carta3.perteneceA(jugador)))
        //    throw new CartaNoMePerteneceException();
        if (!cartasSonCanjeables()) throw new CartasNoCanjeablesException();
        cartas.forEach(c -> jugador.devolverCartaA(c, mazo));
        jugador.obtenerEjercitosPorCanje();
    }

    //private boolean cartasSonCanjeables() {
    //    return carta1.esCanjeableCon(carta2, carta3);
    //}

    public boolean cartasSonCanjeables(){
        return todasDistintas() || todasIguales();
    }
    private boolean todasIguales(){
        boolean iguales = true;
        Simbolo actual;
        Simbolo anterior = cartas.get(0).getSimbolo();

        for (CartaPais carta: cartas){
            actual = carta.getSimbolo();

            if (actual != Simbolo.COMODIN){
                iguales = iguales && ( actual == anterior || anterior == Simbolo.COMODIN);
                anterior = actual;
            }
        }
        return iguales;
    }

    private boolean todasDistintas(){
        Set<Simbolo> set = new HashSet<>();
        return cartas.stream().allMatch(c -> set.add(c.getSimbolo()) || c.getSimbolo() == Simbolo.COMODIN);
    }

}
