package edu.fiuba.algo3.modelo;

import java.util.List;

public class Batalla {
    public static void realizarAtaque(Pais atacante, Pais defensor) {
        Dados dadosAtacante = atacante.obtenerDadosAtacante();
        Dados dadosDefensor = defensor.obtenerDadosDefensor();

        int cantidadDados = Math.min(dadosAtacante.cantidad(), dadosDefensor.cantidad());

        for (int i = 0; i < cantidadDados; i++) {
            if (dadosAtacante.get(i) > dadosDefensor.get(i))
                defensor.quitarEjercitos(1);  //todo: validar que no quede en numeros negativos, conquista y pueda atacar segun cantidad
            else
                atacante.quitarEjercitos(1);
        }

        if (defensor.noTieneEjercitos())
            atacante.conquistar(defensor);
    }
}

