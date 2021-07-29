package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.exception.CartaNoMePerteneceException;
import edu.fiuba.algo3.exception.CartaYaActivadaException;
import edu.fiuba.algo3.exception.PaisNoMePerteneceException;

//todo: incorporar cambios de ejercitos
public class CartaPais {
    private boolean activada;
    public Pais pais;
    public Simbolo simbolo;

    public CartaPais(Pais pais, Simbolo simbolo) {
        this.pais = pais;
        this.simbolo = simbolo;
        activada = false;
    }

    public Simbolo getSimbolo() {
        return simbolo;
    }

    public Pais getPais() {return pais;}

    public void serActivadaPor(Jugador jugador) {
        if (!perteneceA(jugador)) throw new CartaNoMePerteneceException();
        if (activada) throw new CartaYaActivadaException();
        if (!jugador.paisMePertenece(pais)) throw new PaisNoMePerteneceException();
        jugador.agregarEjercitosGenerales(2);
        jugador.colocarEjercitos(pais, 2);
        activada = true;
    }

    public boolean fueActivada() {
        return activada;
    }

    public void restablecer() {
        activada = false;
    }

    public void devolverA(MazoCartasPais mazo) {
        restablecer();
        mazo.agregarCarta(this);
    }

    public boolean perteneceA(Jugador jugador) {
        return jugador.cartaMePertenece(this);
    }

    public boolean esCanjeableCon(CartaPais carta1, CartaPais carta2) {
        Simbolo simbolo1 = carta1.getSimbolo();
        Simbolo simbolo2 = carta2.getSimbolo();
        if (simbolo == Simbolo.COMODIN || simbolo1 == Simbolo.COMODIN || simbolo2 == Simbolo.COMODIN)
            return true;
        if (simbolo == simbolo1)
            return (simbolo == simbolo2);
        return (simbolo != simbolo2 && simbolo1 != simbolo2);
    }
}
