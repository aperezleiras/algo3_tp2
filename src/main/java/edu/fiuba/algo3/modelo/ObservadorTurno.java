package edu.fiuba.algo3.modelo;

import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class ObservadorTurno implements IObservador{

    private final Group groupTransferir;
    private final Group groupAtacar;
    private final Group groupColocar;
    private final Group groupCanjear;
    private final Label labelTurno;
    private final Label labelFase;
    private final Button botonJugadorActual;
    private final ArrayList<ImageView> imageViewDados;
    private ArrayList<Image> imagenesDados;

    private Turno turno;

    public ObservadorTurno(Group groupTransferir, Group groupAtacar, Group groupColocar, Group groupCanjear, Label labelTurno, Label labelFase, Button botonJugadorActual, ArrayList<ImageView> imageViewDados){
        this.labelTurno = labelTurno;
        this.labelFase = labelFase;
        this.botonJugadorActual = botonJugadorActual;
        this.groupTransferir = groupTransferir;
        this.groupAtacar = groupAtacar;
        this.groupColocar = groupColocar;
        this.groupCanjear = groupCanjear;
        this.imageViewDados = imageViewDados;

        cargarImagenesDados();
    }

    private void cargarImagenesDados() {
        imagenesDados = new ArrayList<>();
        imagenesDados.add(new Image(getClass().getResourceAsStream("/img/dado1.png")));
        imagenesDados.add(new Image(getClass().getResourceAsStream("/img/dado2.png")));
        imagenesDados.add(new Image(getClass().getResourceAsStream("/img/dado3.png")));
        imagenesDados.add(new Image(getClass().getResourceAsStream("/img/dado4.png")));
        imagenesDados.add(new Image(getClass().getResourceAsStream("/img/dado5.png")));
        imagenesDados.add(new Image(getClass().getResourceAsStream("/img/dado6.png")));
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
                actualizarDados();
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

    public void actualizarDados() {
        Batalla batalla = turno.obtenerUltimaBatalla();
        if (batalla == null)
            return;

        List<Integer> dadosAtacante = batalla.obtenerDadosAtacante();
        List<Integer> dadosDefensor = batalla.obtenerDadosDefensor();
        ImageView imageViewDado;

        int cantidadDadosAtacante = dadosAtacante.size();
        for (int i = 0; i < 3; i ++) {
            imageViewDado = imageViewDados.get(i);
            imageViewDado.setVisible(false);
            if (i < cantidadDadosAtacante) {
                imageViewDado.setImage(imagenesDados.get(dadosAtacante.get(i) - 1));
                imageViewDado.setVisible(true);
            }
        }

        int cantidadDadosDefensor = dadosDefensor.size();
        for (int i = 0; i < 3; i ++) {
            imageViewDado = imageViewDados.get(i+3);
            imageViewDado.setVisible(false);
            if (i < cantidadDadosDefensor) {
                imageViewDado.setImage(imagenesDados.get(dadosDefensor.get(i) - 1));
                imageViewDado.setVisible(true);
            }
        }
    }
}
