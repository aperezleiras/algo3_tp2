package edu.fiuba.algo3.controlador;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ObjetivoScreenController {

    @FXML
    public Label labelObjetivo;

    public void setearTexto(String text){
        labelObjetivo.setText(text);
    }

}
