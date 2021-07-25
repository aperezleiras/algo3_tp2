package edu.fiuba.algo3.modelo;

import java.util.List;

public class GestorCanjes {

    private int cantidadCanjes;

    public GestorCanjes() {
        cantidadCanjes = 0;
    }

    public void canjearCartas(Jugador jugador, List<CartaPais> cartas, MazoCartasPais mazo) {
        Canje canje = new Canje(cartas, jugador);
        canje.efectuarCanje(mazo);
        jugador.agregarEjercitosGenerales(obtenerEjercitosPorCanje());
        cantidadCanjes++;
    }

    private int obtenerEjercitosPorCanje() {
        int cantidadEjercitos;
        switch (cantidadCanjes) {
            case 0:
                cantidadEjercitos = 3;
                break;
            case 1:
                cantidadEjercitos = 7;
                break;
            case 2:
                cantidadEjercitos = 10;
                break;
            default:
                cantidadEjercitos = (cantidadCanjes) * 5;
        }
        return cantidadEjercitos;
    }
}
