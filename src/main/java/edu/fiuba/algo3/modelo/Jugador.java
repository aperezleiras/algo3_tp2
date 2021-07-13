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
        if (!paisMePertenece(unPais)) throw new PaisInvalidoError();

        unPais.agregarEjercitos(cantidad);
    }

    public void transferirEjercitosDesde(Pais paisOrigen, Pais paisDestino, int cantidad) {
        if (!paisMePertenece(paisOrigen) || !paisMePertenece(paisDestino)) throw new PaisInvalidoError();
        if (!paisOrigen.esLimitrofeCon(paisDestino)) throw new PaisesNoConectadosError();

        paisOrigen.transferirEjercitos(paisDestino, cantidad);
    }

    public void atacarPaisDesde(Pais miPais, Pais paisEnemigo) {
        if (!paisMePertenece(miPais) || paisMePertenece(paisEnemigo)) throw new PaisInvalidoError();
        if (!miPais.esLimitrofeCon(paisEnemigo)) throw new PaisesNoConectadosError(); // tal vez mover chequeo a batalla
        if (!miPais.cantidadEjercitosSuperiorA(1)) throw new InsuficientesEjercitosError(); // tal vez mover chequeo a batalla

        Batalla batalla = new Batalla(miPais, paisEnemigo);
        batalla.resolver();
    }
}

