package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.exception.MazoVacioException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

public class MazoCartasPais {
    public ArrayList<CartaPais> cartas;

    public MazoCartasPais(ArrayList<CartaPais> cartas) {
        // Queue<CartaPais> cartasQ = new LinkedList<>(cartas);
        this.cartas = cartas;
    }

    public void mezclar() {
        Collections.shuffle(cartas);
    }

    public CartaPais levantarCarta() {
        CartaPais carta;
        try {
            carta = cartas.get(cartas.size()-1);
            cartas.remove(cartas.size()-1);
        } catch (IndexOutOfBoundsException e) {
            throw new MazoVacioException();
        }
        return carta;
    }

    // La pone al fondo del mazo
    public void agregarCarta(CartaPais carta) {
        cartas.add(0, carta);
    }
}
