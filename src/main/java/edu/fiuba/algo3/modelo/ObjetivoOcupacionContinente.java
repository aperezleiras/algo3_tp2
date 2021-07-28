package edu.fiuba.algo3.modelo;

public class ObjetivoOcupacionContinente implements IObjetivo {

    private Continente continente;

    public ObjetivoOcupacionContinente(Continente continenteAOcupar) {
        continente = continenteAOcupar;
    }

    public boolean cumplido(Jugador jugador) {
        return (continente.completo(jugador));
    }

    public boolean esValidoPara(Jugador jugador) {
        return true;
    }

    public String getTexto() {
        return "Ocupar " + continente.getNombre() + ". ";
    }
}
