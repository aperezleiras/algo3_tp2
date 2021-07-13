package edu.fiuba.algo3.modelo;

import java.util.ArrayList;

public class Jugador {

    int color;
    ArrayList paises = new ArrayList();

    Jugador(int unColor) {
        color = unColor;
    }

    public void asignarPais(Pais unPais) {
        unPais.asignarJugador(this);
        paises.add(unPais);
    }

    public int obtenerCantidadPaises() {
        return paises.size();
    }

    public boolean paisMePertenece(Pais unPais) {
        return (paises.contains(unPais));
    }

    public void colocarEjercitos(int cantidad, Pais unPais) {
        if (paisMePertenece(unPais)) unPais.agregarEjercitos(cantidad);
    }

    public void transferirEjercitosDesde(Pais paisOrigen, Pais paisDestino, int cantidad) {
        if (paisMePertenece(paisDestino)) paisOrigen.transferirEjercitos(paisDestino, cantidad);
    }

    public void atacarPaisDesde(Pais miPais, Pais paisEnemigo) {
        Batalla batalla = new Batalla(miPais, paisEnemigo);
        batalla.resolver();
    }
}

