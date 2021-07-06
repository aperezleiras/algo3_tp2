package edu.fiuba.algo3.modelo;

import java.util.ArrayList;

public class Jugador {

    int color;
    ArrayList paises = new ArrayList();

    Jugador(int unColor) {
        color = unColor;
    }

    public void colocarEjercitos(int cantidad, Pais unPais) {
        if (paisMePertenece(unPais)) unPais.colocarEjercitos(cantidad);
    }

    public boolean paisMePertenece(Pais unPais) {
        return (paises.contains(unPais));
    }
    /*
    public void atacarPaisDesde(Pais paisOrigen, Pais paisDestino, int cantidad) {
        paisOrigen.atacarA(paisDestino, cantidad);
    }
    */
    public void transferirEjercitosDesde(Pais paisOrigen, Pais paisDestino, int cantidad) {
        if (paisMePertenece(paisDestino)) paisOrigen.transferirEjercitosA(paisDestino, cantidad);
    }

    public void asignarPais(Pais unPais) {
        unPais.asignarJugador(this);
        paises.add(unPais);
    }
}

