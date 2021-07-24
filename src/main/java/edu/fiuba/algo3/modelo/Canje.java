package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.exception.*;

import java.util.*;

public class Canje {
    private List<CartaPais> cartas;
    private Jugador jugador;

    public Canje(List<CartaPais> cartas, Jugador jugador) {
        this.cartas = cartas;
        this.jugador = jugador;
    }

    public void efectuarCanje(MazoCartasPais mazo) {
        if (!cartas.stream().allMatch(jugador::cartaMePertenece))
            throw new CartaNoMePerteneceException();
        if (!cartasSonCanjeables()) throw new CartasNoCanjeablesException();
        cartas.forEach(c -> jugador.devolverCartaA(c, mazo));
        jugador.obtenerEjercitosPorCanje();
    }

    //private boolean cartasSonCanjeables() {
    //    return carta1.esCanjeableCon(carta2, carta3);
    //}

    public boolean cartasSonCanjeables() {
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
