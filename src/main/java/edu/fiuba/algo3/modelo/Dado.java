package edu.fiuba.algo3.modelo;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class Dado implements IDado{

    @Override
    public List<Integer> obtenerDadoDefensor(Pais pais) {
        Random rand = new Random();

        List<Integer> dados = new ArrayList<>();
        int contador = 0;

        while (contador < 3 && contador < (pais.getEjercitos().getCantidad())){
            dados.add(rand.nextInt(6)+1);
            contador++;
        }

        dados.sort(Comparator.reverseOrder());
        return dados;
    }

    @Override
    public List<Integer> obtenerDadoAtacante(Pais pais) {
        Random rand = new Random();

        List<Integer> dados = new ArrayList<>();
        int contador = 0;

        while (contador < 3 && contador < (pais.getEjercitos().getCantidad())-1){
            dados.add(rand.nextInt(6)+1);
            contador++;
        }

        dados.sort(Comparator.reverseOrder());
        return dados;
    }

}
