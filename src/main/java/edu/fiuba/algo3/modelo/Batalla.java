package edu.fiuba.algo3.modelo;

import java.util.List;

public class Batalla {
    public static void realizarAtaque(Pais atacante, Pais defensor) {
        List<Integer> dadosAtacante = atacante.obtenerDadosAtacante();
        List<Integer> dadosDefensor = defensor.obtenerDadosDefensor();

        int cantidadDados = Math.min(dadosAtacante.size(), dadosDefensor.size());

        for (int i = 0; i < cantidadDados; i++) {
            if (dadosAtacante.get(i) > dadosDefensor.get(i))
                defensor.colocarEjercitos(-1);  //todo: validar que no quede en numeros negativos, conquista y pueda atacar segun cantidad
            else
                atacante.colocarEjercitos(-1);
        }
    }
}

