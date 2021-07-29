package edu.fiuba.algo3.modelo;

import javafx.scene.Group;
import javafx.scene.control.Label;

import java.util.ArrayList;

public class ObservadorJugador implements IObservador {

    private Jugador jugador;
    private ArrayList<Label> labelsEjercitos = new ArrayList<>();
    int contador = 1;

    public ObservadorJugador(Group groupEjercitosDisponibles){
        groupEjercitosDisponibles.getChildren().forEach(node -> {
            labelsEjercitos.add((Label) node);
        });
    }

    @Override
    public void actualizar() {
        labelsEjercitos.get(0).setText("Generales: " + jugador.obtenerEjercitosGeneralesDisponibles());

        jugador.getEjercitosPorContinente().forEach( (continente, cantidad) -> {
            labelsEjercitos.get(contador).setText(continente.getNombre() + ": " + cantidad);
            contador++;
        });
        contador = 1;

    }

    public void asignarJugador(Jugador jugador) {
        this.jugador = jugador;
    }
}
