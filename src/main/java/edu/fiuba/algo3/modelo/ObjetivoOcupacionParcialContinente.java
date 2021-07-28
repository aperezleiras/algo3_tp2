package edu.fiuba.algo3.modelo;

public class ObjetivoOcupacionParcialContinente implements IObjetivo {

    private Continente continente;
    private int cantidad;

    public ObjetivoOcupacionParcialContinente(Continente continenteAOcupar, int cantidadAOcupar) {
        continente = continenteAOcupar;
        cantidad = cantidadAOcupar;
    }

    public boolean cumplido(Jugador jugador) {
        int ocupados = 0;
        for (Pais p : jugador.getPaises()) {
            if (continente.tienePais(p))
                    ocupados ++;
        }
        return ocupados >= cantidad;
    }

    public boolean esValidoPara(Jugador jugador) {
        return true;
    }

    public String getTexto() {
        return "Ocupar " + cantidad + " paises en " + continente.getNombre() + ". ";
    }
}
