package edu.fiuba.algo3.modelo;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.util.ArrayList;
import java.util.List;

public class Pais implements IObserbable { //todo: crear interfaz de pais

    private final String nombre;
    private Ejercitos ejercitos;
    private final List<String> limitrofes;
    private Jugador jugador;
    private List<ObservadorPais> observadores = new ArrayList<>();


    public Pais(String nombre, List<String> limitrofes, Ejercitos ejercitos) {
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

        actualizarObservadores();
    }

    public void actualizarObservadores(){
        observadores.forEach(observador -> {
            observador.actualizar();
        });
    }

    public void quitarEjercitos(int cantidad) {
        ejercitos.quitarEjercitos(cantidad);
        actualizarObservadores();
    }

    public void transferirEjercitos(Pais paisDestino, int cantidad) {
        ejercitos.transferirEjercitos(paisDestino, cantidad);
        actualizarObservadores();
    }

    public void asignarJugador(Jugador unJugador) {
        jugador = unJugador;
        actualizarObservadores();
    }


    public Jugador getJugador() {
        return jugador;
    }

    public void conquistar(Pais defensor) {
        defensor.asignarJugador(jugador);
        jugador.asignarPais(defensor);
        transferirEjercitos(defensor,1);
        jugador.conquistoPais();
        actualizarObservadores();
    }

    public void asignarObservador(ObservadorPais observador) {
        observador.asignarPais(this);
        observadores.add(observador);
    }
}

