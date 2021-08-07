package edu.fiuba.algo3.controlador;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class EndScreenController implements Initializable {

    @FXML
    public Label textoGanador;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void cerrarJuego(ActionEvent event){
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    public void setearGanador(String ganador) {
        textoGanador.setText("¡" + ganador + " ha ganado!");
    }

}
