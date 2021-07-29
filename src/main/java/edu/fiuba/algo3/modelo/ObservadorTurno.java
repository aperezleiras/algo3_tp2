package edu.fiuba.algo3.modelo;

import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class ObservadorTurno implements IObservador{

    private final Group groupTransferir;
    private final Group groupAtacar;
    private final Group groupColocar;
    private final Group groupCanjear;
    private final Label labelTurno;
    private final Label labelFase;
    private final Button botonJugadorActual;

    private Turno turno;

    public ObservadorTurno(Group groupTransferir, Group groupAtacar, Group groupColocar, Group groupCanjear, Label labelTurno, Label labelFase, Button botonJugadorActual){
        this.labelTurno = labelTurno;
        this.labelFase = labelFase;
        this.botonJugadorActual = botonJugadorActual;
        this.groupTransferir = groupTransferir;
        this.groupAtacar = groupAtacar;
        this.groupColocar = groupColocar;
        this.groupCanjear = groupCanjear;
    }

    public void asignarTurno(Turno turno){
        this.turno = turno;
    }

    @Override
    public void actualizar() {
        switch (turno.obtenerFase()){
            case ATACAR:
                groupTransferir.setVisible(false);
                groupAtacar.setVisible(true);
                groupColocar.setVisible(false);
                break;
            case COLOCAR:
                groupColocar.setVisible(true);
                groupTransferir.setVisible(false);
                groupCanjear.setVisible(false);
                break;
            case REAGRUPAR:
                groupTransferir.setVisible(true);
                groupAtacar.setVisible(false);
                break;
            case CANJEAR:
                groupCanjear.setVisible(true);
                groupTransferir.setVisible(false);
                groupColocar.setVisible(false);
        }
        labelTurno.setText("Turno de: " + turno.obtenerJugadorActual().getNombre());
        labelFase.setText("Fase: " + turno.obtenerFase().toString());
        botonJugadorActual.setStyle("-fx-background-color: " + turno.obtenerJugadorActual().getColor() + "; -fx-background-radius: 100; -fx-border-width: 2; -fx-border-color: black; -fx-border-radius: 100; -fx-border-insets: -1;");
    }
}
