package edu.fiuba.algo3.modelo;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

public class Dados {
    Random rand = new Random();
    ArrayList dados = new ArrayList();

    Dados(int cantidad) {
        // El constructor recibe una cantidad y devuelve un ArrayList de números del 1 al 6 ordenados de mayor a menor
        for (int i = 0; i < cantidad; i++) dados.add(rand.nextInt(6)+1);
        dados.sort(Comparator.reverseOrder());
    }
}
