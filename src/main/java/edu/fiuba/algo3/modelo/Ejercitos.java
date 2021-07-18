package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.exception.CantidadAQuitarInvalidaException;
import edu.fiuba.algo3.exception.CantidadATransferirInvalidaException;

public class Ejercitos {

    private int cantidad;

    Ejercitos(int unaCantidad) {
        cantidad = unaCantidad;
    }

    public int getCantidad() {
        return cantidad;
    }

    public boolean noQuedanEjercitos() {
        return (cantidad == 0);
    }

    public boolean cantidadEjercitosSuperiorA(int unaCantidad) {
        return (cantidad > unaCantidad);
    }


    public void agregarEjercitos(int unaCantidad) {
        cantidad += unaCantidad;
    }

    public void quitarEjercitos(int unaCantidad) {
        if (unaCantidad > cantidad) throw new CantidadAQuitarInvalidaException();
        cantidad -= unaCantidad;
    }

    public void transferirEjercitos(Pais paisDestino, int unaCantidad) {
        if (unaCantidad >= cantidad) throw new CantidadATransferirInvalidaException();
        quitarEjercitos(unaCantidad);
        paisDestino.agregarEjercitos(unaCantidad);
    }

}

