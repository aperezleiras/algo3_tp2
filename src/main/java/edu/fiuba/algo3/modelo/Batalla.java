package edu.fiuba.algo3.modelo;

public class Batalla {

    private final Pais atacante;
    private final Pais defensor;

    Batalla(Pais atacante, Pais defensor) {
        this.atacante = atacante;
        this.defensor = defensor;
    }

    public void resolver() {
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

