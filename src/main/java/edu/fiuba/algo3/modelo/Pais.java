package edu.fiuba.algo3.modelo;

import java.util.ArrayList;

public class Pais {

    String nombre;
    Ejercitos ejercitos;
    ArrayList<Pais> limitrofes;

    Pais (String unNombre) {
        nombre = unNombre;
        limitrofes = new ArrayList<>();
        ejercitos = new Ejercitos();
    }

    public Ejercitos getEjercitos() {
        return ejercitos;
    }

    public void agregarLimitrofe(Pais unPais) {
        limitrofes.add(unPais);
    }

    public void colocarEjercitos(int cantidad) {
        ejercitos.agregarEjercitos(cantidad);
    }

    public boolean cantidadEjercitosSuperiorA(int unaCantidad) {
        return (ejercitos.cantidadEjercitosSuperiorA(unaCantidad));
    }

    public boolean esLimitrofeCon(Pais unPais) {
        return limitrofes.contains(unPais);
    }

    public void transferirEjercitosA(Pais paisDestino, int cantidad) {
        ejercitos.transferirEjercitos(paisDestino, cantidad);
    }

    public void asignarJugador(Jugador unJugador) {
        ejercitos.asignarJugador(unJugador);
    }

}

