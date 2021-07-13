package edu.fiuba.algo3.modelo;

import java.util.List;

public class Ejercitos {

    int cantidad;


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
        if (cantidad >= unaCantidad)
            cantidad -= unaCantidad;
    }

    public void transferirEjercitos(Pais paisDestino, int unaCantidad) {
        if (cantidad > unaCantidad) {
            quitarEjercitos(unaCantidad);
            paisDestino.agregarEjercitos(unaCantidad);
        }
    }

    public Dados calcularDadosAtacante() {
        int cantidadDados = Math.min(3, cantidad-1);
        return (new Dados(cantidadDados));
    }

    public Dados calcularDadosDefensor() {
        int cantidadDados = Math.min(3, cantidad);
        return (new Dados(cantidadDados));
    }
}

