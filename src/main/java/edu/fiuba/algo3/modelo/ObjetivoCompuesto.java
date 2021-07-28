package edu.fiuba.algo3.modelo;

import java.util.List;

public class ObjetivoCompuesto implements IObjetivo {

    private List<IObjetivo> objetivos;

    public ObjetivoCompuesto(List<IObjetivo> objetivos) {
        this.objetivos = objetivos;
    }

    public boolean cumplido(Jugador jugador) {
        return objetivos.stream().allMatch(o -> o.cumplido(jugador));
    }

    public boolean esValidoPara(Jugador jugador) {
        return objetivos.stream().allMatch(o -> o.esValidoPara(jugador));
    }

    public String getTexto() {
        String txt = "";
        for (IObjetivo obj : objetivos) {
            txt += obj.getTexto();
        }
        return txt;
    }
}
