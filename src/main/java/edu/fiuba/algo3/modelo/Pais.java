package edu.fiuba.algo3.modelo;

import java.util.List;

public class Pais { //todo: crear interfaz de pais

    private final String nombre;
    private Ejercitos ejercitos;
    private final List<String> limitrofes;
    private Jugador jugador;


    Pais(String nombre, List<String> limitrofes, Ejercitos ejercitos) {
        this.nombre = nombre;
        this.limitrofes = limitrofes;
        this.ejercitos = ejercitos;
    }

    public void agregarLimitrofe(String pais) {
        limitrofes.add(pais);
    }

    public int cantidadEjercitos() {
        return ejercitos.getCantidad();
    }

    //TODO esto o getter?
    public boolean esLimitrofeCon(Pais pais) {
        return limitrofes.contains(pais.getNombre());
    }

    public String getNombre() {
        return nombre;
    }

    public Ejercitos getEjercitos() {
        return ejercitos;
    }

    public boolean noTieneEjercitos() {
        return ejercitos.noQuedanEjercitos();
    }

    public boolean cantidadEjercitosSuperiorA(int cantidad) {
        return ejercitos.cantidadEjercitosSuperiorA(cantidad);
    }

    public void agregarEjercitos(int cantidad) {
        ejercitos.agregarEjercitos(cantidad);
    }

    public void quitarEjercitos(int cantidad) {
        ejercitos.quitarEjercitos(cantidad);

    } //todo: validar que no quede en numeros negativos y conquista

    public void transferirEjercitos(Pais paisDestino, int cantidad) {
        ejercitos.transferirEjercitos(paisDestino, cantidad);
    }

    public void asignarJugador(Jugador unJugador) {
        jugador = unJugador;
    }


    public Jugador getJugador() {
        return jugador;
    }

    public void conquistar(Pais defensor) {
        defensor.asignarJugador(jugador);
        transferirEjercitos(defensor,1);
    }
}

