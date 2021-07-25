package edu.fiuba.algo3;

import edu.fiuba.algo3.modelo.Juego;
import edu.fiuba.algo3.modelo.Jugador;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

public class GameScreenController implements Initializable {


    public Button botonAvanzarFase;
    public Group groupTransferir;
    public Group groupAtacar;
    boolean faseAtacar = true;
    public Juego juego;
    public ArrayList<Jugador> listaJugadores;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void avanzarFase(){
        faseAtacar = !faseAtacar;
        groupTransferir.setVisible(!faseAtacar);
        groupAtacar.setVisible(faseAtacar);


    }

    public void seleccionarPais(ActionEvent event){
        String nombrePais = ((Button)event.getSource()).getAccessibleText();
        // hacer que busque a ver si selecciono un pais suyo o no

    }

    public void iniciarPartida(ArrayList<Jugador> jugadores) throws FileNotFoundException {
        listaJugadores = jugadores;
        juego = new Juego(jugadores);

    }

    public void verObjetivo() throws IOException {
        Parent root = FXMLLoader.load((getClass().getResource("/ObjetivoScreen.fxml")));
        // todo: Acá habría que sacar del jugador actual el texto de la misión
        Stage objetivoStage = new Stage();

        objetivoStage.initModality(Modality.APPLICATION_MODAL);
        objetivoStage.setTitle("Ver objetivo");

        objetivoStage.setScene(new Scene(root));
        objetivoStage.setResizable(false);
        objetivoStage.show();

    }

}
