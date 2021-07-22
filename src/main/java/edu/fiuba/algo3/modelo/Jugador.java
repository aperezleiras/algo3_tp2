package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.exception.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class Jugador {

    int color;
    List<Pais> paises; //todo cambiar a atributos privados
    List<CartaPais> cartas;
    private int cantidadCanjes;
    private int ejercitosGeneralesDisponibles;
    private final HashMap<Continente, Integer> ejercitosPorContinenteDisponibles;

    Jugador(int unColor) {
        color = unColor;
        paises = new ArrayList<>();
        cartas = new ArrayList<>();
        cantidadCanjes = 0;
        ejercitosGeneralesDisponibles = 0;
        ejercitosPorContinenteDisponibles = new HashMap<>();
    }

    //<editor-fold desc="Pais">
    public void asignarPais(Pais unPais) {
        unPais.asignarJugador(this);
        paises.add(unPais);
    }

    public boolean paisMePertenece(Pais unPais) {
        return (paises.contains(unPais));
    }

    public int obtenerCantidadPaises() {
        return paises.size();
    }

    //todo: metodo provisorio
    public Pais buscarPais(String nombrePais) {
        for (Pais pais : paises) {
            if (pais.getNombre().equals(nombrePais))
                return pais;
        }
        return null;
    }
    //</editor-fold>

    //<editor-fold desc="Ejercitos">
    public void agregarEjercitosGenerales(int cantidad) {
        ejercitosGeneralesDisponibles += cantidad;
    }

    public void quitarEjercitosGenerales(int cantidad) {
        ejercitosGeneralesDisponibles -= cantidad;
    }

    public void agregarEjercitosPorContinente(Continente continente, int cantidad) {
        this.ejercitosPorContinenteDisponibles.put(continente, cantidad);
    }

    public void quitarEjercitosPorContinente(Continente continente, int cantidad) {
        int cantidadActualizada = ejercitosPorContinenteDisponibles.get(continente) - cantidad;
        if (cantidadActualizada < 0) {
            throw new CantidadAQuitarInvalidaException();
        }
        ejercitosPorContinenteDisponibles.put(continente, cantidadActualizada);
    }

    public boolean tieneEjercitosGenerales() {
        return ejercitosGeneralesDisponibles == 0;
    }

    public boolean tieneEjercitosEnContinente(Continente continente) {
        return ejercitosPorContinenteDisponibles.get(continente) == 0;
    }

    public void validarCantidad(int cantidad) {
        if (ejercitosGeneralesDisponibles < cantidad) {
            throw new CantidadEjercitosInsuficienteException();
        }
    }

    public void validarCantidad(Continente continente, int cantidad) {
        if (ejercitosPorContinenteDisponibles.get(continente) < cantidad) {
            throw new CantidadEjercitosInsuficienteException();
        }
    }

    public void colocarEjercitos(Pais unPais, int cantidad) {
        if (!paisMePertenece(unPais)) throw new PaisNoMePerteneceException();
        unPais.agregarEjercitos(cantidad);
    }

    public void transferirEjercitosDesde(Pais paisOrigen, Pais paisDestino, int cantidad) {
        if (!paisMePertenece(paisOrigen) || !paisMePertenece(paisDestino)) throw new PaisInvalidoException();
        if (!paisOrigen.esLimitrofeCon(paisDestino)) throw new PaisNoLimitrofeException();

        paisOrigen.transferirEjercitos(paisDestino, cantidad);
    }

    public void atacarPaisDesde(Pais miPais, Pais paisEnemigo) {
        if (!paisMePertenece(miPais) || paisMePertenece(paisEnemigo)) throw new PaisInvalidoException();

        Batalla batalla = new Batalla(miPais, paisEnemigo, new Dado());
        batalla.realizarAtaque();
    }
    //</editor-fold>

    //<editor-fold desc="Cartas">
    public void levantarCartaPais(MazoCartasPais mazo) {
        cartas.add(mazo.levantarCarta());
    }

    public boolean cartaMePertenece(CartaPais unaCarta) {
        return (cartas.contains(unaCarta));
    }

    public void devolverCartaA(CartaPais carta, MazoCartasPais mazo) {
        cartas.remove(carta);
        carta.devolverA(mazo);
    }

    public void activarCarta(CartaPais carta) {
        carta.serActivadaPor(this);
    }

    public void canjearCartas(List<CartaPais> cartas, MazoCartasPais mazo) {
        Canje canje = new Canje(cartas, this);
        canje.efectuarCanje(mazo);
        cantidadCanjes++;
    }

    public void obtenerEjercitosPorCanje() {
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
                cantidadEjercitos = (cantidadCanjes - 1) * 5;
        }
        agregarEjercitosGenerales(cantidadEjercitos);
    }
    //</editor-fold>

}

