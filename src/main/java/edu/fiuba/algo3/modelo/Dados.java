package edu.fiuba.algo3.modelo;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

public class Dados { //todo: cambiar a clase estatica
    Random rand = new Random();
    ArrayList dados = new ArrayList();

    Dados(int cantidad) {
        // El constructor recibe una cantidad y arma un ArrayList de números del 1 al 6 ordenados de mayor a menor
        for (int i = 0; i < cantidad; i++)
            dados.add(rand.nextInt(6)+1);

        dados.sort(Comparator.reverseOrder());
    }

    public int cantidad() {
        return dados.size();
    }

    public int get(int indice) {
        return (int) dados.get(indice);
    }
}
