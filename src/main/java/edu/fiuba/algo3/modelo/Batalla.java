package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.exception.CantidadEjercitosInsuficienteException;
import edu.fiuba.algo3.exception.PaisNoLimitrofeException;

import java.util.List;

public class Batalla {

    private Pais atacante;
    private Pais defensor;
    private List<Integer> dadosAtacante;
    private List<Integer> dadosDefensor;

    public Batalla(Pais atacante, Pais defensor, IDado dado) {
        this.atacante = atacante;
        this.defensor = defensor;
        this.dadosAtacante = dado.obtenerDadoAtacante(atacante);
        this.dadosDefensor = dado.obtenerDadoDefensor(defensor);
    }

    public List<Integer> obtenerDadosAtacante() {
        return dadosAtacante;
    }

    public List<Integer> obtenerDadosDefensor() {
        return dadosDefensor;
    }

    public void realizarAtaque() {
        validarAtaque();

        int cantidadDados = Math.min(dadosAtacante.size(), dadosDefensor.size());

        for (int i = 0; i < cantidadDados; i++) {

            if (dadosAtacante.get(i) > dadosDefensor.get(i))
                defensor.quitarEjercitos(1);
            else
                atacante.quitarEjercitos(1);
        }

        if (defensor.noTieneEjercitos()){
            atacante.conquistar(defensor);
        }
    }

    private void validarAtaque(){
        validarLimitrofes();
        validarCantidadEjercitos();
    }

    private void validarLimitrofes(){
        if(!atacante.esLimitrofeCon(defensor)){
            throw new PaisNoLimitrofeException();
        }
    }

    private void validarCantidadEjercitos(){
        if (!atacante.cantidadEjercitosSuperiorA(1)){
            throw new CantidadEjercitosInsuficienteException();
        }
    }
}

