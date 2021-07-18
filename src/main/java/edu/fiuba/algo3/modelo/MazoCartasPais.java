package edu.fiuba.algo3.modelo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

public class MazoCartasPais {
    public ArrayList<CartaPais> cartas;

    public MazoCartasPais(ArrayList<CartaPais> cartas) {
        // Queue<CartaPais> cartasQ = new LinkedList<>(cartas);
        this.cartas = cartas;
        mezclar();
    }

    public void mezclar() {
        Collections.shuffle(cartas);
    }

    public CartaPais levantarCarta() {
        CartaPais carta = cartas.get(cartas.size()-1);
        cartas.remove(cartas.size()-1);
        return carta;
    }

    // La pone al fondo del mazo
    public void devolverCarta(CartaPais carta) {
        cartas.add(0, carta);
    }
}
