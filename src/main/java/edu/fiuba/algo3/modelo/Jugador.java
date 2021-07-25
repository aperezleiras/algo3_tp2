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
    private DepositoEjercitos deposito;
    private GestorCanjes gestorCanjes;
    private List<Objetivo> objetivos;

    Jugador(int unColor, DepositoEjercitos deposito) {
        color = unColor;

        Random rand = new Random();
        auxColor = Color.rgb(rand.nextInt(255),rand.nextInt(255),rand.nextInt(255));

        paises = new ArrayList<>();
        cartas = new ArrayList<>();
        habilitadoLevantarCarta = false;
        this.deposito = deposito;
        objetivos = new ArrayList<>();
        gestorCanjes = new GestorCanjes();
        objetivos.add(new Objetivo());
    }

    //<editor-fold desc="Pais">
    public void asignarPais(Pais unPais) {
        unPais.asignarJugador(this);
        paises.add(unPais);
    }

    public void asignarPais(List<Pais> paises){
        this.paises.addAll(paises);
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

    public void actualizarEjercitosDisponibles() {
        deposito.actualizarEjercitosDisponibles(this);
    }

    public void agregarEjercitosGenerales(int cantidad) {
        deposito.agregarEjercitosGenerales(cantidad);
    }

    // Se podria necesitar para la UI (mostrarle al jugador cuantos ejercitos disponibles tiene)
    public int obtenerEjercitosGeneralesDisponibles() {
        return deposito.obtenerEjercitosGenerales();
    }

    // Se podria necesitar para la UI (mostrarle al jugador cuantos ejercitos disponibles tiene)
    public int obtenerEjercitosDisponiblesEnContinente(Continente continente) {
        return deposito.obtenerEjercitosContinente(continente);
    }

    public void colocarEjercitos(Pais unPais, int cantidad) {
        if (!paisMePertenece(unPais)) throw new PaisNoMePerteneceException();
        deposito.agregarEjercitosAPais(unPais, cantidad);
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
        gestorCanjes.canjearCartas(this, cartas, mazo);
    }

    //</editor-fold>

    public Paint getColor() { // AUXILIAR
        return auxColor;
    }

    public void conquistoPais() {
        habilitadoLevantarCarta = true;
    }

    public boolean haGanado() {
        return objetivos.stream().anyMatch(o -> o.cumplido(this));
    }
}

