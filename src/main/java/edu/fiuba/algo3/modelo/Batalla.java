package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.exception.CantidadEjercitosInsuficienteException;
import edu.fiuba.algo3.exception.PaisNoLimitrofeException;

import java.util.List;

public class Batalla implements IBatalla{

    private Pais atacante;

    private Pais defensor;

    private IDado dadoAtacante;

    private IDado dadoDefensor;

    public Batalla(Pais atacante, Pais defensor, IDado dadoAtacante, IDado dadoDefensor) {
        this.atacante = atacante;
        this.defensor = defensor;
        this.dadoAtacante = dadoAtacante;
        this.dadoDefensor = dadoDefensor;
    }

    @Override
    public void realizarAtaque() {
        validarAtaque();

        //TODO dos metodos o sacar el ultimo/primer elemento
        List<Integer> dadosAtacante = dadoAtacante.obtenerDadoAtacante(atacante);
        List<Integer> dadosDefensor = dadoDefensor.obtenerDadoDefensor(defensor);

        int cantidadDados = Math.min(dadosAtacante.size(), dadosDefensor.size());

        for (int i = 0; i < cantidadDados; i++) {

            if (dadosAtacante.get(i) > dadosDefensor.get(i))
                defensor.quitarEjercitos(1);  //todo: validar que no quede en numeros negativos, conquista y pueda atacar segun cantidad
            else
                atacante.quitarEjercitos(1);
        }

        if (defensor.noTieneEjercitos())
            atacante.conquistar(defensor);
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

