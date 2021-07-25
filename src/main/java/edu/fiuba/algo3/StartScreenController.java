package edu.fiuba.algo3;

import edu.fiuba.algo3.modelo.Jugador;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class StartScreenController {

    @FXML
    public TextField textJugador1;
    public TextField textJugador2;
    public TextField textJugador3;
    public TextField textJugador4;
    public TextField textJugador5;
    public TextField textJugador6;
    public Label labelError;

    private Stage stage;
    private Scene scene;



    public void iniciarPartida(ActionEvent event) throws IOException {
        ArrayList<Jugador> jugadores = new ArrayList<>();
        if(!textJugador1.getText().isEmpty()) jugadores.add(new Jugador(1)); // todo: agregar nombre del jugador para mostrar en la interfaz grafica
        if(!textJugador2.getText().isEmpty()) jugadores.add(new Jugador(2));
        if(!textJugador3.getText().isEmpty()) jugadores.add(new Jugador(3));
        if(!textJugador4.getText().isEmpty()) jugadores.add(new Jugador(4));
        if(!textJugador5.getText().isEmpty()) jugadores.add(new Jugador(5));
        if(!textJugador6.getText().isEmpty()) jugadores.add(new Jugador(6));

        if(jugadores.size()<2) labelError.setText("Ingrese una cantidad de jugadores mayor a 1");
        else {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/GameScreen.fxml"));
            Parent root = loader.load();
            GameScreenController gameScreenController = loader.getController();
            gameScreenController.iniciarPartida(jugadores);

            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }

}
