package edu.fiuba.algo3.modelo;

import java.util.List;

public class Pais {

    private final String nombre;
    private Jugador jugador;
    private final Ejercitos ejercitos;
    private final List<Pais> limitrofes;

    Pais(String nombre, List<Pais> limitrofes, Ejercitos ejercitos) {
        this.nombre = nombre;
        this.limitrofes = limitrofes;
        this.ejercitos = ejercitos;
    }

    public void agregarLimitrofe(Pais pais) {
        limitrofes.add(pais);
    }

    public void asignarJugador(Jugador jugador) {
        this.jugador = jugador;
    }

    public boolean esLimitrofeCon(Pais pais) {
        return limitrofes.contains(pais);
    }

    public int cantidadEjercitos() {
        return ejercitos.getCantidad();
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

    public void conquistar(Pais unPais) {
        unPais.asignarJugador(jugador);
        transferirEjercitos(unPais, 1);
    }

    public Dados obtenerDadosAtacante() { return (ejercitos.calcularDadosAtacante()); }
    public Dados obtenerDadosDefensor() { return (ejercitos.calcularDadosDefensor()); }

}

