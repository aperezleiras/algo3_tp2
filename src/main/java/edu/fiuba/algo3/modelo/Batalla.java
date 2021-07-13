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
    public void realizarAtaque() throws PaisNoLimitrofeException, CantidadEjercitosInsuficienteException {
        if (!validarAtaque()){
            if (!validarLimitrofes()){
                throw new PaisNoLimitrofeException();
            }
            throw new CantidadEjercitosInsuficienteException();
        }

        //TODO dos metodos o sacar el ultimo/primer elemento
        List<Integer> dadosAtacante = dadoAtacante.obtenerDado(atacante);
        List<Integer> dadosDefensor = dadoDefensor.obtenerDado(defensor);

        int cantidadDados = Math.min(dadosAtacante.size(), dadosDefensor.size());

        for (int i = 0; i < cantidadDados; i++) {

            if (dadosAtacante.get(i) > dadosDefensor.get(i))
                defensor.quitarEjercitos(1);  //todo: validar que no quede en numeros negativos, conquista y pueda atacar segun cantidad
            else
                atacante.quitarEjercitos(1);
        }

        if (defensor.noTieneEjercitos())
            defensor.fueConquistadoPor(atacante);
    }

    private boolean validarAtaque(){
        return (validarLimitrofes() && validarCantidadEjercitos());
    }

    private boolean validarLimitrofes(){
        return atacante.esLimitrofeCon(defensor);
    }

    private boolean validarCantidadEjercitos(){
        return atacante.cantidadEjercitosSuperiorA(1);
    }
}

