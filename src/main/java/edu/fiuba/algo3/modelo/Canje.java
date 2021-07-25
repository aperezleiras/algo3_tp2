package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.exception.*;

import java.util.*;

import static java.util.stream.Collectors.toList;

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
        if (!cartasSonCanjeables())
            throw new CartasNoCanjeablesException();
        cartas.forEach(c -> jugador.devolverCartaA(c, mazo));
    }

    public boolean cartasSonCanjeables() {
        List<Simbolo> simbolos = cartas.stream().map(CartaPais::getSimbolo).collect(toList());
        return todosDistintos(simbolos) || todosIguales(simbolos) || hayComodin(simbolos);
    }

    private boolean hayComodin(List<Simbolo> simbolos) {
        return simbolos.stream().anyMatch(s -> s == Simbolo.COMODIN);
    }

    private boolean todosIguales(List<Simbolo> simbolos){
        return simbolos.stream().distinct().count() == 1;
    }

    private boolean todosDistintos(List<Simbolo> simbolos) {
        return simbolos.stream().distinct().count() == cartas.size();
    }

}
