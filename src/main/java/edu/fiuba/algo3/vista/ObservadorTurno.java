package edu.fiuba.algo3.vista;

import edu.fiuba.algo3.controlador.EndScreenController;
import edu.fiuba.algo3.controlador.ObjetivoScreenController;
import edu.fiuba.algo3.modelo.Batalla;
import edu.fiuba.algo3.modelo.IObservador;
import edu.fiuba.algo3.modelo.Turno;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ObservadorTurno implements IObservador {

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

        if (turno.hayGanador()) {
            String ganador = turno.obtenerJugadorActual().getNombre();
            try {
                terminarJuego(ganador);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public void terminarJuego(String ganador) throws IOException {
        Stage stage = (Stage) botonJugadorActual.getScene().getWindow();
        stage.close();

        FXMLLoader loader = new FXMLLoader((getClass().getResource("/vista/EndScreen.fxml")));
        Parent root = loader.load();
        EndScreenController endScreenController = loader.getController();
        endScreenController.setearGanador(ganador);

        Stage endStage = new Stage();

        endStage.initModality(Modality.APPLICATION_MODAL);
        endStage.setTitle("Fin del juego");
        endStage.getIcons().add(new Image(getClass().getResourceAsStream("/img/icono.png")));
        endStage.setScene(new Scene(root));
        endStage.setResizable(false);

        endStage.show();
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
