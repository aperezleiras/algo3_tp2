package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.exception.CantidadEjercitosInsuficienteException;
import edu.fiuba.algo3.exception.PaisInvalidoException;
import edu.fiuba.algo3.exception.PaisNoLimitrofeException;
import edu.fiuba.algo3.exception.PaisNoMePerteneceException;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;


public class Jugador {

    int color;
    Color auxColor; // AUXILIAR
    List<Pais> paises; //todo cambiar a atributos privados??
    List<CartaPais> cartas;
    boolean habilitadoLevantarCarta;
    private int cantidadCanjes;
    private int ejercitosGeneralesDisponibles;
    private final HashMap<Continente, Integer> ejercitosPorContinenteDisponibles;

    public Jugador(int unColor) {
        color = unColor;

        Random rand = new Random();
        auxColor = Color.rgb(rand.nextInt(255),rand.nextInt(255),rand.nextInt(255));

        paises = new ArrayList<>();
        cartas = new ArrayList<>();
        habilitadoLevantarCarta = false;
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
        for (Pais pais : paises){
            if (pais.getNombre().equals(nombrePais))
                return pais; }
        return null;
    }
    //</editor-fold>

    //<editor-fold desc="Ejercitos">

    public int obtenerEjercitosGeneralesDisponibles() {
        return ejercitosGeneralesDisponibles;
    }

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
        ejercitosPorContinenteDisponibles.put(continente, cantidadActualizada);
    }

    public boolean tieneEjercitosGenerales() {
        return ejercitosGeneralesDisponibles != 0;
    }

    public boolean tieneEjercitosEnContinente(Continente continente) {
        return ejercitosPorContinenteDisponibles.get(continente) != 0;
    }

    public void validarCantidad(int cantidad) {
        if (ejercitosGeneralesDisponibles < cantidad)
            throw new CantidadEjercitosInsuficienteException();
    }

    public void validarCantidad(Continente continente, int cantidad) {
        if (ejercitosPorContinenteDisponibles.get(continente) < cantidad)
            throw new CantidadEjercitosInsuficienteException();
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

    public void atacarPaisDesde(Pais miPais, Pais paisEnemigo){
        if (!paisMePertenece(miPais) || paisMePertenece(paisEnemigo)) throw new PaisInvalidoException();

        Batalla batalla = new Batalla(miPais, paisEnemigo, new Dado());
        batalla.realizarAtaque(); }

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
                cantidadEjercitos = (cantidadCanjes) * 5;
        }
        agregarEjercitosGenerales(cantidadEjercitos);
    }

    public Paint getColor() { // AUXILIAR
        return auxColor;
    }


    //</editor-fold>

}

