package edu.fiuba.algo3.modelo;

public class ObjetivoOcupacionPaises implements IObjetivo {

    private int cantidad;

    public ObjetivoOcupacionPaises(int cantidadPaisesAOcupar) {
        cantidad = cantidadPaisesAOcupar;
    }

    public boolean cumplido(Jugador jugador) {
        return (jugador.obtenerCantidadPaises() >= cantidad);
    }

    public boolean esValidoPara(Jugador jugador) {
        return true;
    }

    public String getTexto() {
        return "Ocupar " + cantidad + " paises. ";
    }
}
